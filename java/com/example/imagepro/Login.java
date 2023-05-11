package com.example.imagepro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText username ,password;
        Button login_btn;
        DBHelper DB;
        Button btn;

        username = findViewById(R.id.Username);
        password = findViewById(R.id.Password);
        login_btn = findViewById(R.id.login);
        DB = new DBHelper(this);
        btn=findViewById(R.id.Signup);

        login_btn.setOnClickListener(view ->{

            String user,pass;

            user = username.getText().toString();
            pass = password.getText().toString();

            Boolean checkUserPass = DB.checkUsernamesPasswords(user,pass);
            if(checkUserPass == true){
                    Toast.makeText(this,"Login Successfull!",Toast.LENGTH_SHORT).show(); // get a massage for user
                    Intent intent = new Intent(Login.this,MainActivity.class);
                    startActivity(intent);
                }else {
                Toast.makeText(this, "Login Faild !", Toast.LENGTH_SHORT).show();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Signup.class));
            }
        });
    }
}