package com.rajkishorbgp.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.rajkishorbgp.onlineshopping.myclass.LoadData;

import java.util.ArrayList;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText loginEmail, loginPassword;
    TextInputLayout loginEmailLayout, loginPasswordLayout;
    public Button loginButton;
    public TextView registerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        loginEmailLayout = findViewById(R.id.loginEmailLayout); // Initialize loginEmailLayout
        loginPasswordLayout = findViewById(R.id.loginPasswordLayout); // Initialize loginPasswordLayout
        loginButton = findViewById(R.id.loginButton);
        registerText = findViewById(R.id.registerText);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SharedPreferences sharedPreferences = getSharedPreferences("userDetails", 0);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();

        if (sharedPreferences.getBoolean("isLogin", false)) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFill()) {
                    if (databaseHelper.loginUser(Objects.requireNonNull(loginEmail.getText()).toString(), Objects.requireNonNull(loginPassword.getText()).toString())) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        sharedPreferencesEditor.putBoolean("isLogin", true);
                        sharedPreferencesEditor.apply();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "User does not exist", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public Boolean isFill() {
        if (loginEmail.getText() == null || loginEmail.getText().toString().isEmpty()) {
            loginEmailLayout.setError("Error");
            loginEmailLayout.setErrorEnabled(true);
        } else {
            loginEmailLayout.setError(null);
            loginEmailLayout.setErrorEnabled(false);
            if (loginPassword.getText() == null || loginPassword.getText().toString().isEmpty()) {
                loginPasswordLayout.setError("Error");
                loginPasswordLayout.setErrorEnabled(true);
            } else {
                loginPasswordLayout.setError(null);
                loginPasswordLayout.setErrorEnabled(false);
                return true;
            }
        }
        return false;
    }
}