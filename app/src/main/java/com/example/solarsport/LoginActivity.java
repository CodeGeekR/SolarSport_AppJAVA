package com.example.solarsport;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private MaterialButton btnLogin;
    private View btnRegister;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        // Inicializamos la base de datos
        dbHelper = new DatabaseHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                // Validar los campos de entrada
                if (TextUtils.isEmpty(email) || !isValidEmail(email)) {
                    Toast.makeText(LoginActivity.this, "Por favor, ingrese un email válido", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "Por favor, ingrese su contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Verificar las credenciales del usuario
                if (checkUserCredentials(email, password)) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterUserActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Verifica si el email proporcionado tiene un formato válido.
     *
     * @param target Email a verificar
     * @return true si el email es válido, false de lo contrario
     */
    private boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    /**
     * Verifica las credenciales del usuario en la base de datos.
     *
     * @param email    Email del usuario
     * @param password Contraseña del usuario
     * @return true si las credenciales son correctas, false de lo contrario
     */
    private boolean checkUserCredentials(String email, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Consulta para verificar las credenciales del usuario
        String[] columns = { "id" };
        String selection = "email = ? AND password = ?";
        String[] selectionArgs = { email, password };

        Cursor cursor = db.query("users", columns, selection, selectionArgs, null, null, null);
        boolean isValid = cursor.getCount() > 0;
        cursor.close();
        db.close();

        return isValid;
    }
}
