package com.example.solarsport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class CategoriesActivity extends AppCompatActivity {

    private ImageButton chomeb, ccategoriesb, cstatisticsb, cbenefitsb, cprofileb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_activity);

        chomeb=findViewById(R.id.chomeb);
        ccategoriesb=findViewById(R.id.ccategoriesb);
        cstatisticsb=findViewById(R.id.cstatisticsb);
        cbenefitsb=findViewById(R.id.cbenefitsb);
        cprofileb=findViewById(R.id.cprofileb);

        chomeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CategoriesActivity.this, MainActivity.class));
            }
        });
        ccategoriesb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CategoriesActivity.this, CategoriesActivity.class));
            }
        });
        cstatisticsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CategoriesActivity.this, StatisticsActivity.class));
            }
        });
        cbenefitsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CategoriesActivity.this, BenefitsActivity.class));
            }
        });
        cprofileb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CategoriesActivity.this, ProfileActivity.class));
            }
        });


    }
}
