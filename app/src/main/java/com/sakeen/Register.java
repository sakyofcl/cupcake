package com.sakeen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sakeen.DAL.Model.User;
import com.sakeen.DAL.UnitOfWork;

public class Register extends AppCompatActivity {
    EditText userName, password, cPassword, phone, address;
    Button register_btn;
    TextView loginBtnRegView;
    UnitOfWork uow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        uow = new UnitOfWork(this);

        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        cPassword = findViewById(R.id.cPassword);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        register_btn = findViewById(R.id.login_btn);
        loginBtnRegView = findViewById(R.id.loginBtnRegView);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isValidate = validateFeilds();

                if (isValidate) {
                    User newUser = new User();

                    newUser.address = address.getText().toString();
                    newUser.name = userName.getText().toString();
                    newUser.phone =  Integer.parseInt(phone.getText().toString());
                    newUser.userType = 0;
                    newUser.password = password.getText().toString();

                    uow.user.AddUser(newUser);

                    Intent i = new Intent(Register.this, Login.class);
                    startActivity(i);
                    finish();
                }

            }
        });

        loginBtnRegView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
                finish();
            }
        });



    }

    private boolean validateFeilds(){
        String requiredMessage = "This field is required";
        if (userName.length() == 0) {
            userName.setError(requiredMessage);
            return false;
        }
        if (password.length() == 0) {
            password.setError(requiredMessage);
            return false;
        }
        if (cPassword.length() == 0) {
            cPassword.setError(requiredMessage);
            return false;
        }
        if (phone.length() == 0) {
            phone.setError(requiredMessage);
            return false;
        }
        if (address.length() == 0) {
            address.setError(requiredMessage);
            return false;
        }

        if(!password.getText().toString().equals(cPassword.getText().toString())){
            cPassword.setError("Confirm password not matched.");
            return false;
        }

        if(phone.getText().toString().length() != 10){
            phone.setError("Invalid phone number.");
            return false;
        }

        return true;
    }
}