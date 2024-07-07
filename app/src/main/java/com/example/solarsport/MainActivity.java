package com.example.solarsport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnCategories, btnStatistics, btnBenefits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCategories = findViewById(R.id.btnCategories);
        btnStatistics = findViewById(R.id.btnStatistics);
        btnBenefits = findViewById(R.id.btnBenefits);

        btnCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
                startActivity(intent);
            }
        });

        btnStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StatisticsActivity.class);
                startActivity(intent);
            }
        });

        btnBenefits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BenefitsActivity.class);
                startActivity(intent);
            }
        });
    }
}
