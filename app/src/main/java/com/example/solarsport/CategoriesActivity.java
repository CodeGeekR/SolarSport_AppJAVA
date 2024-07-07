package com.example.solarsport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CategoriesActivity extends AppCompatActivity {

    private Button btnStadiums, btnGyms, btnTracks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_activity);

        btnStadiums = findViewById(R.id.btnStadiums);
        btnGyms = findViewById(R.id.btnGyms);
        btnTracks = findViewById(R.id.btnTracks);

        btnStadiums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoriesActivity.this, RegisterActivity.class);
                intent.putExtra("category", "Estadios");
                startActivity(intent);
            }
        });

        btnGyms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoriesActivity.this, RegisterActivity.class);
                intent.putExtra("category", "Gimnasios");
                startActivity(intent);
            }
        });

        btnTracks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoriesActivity.this, RegisterActivity.class);
                intent.putExtra("category", "Pistas");
                startActivity(intent);
            }
        });
    }
}
