package com.example.solarsport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class StatisticsActivity extends AppCompatActivity {

    private ImageButton shomeb, scategoriesb, sstatisticsb, sbenefitsb, sprofileb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics_activity);

        shomeb=findViewById(R.id.shomeb);
        scategoriesb=findViewById(R.id.scategoriesb);
        sstatisticsb=findViewById(R.id.sstatisticsb);
        sbenefitsb=findViewById(R.id.sbenefitsb);
        sprofileb=findViewById(R.id.sprofileb);

        shomeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StatisticsActivity.this, MainActivity.class));
            }
        });
        scategoriesb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StatisticsActivity.this, CategoriesActivity.class));
            }
        });
        sstatisticsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StatisticsActivity.this, StatisticsActivity.class));
            }
        });
        sbenefitsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StatisticsActivity.this, BenefitsActivity.class));
            }
        });
        sprofileb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StatisticsActivity.this, ProfileActivity.class));
            }
        });


    }
}

