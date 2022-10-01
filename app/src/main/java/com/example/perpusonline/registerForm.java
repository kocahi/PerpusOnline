package com.example.perpusonline;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.perpusonline.database.databaseHelper;

public class registerForm extends AppCompatActivity {
    private EditText etEmail, etPassword, etPhoneNumber;
    DatePickerDialog.OnDateSetListener dateSetListener;
    int year, month, day, ageValidate;
    databaseHelper db;
    private Button btnSubmitRegister;
    private CheckBox chkBxTerms;
    private TextView tvDatePick;
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 1: return new DatePickerDialog(this, dateSetListener, year, month, day);
        }
        return super.onCreateDialog(id);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);
        db = new databaseHelper(this);
        tvDatePick = findViewById(R.id.datePickerTxtView);
        year = 2000;
        month = 0;
        day = 1;
        tvDatePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(1);
            }
        });
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int ii, int i1, int i2) {
                String tanggal = Integer.toString(ii) + Integer.toString(i1) + Integer.toString(i2); //20020202
                etEmail = findViewById(R.id.et_emailRegister);
                etPassword = findViewById(R.id.et_passwordRegister);
                etPhoneNumber = findViewById(R.id.et_phoneNumberRegister);
                btnSubmitRegister = findViewById(R.id.SubmitRegister);
                chkBxTerms = findViewById(R.id.agreeChkBx);
                btnSubmitRegister.setOnClickListener(view -> {
                    String email = etEmail.getText().toString();
                    String password = etPassword.getText().toString();
                    String phoneNumber = etPhoneNumber.getText().toString();
                    System.out.println("age"+ageValidate);

                    boolean alfabet = false; boolean numeric = false;
                    for (int i = 0; i < password.length(); i++) {
                        if(Character.isDigit(password.charAt(i))) {
                            numeric = true;
                        }
                        if(Character.isAlphabetic(password.charAt(i))) {
                            alfabet = true;
                        }
                    }
                    //All field must filled
                    if (email.isEmpty()){
                        Toast.makeText(registerForm.this,"Email must be filled", Toast.LENGTH_SHORT).show();
                    }
                    else if (password.isEmpty()){
                        Toast.makeText(registerForm.this,"Password must be filled", Toast.LENGTH_SHORT).show();
                    }
                    else if (phoneNumber.isEmpty()){
                        Toast.makeText(registerForm.this,"Phone must be filled", Toast.LENGTH_SHORT).show();
                    }
                    //Password More than 8 characters
                    else if (password.length() < 8){
                        Toast.makeText(registerForm.this,"Password must more than 8 characters", Toast.LENGTH_SHORT).show();
                    }
                    //at least 1 digit and 1 letter
                    else if(!alfabet == true){
                        Toast.makeText(registerForm.this,"Password must be alphanumeric", Toast.LENGTH_SHORT).show();
                    }
                    else if(!numeric == true){
                        Toast.makeText(registerForm.this,"Password must be alphanumeric", Toast.LENGTH_SHORT).show();
                    }
                    //age at least 13 years old
                    else if (ii > 2009){
                        Toast.makeText(registerForm.this,"Age must more than 13 years old", Toast.LENGTH_SHORT).show();
                    }
                    //must start with +62
                    else if (!phoneNumber.startsWith("+62")){
                        Toast.makeText(registerForm.this,"Phone number must start with +62", Toast.LENGTH_SHORT).show();
                    }
                    //phone numbers 10-15 digits
                    else if(phoneNumber.length() < 10 || phoneNumber.length() > 15){
                        Toast.makeText(registerForm.this,"Phone number must less than 10 or more than 15 characters", Toast.LENGTH_SHORT).show();
                    }
                    //chkbx checked
                    else if(!chkBxTerms.isChecked()){
                        Toast.makeText(registerForm.this,"Check the box first", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        db.insertUserData(email, password, phoneNumber, tanggal);
                        Intent intent = new Intent(registerForm.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
            }
        };
    }


}