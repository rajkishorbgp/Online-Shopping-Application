package com.rajkishorbgp.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
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

import java.util.Objects;

public class RegistrationActivity extends AppCompatActivity {
    TextInputEditText  regName, regEmail, regPassword, regConformPassword;
    TextInputLayout regNameLayout,regPasswordLayout,regConformPasswordLayout,regEmailLayout;
    Button registrationButton;
    TextView loginText;
    DatabaseHelper databaseHelper;

    Boolean flog = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.black));
        regName = findViewById(R.id.regName);
        regEmail = findViewById(R.id.regEmail);
        regPassword = findViewById(R.id.regPassword);
        regConformPassword = findViewById(R.id.regConformPassword);
        registrationButton = findViewById(R.id.registerButton);
        loginText = findViewById(R.id.loginText);
        regNameLayout = findViewById(R.id.regNameLayout);
        regPasswordLayout = findViewById(R.id.regPasswordLayout);
        regConformPasswordLayout = findViewById(R.id.regConformPasswordLayout);
        regEmailLayout = findViewById(R.id.regEmailLayout);


        databaseHelper = new DatabaseHelper(this);
        SharedPreferences sharedPreferences= getSharedPreferences("userDetails",0);
        SharedPreferences.Editor sharedPreferencesEditor =sharedPreferences.edit();

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFill()) {
                    flog = databaseHelper.registrationUser(Objects.requireNonNull(regName.getText()).toString(), Objects.requireNonNull(regEmail.getText()).toString(), Objects.requireNonNull(regPassword.getText()).toString());
                    if (flog) {
                        Toast.makeText(getApplicationContext(), "User Successfully Registration", Toast.LENGTH_LONG).show();
                        LoadData loadData = new LoadData(getApplicationContext());
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        sharedPreferencesEditor.putString("name", regName.getText().toString());
                        sharedPreferencesEditor.putString("email", regEmail.getText().toString());
                        sharedPreferencesEditor.apply();
                        finish();
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "User not Successfully Registration!!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    public Boolean isFill(){
        if (regName.getText() == null || regName.getText().toString().isEmpty()) {
            regNameLayout.setError("Error");
            regNameLayout.setErrorEnabled(true);
        } else {
            regNameLayout.setError(null);
            regNameLayout.setErrorEnabled(false);
            if (regEmail.getText() == null || regEmail.getText().toString().isEmpty()) {
                regEmailLayout.setError("Error");
                regEmailLayout.setErrorEnabled(true);
            } else {
                regEmailLayout.setError(null);
                regEmailLayout.setErrorEnabled(false);
                if (regPassword.getText() == null || regPassword.getText().toString().isEmpty()) {
                    regPasswordLayout.setError("Error");
                    regPasswordLayout.setErrorEnabled(true);
                } else {
                    regPasswordLayout.setError(null);
                    regPasswordLayout.setErrorEnabled(false);
                    if (regConformPassword.getText() == null || regConformPassword.getText().toString().isEmpty()) {
                        regConformPasswordLayout.setError("Error");
                        regConformPasswordLayout.setErrorEnabled(true);
                    } else {
                        if (regPassword.getText().toString().equals(regConformPassword.getText().toString())) {
                            regConformPasswordLayout.setError(null);
                            regConformPasswordLayout.setErrorEnabled(false);
                            return true;
                        } else {
                            regConformPasswordLayout.setError("Passwords don't match");
                            regConformPasswordLayout.setErrorEnabled(true);
                        }
                    }
                }
            }
        }
        return false;
    }
}