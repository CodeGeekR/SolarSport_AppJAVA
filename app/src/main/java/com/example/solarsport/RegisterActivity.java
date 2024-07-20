package com.example.solarsport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {


    private ImageButton rhomeb, rcategoriesb, rstatisticsb, rbenefitsb, rprofileb;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        btnNext = findViewById(R.id.btnNext);
        rhomeb = findViewById(R.id.rhomeb);
        rcategoriesb = findViewById(R.id.rcategoriesb);
        rstatisticsb = findViewById(R.id.rstatisticsb);
        rbenefitsb = findViewById(R.id.rbenefitsb);
        rprofileb = findViewById(R.id.rprofileb);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, StatisticsActivity.class));
            }
        });

        rhomeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });

        rcategoriesb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, CategoriesActivity.class));
            }
        });

        rstatisticsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, StatisticsActivity.class));
            }
        });

        rbenefitsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, BenefitsActivity.class));
            }
        });
        rprofileb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });






    }
}
