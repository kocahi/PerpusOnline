package com.example.perpusonline;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.perpusonline.database.databaseHelper;

public class MainActivity extends AppCompatActivity {
    private Button btnLogin;
    private EditText etEmail, etPassword;
    private TextView tvRegister;
    databaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = findViewById(R.id.loginBtn);
        etEmail = findViewById(R.id.et_emailLogin);
        etPassword = findViewById(R.id.et_passwordLogin);
        tvRegister = findViewById(R.id.txtV_loginToRegister);
        //insert database
        String email1 = "test";
        String pas1 = "test";
        String ph = "test";
        String dob = "test";
        db = new databaseHelper(this);
        db.insertUserData(email1, pas1, ph, dob);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                //validasi pake database
                Boolean check = db.checkUser(email, password);
                if (check==true){
                    Intent intent = new Intent(MainActivity.this, formMain.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Incorrect Username and Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, registerForm.class);
                startActivity(intent);
            }
        });

    }

    private static String userEmail;

    public String CheckEmail(){
        return userEmail;
    }
}
