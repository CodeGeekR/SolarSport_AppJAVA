package com.example.solarsport;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private Button btnSave, btnStatistics;
    private ImageButton rhomeb, rcategoriesb, rstatisticsb, rbenefitsb, rprofileb;
    private int userId;
    private String fullName;
    private String selectedCategory, selectedSportSupply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        // Obtener el ID y el nombre completo del usuario desde el Intent
        userId = getIntent().getIntExtra("USER_ID", -1);
        fullName = getIntent().getStringExtra("FULL_NAME");

        // Obtener los textos seleccionados desde el Intent
        selectedCategory = getIntent().getStringExtra("SELECTED_CATEGORY");
        selectedSportSupply = getIntent().getStringExtra("SELECTED_SPORT_SUPPLY");

        btnSave = findViewById(R.id.btnSave);
        btnStatistics = findViewById(R.id.btnStatistics);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSolarDataToDatabase();
            }
        });

        btnStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, StatisticsActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("FULL_NAME", fullName);
                intent.putExtra("SELECTED_CATEGORY", selectedCategory);
                intent.putExtra("SELECTED_SPORT_SUPPLY", selectedSportSupply);
                startActivity(intent);
            }
        });

        // Initialize navigation buttons
        rhomeb = findViewById(R.id.rhomeb);
        rcategoriesb = findViewById(R.id.rcategoriesb);
        rstatisticsb = findViewById(R.id.rstatisticsb);
        rbenefitsb = findViewById(R.id.rbenefitsb);
        rprofileb = findViewById(R.id.rprofileb);

        // Set onClickListeners for navigation buttons
        rhomeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("FULL_NAME", fullName);
                startActivity(intent);
            }
        });
        rcategoriesb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, CategoriesActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("FULL_NAME", fullName);
                startActivity(intent);
            }
        });
        rstatisticsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, StatisticsActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("FULL_NAME", fullName);
                startActivity(intent);
            }
        });
        rbenefitsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, BenefitsActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("FULL_NAME", fullName);
                startActivity(intent);
            }
        });
        rprofileb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, ProfileActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("FULL_NAME", fullName);
                startActivity(intent);
            }
        });
    }

    private void saveSolarDataToDatabase() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Spinner numPanelsSpinner = findViewById(R.id.num_panels_spinner);
        EditText etEnergyProduced = findViewById(R.id.etEnergyProduced);
        EditText etSavings = findViewById(R.id.etSavings);
        Spinner etMonth = findViewById(R.id.etMonth);

        int numPanels = Integer.parseInt(numPanelsSpinner.getSelectedItem().toString());
        double energyProduced = Double.parseDouble(etEnergyProduced.getText().toString());
        double savings = Double.parseDouble(etSavings.getText().toString());
        String month = etMonth.getSelectedItem().toString();

        ContentValues values = new ContentValues();
        values.put("user_id", userId);
        values.put("num_panels", numPanels);
        values.put("energy_produced", energyProduced);
        values.put("savings", savings);
        values.put("month", month);
        values.put("selected_category", selectedCategory);
        values.put("selected_sport_supply", selectedSportSupply);

        long newRowId = db.insert("solar_data", null, values);
        if (newRowId != -1) {
            Toast.makeText(this, "Solar data saved successfully!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error saving solar data.", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }
}