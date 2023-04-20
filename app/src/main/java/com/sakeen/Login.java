package com.sakeen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sakeen.DAL.Model.User;
import com.sakeen.DAL.UnitOfWork;

public class Login extends AppCompatActivity {
    TextView loginBtnRegView;
    EditText userName, password;
    Button login_btn;
    UnitOfWork uow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppContext _app = new AppContext(getSharedPreferences(AppContext.name,MODE_PRIVATE));

        if(_app.isLogin()){
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }

        uow = new UnitOfWork(this);

        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        loginBtnRegView = findViewById(R.id.loginBtnRegView);
        login_btn = findViewById(R.id.login_btn);



        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isValidate = validateFeilds();

                if (isValidate) {
                    User auth = new User();
                    auth.name = userName.getText().toString();
                    auth.password = password.getText().toString();
                    auth = uow.user.login(auth);

                    if(auth != null){
                        _app.setIsAdmin(auth.userType == 1);
                        _app.setIsLogin(true);
                        _app.setUserName(auth.name);
                        _app.setUserId(auth.userId);
                        _app.save();
                        startActivity(new Intent(Login.this, MainActivity.class));
                        finish();
                    }
                    else{
                        Toast.makeText(Login.this, "userName And Password wrong", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


        loginBtnRegView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
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
        return true;
    }


}