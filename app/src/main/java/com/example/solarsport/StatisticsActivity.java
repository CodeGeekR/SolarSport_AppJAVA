package com.example.solarsport;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StatisticsActivity extends AppCompatActivity {

    private ImageButton shomeb, scategoriesb, sstatisticsb, sbenefitsb, sprofileb;
    private int userId;
    private String fullName;
    private String selectedCategory, selectedSportSupply;
    private TextView textKW, textMonth, textMin, textMax, textAverage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics_activity);

        // Obtener el ID y el nombre completo del usuario desde el Intent
        userId = getIntent().getIntExtra("USER_ID", -1);
        fullName = getIntent().getStringExtra("FULL_NAME");
        selectedCategory = getIntent().getStringExtra("SELECTED_CATEGORY");
        selectedSportSupply = getIntent().getStringExtra("SELECTED_SPORT_SUPPLY");

        textKW = findViewById(R.id.TextKW);
        textMonth = findViewById(R.id.TextMonth);
        textMin = findViewById(R.id.textMin);
        textMax = findViewById(R.id.textMax);
        textAverage = findViewById(R.id.textAverage);

        // Initialize navigation buttons
        shomeb = findViewById(R.id.shomeb);
        scategoriesb = findViewById(R.id.scategoriesb);
        sstatisticsb = findViewById(R.id.sstatisticsb);
        sbenefitsb = findViewById(R.id.sbenefitsb);
        sprofileb = findViewById(R.id.sprofileb);

        // Set onClickListeners for navigation buttons
        shomeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatisticsActivity.this, MainActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("FULL_NAME", fullName);
                startActivity(intent);
            }
        });
        scategoriesb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatisticsActivity.this, CategoriesActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("FULL_NAME", fullName);
                startActivity(intent);
            }
        });
        sstatisticsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatisticsActivity.this, StatisticsActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("FULL_NAME", fullName);
                startActivity(intent);
            }
        });
        sbenefitsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatisticsActivity.this, BenefitsActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("FULL_NAME", fullName);
                startActivity(intent);
            }
        });
        sprofileb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatisticsActivity.this, ProfileActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("FULL_NAME", fullName);
                startActivity(intent);
            }
        });

        // Fetch statistics data
        fetchStatisticsData();
    }

    private void fetchStatisticsData() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query("solar_data",
                new String[]{"energy_produced", "month"},
                "user_id=? AND selected_category=? AND selected_sport_supply=?",
                new String[]{String.valueOf(userId), selectedCategory, selectedSportSupply},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            double totalEnergy = 0;
            double minEnergy = Double.MAX_VALUE;
            double maxEnergy = Double.MIN_VALUE;
            int count = 0;
            String month = "";

            do {
                int energyIndex = cursor.getColumnIndex("energy_produced");
                int monthIndex = cursor.getColumnIndex("month");

                if (energyIndex != -1 && monthIndex != -1) {
                    double energy = cursor.getDouble(energyIndex);
                    month = cursor.getString(monthIndex);
                    totalEnergy += energy;
                    if (energy < minEnergy) minEnergy = energy;
                    if (energy > maxEnergy) maxEnergy = energy;
                    count++;
                }
            } while (cursor.moveToNext());

            if (count > 0) {
                double averageEnergy = totalEnergy / count;

                textKW.setText(String.format("%.2f kWh", totalEnergy));
                textMonth.setText(month);
                textMin.setText(String.format("%.2f kWh", minEnergy));
                textMax.setText(String.format("%.2f kWh", maxEnergy));
                textAverage.setText(String.format("%.2f kWh", averageEnergy));
            } else {
                Toast.makeText(this, "No data found.", Toast.LENGTH_SHORT).show();
            }

            cursor.close();
        } else {
            Toast.makeText(this, "No data found.", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }
}