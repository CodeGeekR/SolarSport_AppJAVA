package com.example.solarsport;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class StatisticsActivity extends AppCompatActivity {

    private TextView tvAverageEnergy, tvMaxEnergy, tvMinEnergy;
    private ArrayList<Integer> energyData; // Lista que contendrá datos de energía para cálculo de estadísticas

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics_activity);

        tvAverageEnergy = findViewById(R.id.tvAverageEnergy);
        tvMaxEnergy = findViewById(R.id.tvMaxEnergy);
        tvMinEnergy = findViewById(R.id.tvMinEnergy);

        // Supongamos que energyData se llena con datos reales de la base de datos o algún servicio
        energyData = new ArrayList<>();
        energyData.add(100);
        energyData.add(200);
        energyData.add(150);
        energyData.add(300);
        energyData.add(250);

        // Calcular estadísticas
        calculateStatistics();
    }

    private void calculateStatistics() {
        if (energyData.isEmpty()) return;

        int sum = 0;
        for (int energy : energyData) {
            sum += energy;
        }

        int average = sum / energyData.size();
        int max = Collections.max(energyData);
        int min = Collections.min(energyData);

        tvAverageEnergy.setText("Promedio de Energía Producida: " + average + " kWh");
        tvMaxEnergy.setText("Máxima Energía Producida: " + max + " kWh");
        tvMinEnergy.setText("Mínima Energía Producida: " + min + " kWh");
    }
}

