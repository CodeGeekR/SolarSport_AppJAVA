package com.example.solarsport;

import android.content.Context;
import android.content.SharedPreferences;

public class UserManager {
    //Crear archivos en el preferencias compartidas
    private static final String PREF_NAME = "UserPrefs";

    //Claves que se usan para recuperar datos
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

    //Las variables que vamos a instanciar
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    //Constructor
    public UserManager(Context context){
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        //Obtener editor para modificar los datos
        editor = sharedPreferences.edit();
    }

    public void RegisterUser(String email, String password){
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, password);

        editor.apply();
    }

    public boolean loginUser(String email, String password){
        String RegisteredEmail = sharedPreferences.getString(KEY_EMAIL, null);
        String RegisteredPassword = sharedPreferences.getString(KEY_PASSWORD, null);
        return email.equals(RegisteredEmail) && password.equals(RegisteredPassword);
    }

}
