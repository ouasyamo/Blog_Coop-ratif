# 📝 Plateforme de Blog Collaboratif (Système de Gestion de Contenu)

![GitHub repo size](https://img.shields.io/github/repo-size/ouasyamo/Blog_Coop-ratif)
![GitHub contributors](https://img.shields.io/github/contributors/username/repository)
![GitHub stars](https://img.shields.io/github/stars/username/repository?style=social)
![GitHub license](https://img.shields.io/github/license/username/repository)

## 🌟 Description

Cette plateforme de blog collaboratif permet aux utilisateurs de publier, modifier et commenter des articles grâce à un système de gestion de contenu (CMS). 
Elle repose sur une architecture multi-technologies :

- **Laravel** pour la gestion des articles et des utilisateurs.
- **Java** pour une application de gestion des articles et des commentaires.
- **Python** pour l'automatisation de la modération des commentaires.

## ✨ Fonctionnalités

- ✅ Création, modification et suppression d'articles
- ✅ Gestion des utilisateurs (admin, auteur, lecteur)
- ✅ Publication et consultation d'articles via une interface Java
- ✅ Commentaires sur les articles
- ✅ Modération automatique des commentaires (détection de mots interdits et spam via Python)

## 🏗 Architecture du Projet

```
📂 blog-platform
 ├── 📁 backend (Laravel - API et gestion du CMS)
 ├── 📁 frontend (Java - Interface utilisateur)
 ├── 📁 moderation (Python - Filtrage des commentaires)
 ├── 📄 README.md
 ├── 📄 LICENSE
```

## 🛠 Installation

### 📌 Prérequis
- PHP 8.x et Composer
- Java 11+
- Python 3.x
- MySQL ou PostgreSQL

### 📦 Installation du Backend (Laravel)
```bash
# Cloner le dépôt
git clone https://github.com/username/repository.git
cd repository/backend

# Installer les dépendances
composer install

# Configurer l'environnement
cp .env.example .env
php artisan key:generate

# Migrer la base de données
php artisan migrate --seed

# Lancer le serveur
php artisan serve
```

### 🎨 Installation de l'Interface Java
```bash
cd repository/frontend
# Compiler et exécuter l'application
javac Main.java
java Main
```

### 🤖 Installation du Système de Modération Python
```bash
cd repository/moderation
pip install -r requirements.txt
python moderation.py
```

## 📸 Aperçu

![Screenshot](https://via.placeholder.com/800x400)

## 🔗 Technologies Utilisées

- ⚡ **Laravel** - Gestion du CMS et API
- ☕ **Java** - Interface utilisateur et gestion des articles/commentaires
- 🐍 **Python** - Automatisation et modération des commentaires

## 🤝 Contribution

1. Forker le projet
2. Créer une branche (`git checkout -b feature-branch`)
3. Commiter vos modifications (`git commit -m 'Ajout d'une nouvelle fonctionnalité'`)
4. Pousser la branche (`git push origin feature-branch`)
5. Ouvrir une Pull Request

## 📜 Licence

Ce projet est sous licence **MIT** - voir le fichier [LICENSE](LICENSE) pour plus de détails.

## 📬 Contact

✉️ Email : your.email@example.com  
🔗 LinkedIn : [Votre Profil](https://linkedin.com/in/yourprofile)
