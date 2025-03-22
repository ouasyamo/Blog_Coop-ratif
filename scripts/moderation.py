import re
import mysql.connector
from config import DB_CONFIG

# Charger les mots interdits
def load_banned_words(file_path="banned_words.txt"):
    try:
        with open(file_path, "r", encoding="utf-8") as file:
            return set(word.strip().lower() for word in file)
    except FileNotFoundError:
        print("⚠️ Fichier 'banned_words.txt' introuvable !")
        return set()

# Vérifier si un commentaire est autorisé
def is_comment_allowed(comment, banned_words):
    words = re.findall(r'\b\w+\b', comment.lower())  # Extraction des mots
    return not any(word in banned_words for word in words)

# Récupérer les commentaires en attente
def get_pending_comments():
    try:
        conn = mysql.connector.connect(**DB_CONFIG)
        cursor = conn.cursor(dictionary=True)

        cursor.execute("SELECT id, user_id, post_id, comment FROM comments WHERE status = 'pending'")
        comments = cursor.fetchall()

        conn.close()
        return comments

    except mysql.connector.Error as e:
        print(f"❌ Erreur MySQL: {e}")
        return []

# Mettre à jour le statut d'un commentaire
def update_comment_status(comment_id, status):
    try:
        conn = mysql.connector.connect(**DB_CONFIG)
        cursor = conn.cursor()

        sql = "UPDATE comments SET status = %s WHERE id = %s"
        cursor.execute(sql, (status, comment_id))

        conn.commit()
        conn.close()
        print(f"✅ Commentaire {comment_id} mis à jour : {status}")

    except mysql.connector.Error as e:
        print(f"❌ Erreur MySQL: {e}")

# Processus de modération automatique
def moderate_comments():
    banned_words = load_banned_words()
    pending_comments = get_pending_comments()

    if not pending_comments:
        print("🚀 Aucun commentaire en attente à modérer.")
        return

    for comment in pending_comments:
        status = "allow" if is_comment_allowed(comment["comment"], banned_words) else "block"
        update_comment_status(comment["id"], status)

# Exécution
if __name__ == "__main__":
    moderate_comments()
