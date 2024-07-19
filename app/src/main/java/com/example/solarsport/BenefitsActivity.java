package com.example.solarsport;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BenefitsActivity extends AppCompatActivity {

    private ImageButton bhomeb;
    private ImageButton bcategoriesb;
    private ImageButton bstatisticsb;
    private ImageButton bbenefitsb;
    private ImageButton bprofileb;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.benefits_activity);

        bhomeb=findViewById(R.id.bhomeb);
        bcategoriesb=findViewById(R.id.bcategoriesb);
        bstatisticsb=findViewById(R.id.bstatisticsb);
        bbenefitsb=findViewById(R.id.bbenefitsb);
        bprofileb=findViewById(R.id.bprofileb);

        bhomeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BenefitsActivity.this, MainActivity.class));
            }
        });
        bcategoriesb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BenefitsActivity.this, CategoriesActivity.class));
            }
        });
        bstatisticsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BenefitsActivity.this, StatisticsActivity.class));
            }
        });
        bbenefitsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BenefitsActivity.this, BenefitsActivity.class));
            }
        });
        bprofileb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BenefitsActivity.this, ProfileActivity.class));
            }
        });


    }
}
