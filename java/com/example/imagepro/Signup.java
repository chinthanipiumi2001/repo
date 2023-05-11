package com.example.imagepro;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity {
    EditText username,password,RePassword;
    Button log;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        setContentView(R.layout.activity_signup);
        username = findViewById(R.id.username);
        password = findViewById(R.id.Password);
        RePassword = findViewById(R.id.RePassword);
        log = findViewById(R.id.Loginbtn);
        DB = new DBHelper(this);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String cpass = RePassword.getText().toString();

                if(TextUtils.isEmpty(user)||TextUtils.isEmpty(pass)||TextUtils.isEmpty(cpass)){
                    Toast.makeText(Signup.this, "All feilds are required", Toast.LENGTH_SHORT).show();
                }else{
                    if(pass.equals(cpass)){
                        Boolean checkUser = DB.checkUsrename(user);
                        if(checkUser == false){
                            Boolean insert = DB.insertData(user,pass);
                            if(insert == true){
                                Toast.makeText(Signup.this, "Registered Successfully", Toast.LENGTH_SHORT).show();//get a massage for user
                                Intent intent = new Intent(getApplicationContext(),Login.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(Signup.this, "Registeration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(Signup.this, "User already exists", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Signup.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signup.this,Login.class));
            }
        });
    }
}