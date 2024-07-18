package com.example.solarsport;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RegisterUserActivity extends AppCompatActivity {

    EditText etFullName, etEmail, etPassword, etConfirmPassword;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_user);

        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void registerUser() {
        String fullName = etFullName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();
        String hashedPassword = hashPassword(password);

        // Validations...
        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        new Thread(() -> {
            try {
                String dbUrl = "jdbc:mysql://";
                Connection connection = DriverManager.getConnection(dbUrl);

                String sql = "INSERT INTO Usuario (username, email, password_hash, first_name, last_name) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);

                String[] names = fullName.split(" ", 2);
                String firstName = names[0];
                String lastName = names.length > 1 ? names[1] : "";

                statement.setString(1, email);
                statement.setString(2, email);
                statement.setString(3, hashedPassword);
                statement.setString(4, firstName);
                statement.setString(5, lastName);

                int rowsInserted = statement.executeUpdate();

                runOnUiThread(() -> {
                    if (rowsInserted > 0) {
                        Toast.makeText(RegisterUserActivity.this, "¡Registro exitoso!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(RegisterUserActivity.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                    }
                });

                connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
                runOnUiThread(() -> {
                    Toast.makeText(RegisterUserActivity.this, "Error de conexión a la base de datos", Toast.LENGTH_SHORT).show();
                });
            }
        }).start();
    }
}