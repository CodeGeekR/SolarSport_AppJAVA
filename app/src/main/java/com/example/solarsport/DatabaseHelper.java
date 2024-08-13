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
    private static final int DATABASE_VERSION = 3; // Incremented version

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

        // Sentencia SQL para crear la tabla de categorías seleccionadas
        String CREATE_SELECTIONS_TABLE = "CREATE TABLE selections (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "user_id INTEGER, " +
                "selected_category TEXT, " +
                "selected_sport_supply TEXT, " +
                "FOREIGN KEY(user_id) REFERENCES users(id))";
        // Ejecutar la sentencia SQL
        db.execSQL(CREATE_SELECTIONS_TABLE);

        // Sentencia SQL para crear la tabla de datos de paneles solares
        String CREATE_SOLAR_DATA_TABLE = "CREATE TABLE solar_data (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "user_id INTEGER, " +
                "num_panels INTEGER, " +
                "energy_produced REAL, " +
                "savings REAL, " +
                "month TEXT, " +
                "selected_category TEXT, " +
                "selected_sport_supply TEXT, " +
                "FOREIGN KEY(user_id) REFERENCES users(id))";
        // Ejecutar la sentencia SQL
        db.execSQL(CREATE_SOLAR_DATA_TABLE);
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
        if (oldVersion < 3) {
            // Modificar la tabla de datos de paneles solares para incluir las nuevas columnas
            String ALTER_SOLAR_DATA_TABLE = "ALTER TABLE solar_data ADD COLUMN selected_category TEXT";
            db.execSQL(ALTER_SOLAR_DATA_TABLE);
            ALTER_SOLAR_DATA_TABLE = "ALTER TABLE solar_data ADD COLUMN selected_sport_supply TEXT";
            db.execSQL(ALTER_SOLAR_DATA_TABLE);
        }
    }
}