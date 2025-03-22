<?php

namespace App\Observers;

use App\Models\Comment;

use Illuminate\Support\Facades\Log;

class CommentObserver
{
    public function created(Comment $comment)
    {
        $pythonScript = base_path('scripts/moderation.py');

    // Vérifier si le fichier existe
    if (file_exists($pythonScript)) {
        // Exécuter le script Python avec le commentaire et l'ID
        $output = shell_exec("python $pythonScript \"$comment->comment\" $comment->id");

        // Enregistrer le résultat dans les logs Laravel
        Log::info("Python moderation executed: " . $output);
    } else {
        // Enregistrer une erreur si le fichier n'existe pas
        Log::error("Python moderation script not found: " . $pythonScript);
}
    }


    /**
     * Handle the Comment "updated" event.
     */
    public function updated(Comment $comment): void
    {
        //
    }

    /**
     * Handle the Comment "deleted" event.
     */
    public function deleted(Comment $comment): void
    {
        //
    }

    /**
     * Handle the Comment "restored" event.
     */
    public function restored(Comment $comment): void
    {
        //
    }

    /**
     * Handle the Comment "force deleted" event.
     */
    public function forceDeleted(Comment $comment): void
    {
        //
    }
}
