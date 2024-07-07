package com.example.solarsport;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private TextView tvCategory;
    private EditText etEnergyProduced, etSavings, etMonth;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        tvCategory = findViewById(R.id.tvCategory);
        etEnergyProduced = findViewById(R.id.etEnergyProduced);
        etSavings = findViewById(R.id.etSavings);
        etMonth = findViewById(R.id.etMonth);
        btnSubmit = findViewById(R.id.btnSubmit);

        String category = getIntent().getStringExtra("category");
        tvCategory.setText(category);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String energyProduced = etEnergyProduced.getText().toString();
                String savings = etSavings.getText().toString();
                String month = etMonth.getText().toString();

                if (!energyProduced.isEmpty() && !savings.isEmpty() && !month.isEmpty()) {
                    // Aquí se debería guardar la información en una base de datos o estructura de datos
                    Toast.makeText(RegisterActivity.this, "Datos registrados correctamente", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
