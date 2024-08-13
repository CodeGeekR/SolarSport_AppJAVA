package com.example.solarsport;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Clase DatabaseHelper que extiende SQLiteOpenHelper para manejar la creación y actualización de la base de datos.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Nombre de la base de datos
    private static final String DATABASE_NAME = "solarsport.db";
    // Versión de la base de datos
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructor de la clase DatabaseHelper.
     *
     * @param context Contexto de la aplicación.
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Método onCreate que se llama cuando la base de datos es creada por primera vez.
     *
     * @param db Instancia de SQLiteDatabase.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Sentencia SQL para crear la tabla de usuarios
        String CREATE_USERS_TABLE = "CREATE TABLE users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "full_name TEXT, " +
                "email TEXT UNIQUE, " +
                "password TEXT, " +
                "salt TEXT, " + "phone INTEGER, " + "address TEXT, " + " birth_year INTEGER)";
        // Ejecutar la sentencia SQL
        db.execSQL(CREATE_USERS_TABLE);
    }

    /**
     * Método onUpgrade que se llama cuando la base de datos necesita ser actualizada.
     *
     * @param db Instancia de SQLiteDatabase.
     * @param oldVersion Versión antigua de la base de datos.
     * @param newVersion Nueva versión de la base de datos.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Eliminar la tabla de usuarios si existe
        db.execSQL("DROP TABLE IF EXISTS users");
        // Crear la tabla de usuarios nuevamente
        onCreate(db);
    }
}