<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

// return new class extends Migration
// {
//     /**
//      * Run the migrations.
//      */
//     public function up(): void
//     {
//         Schema::table('comments', function (Blueprint $table) {
//             //
//         });
//     }

//     /**
//      * Reverse the migrations.
//      */
//     public function down(): void
//     {
//         Schema::table('comments', function (Blueprint $table) {
//             //
//         });
//     }
// };



return new class extends Migration {
    public function up()
    {
        Schema::table('comments', function (Blueprint $table) {
            $table->enum('status', ['pending', 'allow', 'block'])->default('pending');// Ajoute la colonne status
        });
    }

    public function down()
    {
        Schema::table('comments', function (Blueprint $table) {
            $table->dropColumn('status'); // Supprime la colonne en cas de rollback
        });
    }
};

