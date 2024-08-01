// LoginActivity.java
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

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class LoginActivity extends AppCompatActivity {

    // Declaración de variables para los elementos de la interfaz de usuario
    private EditText etEmail, etPassword;
    private MaterialButton btnLogin;
    private View btnRegister;

    // Instancia del helper de base de datos
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicialización de los elementos de la interfaz de usuario
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        // Inicialización del helper de base de datos
        dbHelper = new DatabaseHelper(this);

        // Configuración del listener para el botón de login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString();

                // Validar que el email no esté vacío y sea válido
                if (TextUtils.isEmpty(email) || !isValidEmail(email)) {
                    Toast.makeText(LoginActivity.this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validar que la contraseña no esté vacía
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Verificar las credenciales del usuario
                int userId = checkUserCredentials(email, password);
                if (userId != -1) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("USER_ID", userId);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Configuración del listener para el botón de registro
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterUserActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Método para validar si un email es válido.
     *
     * @param target Email a validar.
     * @return true si el email es válido, false en caso contrario.
     */
    private boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    /**
     * Método para verificar las credenciales del usuario.
     *
     * @param email Email del usuario.
     * @param password Contraseña del usuario.
     * @return ID del usuario si las credenciales son válidas, -1 en caso contrario.
     */
    private int checkUserCredentials(String email, String password) {
        // Obtener una instancia de la base de datos en modo lectura
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = { "id", "password", "salt" };
        String selection = "email = ?";
        String[] selectionArgs = { email };

        // Consultar la base de datos para obtener las credenciales del usuario
        Cursor cursor = db.query("users", columns, selection, selectionArgs, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex("id");
            int passwordIndex = cursor.getColumnIndex("password");
            int saltIndex = cursor.getColumnIndex("salt");

            // Verificar que los índices de las columnas sean válidos
            if (idIndex != -1 && passwordIndex != -1 && saltIndex != -1) {
                String storedPassword = cursor.getString(passwordIndex);
                String storedSalt = cursor.getString(saltIndex);
                int userId = cursor.getInt(idIndex);

                try {
                    String hashedPassword = hashPassword(password, storedSalt);
                    if (storedPassword.equals(hashedPassword)) {
                        cursor.close();
                        db.close();
                        return userId;
                    }
                } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                    e.printStackTrace();
                }
            }
            cursor.close();
        }

        db.close();
        return -1;
    }

    /**
     * Método para hashear una contraseña usando PBKDF2 con HmacSHA512.
     *
     * @param password Contraseña a hashear.
     * @param salt Salt a usar.
     * @return Contraseña hasheada.
     * @throws NoSuchAlgorithmException Si el algoritmo no está disponible.
     * @throws InvalidKeySpecException Si la especificación de la clave es inválida.
     */
    private String hashPassword(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), Base64.getDecoder().decode(salt), 10000, 512);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return Base64.getEncoder().encodeToString(hash);
    }
}