package com.example.solarsport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterUserActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword, editTextConfirmPassword, editTextFullName;
    private CheckBox checkBoxTerms;
    private Button buttonRegister;
    private TextView btnLogin; // TextView for login redirection
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_user);

        editTextFullName = findViewById(R.id.etFullName);
        editTextEmail = findViewById(R.id.etEmail);
        editTextPassword = findViewById(R.id.etPassword);
        editTextConfirmPassword = findViewById(R.id.etConfirmPassword);
        checkBoxTerms = findViewById(R.id.cbAcceptTerms);
        buttonRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin); // Initialize the TextView for login

        userManager = new UserManager(this);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();


                if(TextUtils.isEmpty(email)){
                    Toast.makeText(RegisterUserActivity.this, "Ingrese un correo electronico", Toast.LENGTH_SHORT).show();

                } else if(!isValidEmail(email)){
                    Toast.makeText(RegisterUserActivity.this, "Ingrese un correo electronico valido", Toast.LENGTH_SHORT).show();

                } else if(TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterUserActivity.this, "Ingrese una contraseña", Toast.LENGTH_SHORT).show();

                } else if(!checkBoxTerms.isChecked()){
                    Toast.makeText(RegisterUserActivity.this, "Debe aceptar terminos y condiciones", Toast.LENGTH_SHORT).show();

                } else{
                    registrarUsuario(email, password);
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterUserActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private boolean isValidEmail(CharSequence target){
        return(!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    private void registrarUsuario(String email, String password){
        userManager.RegisterUser(email, password);
        Toast.makeText(RegisterUserActivity.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
        finish();
    }


}


