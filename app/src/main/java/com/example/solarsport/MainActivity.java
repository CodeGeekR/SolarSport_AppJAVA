// MainActivity.java
package com.example.solarsport;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout homeContainer, categoriesContainer, statisticsContainer, benefitsContainer, profileContainer;
    private TextView userNameTextView; // TextView para mostrar el nombre del usuario
    private DatabaseHelper dbHelper; // Helper para acceder a la base de datos
    private RelativeLayout rootLayout; // Root layout para detectar clicks
    private int userId; // ID del usuario logueado
    private String fullName; // Nombre completo del usuario

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar el helper de la base de datos
        dbHelper = new DatabaseHelper(this);

        // Obtener referencia al TextView para el nombre de usuario
        userNameTextView = findViewById(R.id.user_name);

        // Obtener referencia al root layout
        rootLayout = findViewById(R.id.root_layout);

        // Obtener el ID y el nombre completo del usuario desde el Intent
        userId = getIntent().getIntExtra("USER_ID", -1);
        fullName = getIntent().getStringExtra("FULL_NAME");

        // Si el nombre completo no se pasó en el Intent, obtenerlo de la base de datos
        if (fullName == null) {
            fullName = obtenerNombreUsuario(userId);
        }

        // Configurar el listener para actualizar el nombre de usuario al hacer click en el root layout
        rootLayout.setOnClickListener(v -> updateUserName());

        // Configurar los listeners para los contenedores (botones)
        homeContainer = findViewById(R.id.home_container);
        categoriesContainer = findViewById(R.id.categories_container);
        statisticsContainer = findViewById(R.id.statistics_container);
        benefitsContainer = findViewById(R.id.benefits_container);
        profileContainer = findViewById(R.id.profile_container);

        homeContainer.setOnClickListener(v -> {
            // Lógica para el botón "Home"
        });

        categoriesContainer.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
            intent.putExtra("USER_ID", userId);
            intent.putExtra("FULL_NAME", fullName);
            startActivity(intent);
        });

        statisticsContainer.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, StatisticsActivity.class);
            intent.putExtra("USER_ID", userId);
            intent.putExtra("FULL_NAME", fullName);
            startActivity(intent);
        });

        benefitsContainer.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BenefitsActivity.class);
            intent.putExtra("USER_ID", userId);
            intent.putExtra("FULL_NAME", fullName);
            startActivity(intent);
        });

        profileContainer.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.putExtra("USER_ID", userId);
            intent.putExtra("FULL_NAME", fullName);
            startActivity(intent);
        });

        // Configurar el listener para el icono de cierre de sesión
        ImageView logoutIcon = findViewById(R.id.logout_icon);
        logoutIcon.setOnClickListener(v -> cerrarSesion());
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUserName();
    }

    /**
     * Actualiza el nombre de usuario en el TextView.
     */
    private void updateUserName() {
        if (fullName != null) {
            userNameTextView.setText("Hola, " + fullName);
        } else {
            userNameTextView.setText("Usuario");
        }
    }

    /**
     * Obtiene el nombre completo del usuario desde la base de datos SQLite usando el ID.
     *
     * @param userId ID del usuario.
     * @return El nombre completo del usuario, o "Usuario" si no se encuentra.
     */
    private String obtenerNombreUsuario(int userId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {"full_name"};
        String selection = "id = ?";
        String[] selectionArgs = { String.valueOf(userId) };
        Cursor cursor = db.query("users", columns, selection, selectionArgs, null, null, null);

        String fullName = "Usuario"; // Valor por defecto

        if (cursor != null && cursor.moveToFirst()) {
            fullName = cursor.getString(cursor.getColumnIndexOrThrow("full_name"));
            cursor.close();
        }

        db.close();
        return fullName;
    }

    /**
     * Cierra la sesión del usuario y lo redirige a LoginActivity.
     * Limpia los datos en memoria o cache.
     */
    private void cerrarSesion() {
        // Redirigir a LoginActivity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}