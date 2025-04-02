package com.rajkishorbgp.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.rajkishorbgp.onlineshopping.fragment.AccountInformationFragment;
import com.rajkishorbgp.onlineshopping.fragment.HomeFragment;

public class EditActivity extends AppCompatActivity {
    TextInputEditText nameEdit,mobileEdit,addressEdit,editDOB;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.black));

        nameEdit = findViewById(R.id.nameEdit);
        mobileEdit = findViewById(R.id.mobileEdit);
        addressEdit = findViewById(R.id.addressEdit);
        editDOB = findViewById(R.id.editDOB);
        saveButton = findViewById(R.id.saveButton);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SharedPreferences sharedPreferences= getSharedPreferences("userDetails",0);
        String email = sharedPreferences.getString("email","null");

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.updateDetailes(email,nameEdit.getText().toString(),mobileEdit.getText().toString(),addressEdit.getText().toString(),editDOB.getText().toString());
                Toast.makeText(getApplicationContext(),"saved",Toast.LENGTH_SHORT).show();
                Fragment accountInformationFragment = new AccountInformationFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment, accountInformationFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
}