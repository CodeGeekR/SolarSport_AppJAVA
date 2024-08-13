package com.example.solarsport;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;

public class ProfileUserActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextPhone, editTextAddress, editTextBYear;
    private Button btnsaveProfile, btnCalaculateAge;
    private TextInputLayout inputLayout;
    private TextView tvage;
    private MaterialAutoCompleteTextView inputTV;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextAddress = findViewById(R.id.editTextAddress);
        btnsaveProfile = findViewById(R.id.btnsaveProfile);
        inputLayout = findViewById(R.id.inputLayout);
        inputTV = findViewById(R.id.inputTV);
        editTextBYear = findViewById(R.id.editTextBYear);
        tvage = findViewById(R.id.tvage);
        btnCalaculateAge = findViewById(R.id.btnCalculateAge);

        // Inicialización del helper de base de datos
        dbHelper = new DatabaseHelper(this);

        btnsaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputTV.getText().toString().isEmpty()){
                    inputLayout.setError("Por favor seleccione una opcion");
                } else {
                    Toast.makeText(ProfileUserActivity.this, inputTV.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}