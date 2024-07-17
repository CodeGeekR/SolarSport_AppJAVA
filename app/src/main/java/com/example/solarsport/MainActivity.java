package com.example.solarsport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

/**
 * MainActivity es la actividad principal que maneja la lógica de navegación
 * de los íconos inferiores: Home, Categorías, Estadísticas, Beneficios, Perfil.
 */
public class MainActivity extends AppCompatActivity {

    // Definición de los LinearLayout como contenedores de los íconos
    private LinearLayout homeContainer, categoriesContainer, statisticsContainer, benefitsContainer, profileContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialización de los LinearLayout
        homeContainer = findViewById(R.id.home_container);
        categoriesContainer = findViewById(R.id.categories_container);
        statisticsContainer = findViewById(R.id.statistics_container);
        benefitsContainer = findViewById(R.id.benefits_container);
        profileContainer = findViewById(R.id.profile_container);

        // Establecer los OnClickListener para cada contenedor
        homeContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí se maneja el clic en "Home"
                // Por ejemplo, reiniciar esta actividad o mostrar el fragmento de inicio
            }
        });

        categoriesContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a la actividad de Categorías
                Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
                startActivity(intent);
            }
        });

        statisticsContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a la actividad de Estadísticas
                Intent intent = new Intent(MainActivity.this, StatisticsActivity.class);
                startActivity(intent);
            }
        });

        benefitsContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a la actividad de Beneficios
                Intent intent = new Intent(MainActivity.this, BenefitsActivity.class);
                startActivity(intent);
            }
        });

        profileContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a la actividad de Perfil
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}