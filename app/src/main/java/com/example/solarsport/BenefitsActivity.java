package com.example.solarsport;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BenefitsActivity extends AppCompatActivity {

    private TextView tvBenefitsDetails;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.benefits_activity);

        tvBenefitsDetails = findViewById(R.id.tvBenefitsDetails);

        // Supongamos que se obtienen los detalles de los beneficios de una base de datos o servicio
        String benefitsDetails = "Ahorro total: $500,000 COP\nRecomendación: Invertir en baterías de almacenamiento.";

        tvBenefitsDetails.setText(benefitsDetails);
    }
}
