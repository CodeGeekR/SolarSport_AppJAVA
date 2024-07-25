package com.example.solarsport;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class RegisterUserActivity extends AppCompatActivity {

    // Declaración de variables para los elementos de la interfaz de usuario
    private TextInputEditText editTextEmail, editTextPassword, editTextConfirmPassword, editTextFullName;
    private MaterialCheckBox checkBoxTerms;
    private MaterialButton buttonRegister;
    private TextView btnLogin;

    // Instancia del helper de base de datos
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_user);

        // Inicialización de los elementos de la interfaz de usuario
        editTextFullName = findViewById(R.id.etFullName);
        editTextEmail = findViewById(R.id.etEmail);
        editTextPassword = findViewById(R.id.etPassword);
        editTextConfirmPassword = findViewById(R.id.etConfirmPassword);
        checkBoxTerms = findViewById(R.id.cbAcceptTerms);
        buttonRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);

        // Inicialización del helper de base de datos
        dbHelper = new DatabaseHelper(this);

        // Configuración del listener para el botón de registro
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        // Configuración del listener para el botón de login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterUserActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Método para registrar un nuevo usuario.
     */
    private void registerUser() {
        // Obtener los valores ingresados por el usuario
        String fullName = editTextFullName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString();
        String confirmPassword = editTextConfirmPassword.getText().toString();

        // Validar que el nombre completo no esté vacío
        if (TextUtils.isEmpty(fullName)) {
            Toast.makeText(this, "Please enter your full name", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar que el email no esté vacío y sea válido
        if (TextUtils.isEmpty(email) || !isValidEmail(email)) {
            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar que la contraseña no esté vacía y tenga al menos 6 caracteres
        if (TextUtils.isEmpty(password) || password.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar que las contraseñas coincidan
        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar que los términos y condiciones estén aceptados
        if (!checkBoxTerms.isChecked()) {
            Toast.makeText(this, "You must accept the terms and conditions", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            // Generar un salt y hashear la contraseña
            String salt = generateSalt();
            String hashedPassword = hashPassword(password, salt);

            // Insertar el nuevo usuario en la base de datos
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("full_name", fullName);
            values.put("email", email);
            values.put("password", hashedPassword);
            values.put("salt", salt);

            long newRowId = db.insert("users", null, values);
            db.close();

            // Verificar si la inserción fue exitosa
            if (newRowId != -1) {
                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterUserActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            Toast.makeText(this, "An error occurred during registration", Toast.LENGTH_SHORT).show();
        }
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
     * Método para generar un salt aleatorio.
     *
     * @return Salt generado.
     * @throws NoSuchAlgorithmException Si el algoritmo no está disponible.
     */
    private String generateSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
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