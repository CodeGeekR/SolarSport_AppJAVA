package com.example.solarsport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private MaterialButton btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        // Para el registro, con un TextView como botón de registro
//        btnRegister = findViewById(R.id.btnRegister);

        // Listener para el botón de inicio de sesión
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesion();
            }
        });

        // Listener para el botón de registro
//        btnRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Navegar a RegisterActivity
//                Intent intent = new Intent(LoginActivity.this, RegisterUserActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    /**
     * Valida las credenciales de inicio de sesión y navega a MainActivity si son correctas.
     */
    private void iniciarSesion() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        // Validación de credenciales (este ejemplo usa valores estáticos para demostración)
        if (email.equals("admin@solarsport.com") && password.equals("1234")) {
            // Navegar a MainActivity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            // Mostrar mensaje de error
            Toast.makeText(LoginActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
        }
    }
}