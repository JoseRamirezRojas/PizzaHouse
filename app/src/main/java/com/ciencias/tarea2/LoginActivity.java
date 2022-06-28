package com.ciencias.tarea2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private String USERNAME_TOAST = "EL nombre de usuario debe tener minimo 4 caracteres";
    private String PASSWORD_TOAST = "La contraseña debe tener minimo 6 caracteres";
    private String USER_TOAST = "Usuario o contraseña incorrectos";
    private String USER_ID = "USER_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        startLoginButton();
        startSignupButtons();

    }

    private void startLoginButton(){
        Button login_button = (Button) findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name = (EditText) findViewById(R.id.user_name);
                EditText password = (EditText) findViewById(R.id.password);
                boolean isValid = validateData(name.getText().toString(), password.getText().toString());
                long userId = -1;
                if(isValid){
                    userId = getUser(name.getText().toString(), password.getText().toString());
                    if(userId > 0){
                        Intent home = new Intent(LoginActivity.this, MainActivity.class);
                        home.putExtra(USER_ID, userId);
                        startActivity(home);
                    }
                }

            }
        });
    }

    private long getUser(String username, String password){
        ManagerBDD managerBdd = new ManagerBDD(LoginActivity.this);
        managerBdd.openForRead();
        User user = managerBdd.getUserByName(username);
        if(user != null){
            if (!password.equals(user.getPassword())){
                Toast toastName = Toast.makeText(getApplicationContext(),
                        USER_TOAST, Toast.LENGTH_SHORT);
                toastName.show();
            } else {
                return user.getId();
            }
            ;
        } else {
            Toast toastName = Toast.makeText(getApplicationContext(),
                    USER_TOAST, Toast.LENGTH_SHORT);
            toastName.show();
        }

        managerBdd.close();

        return -1;
    }


    private boolean validateData(String username, String password){
        if(username.length() < 4){
            Toast toastName = Toast.makeText(getApplicationContext(),
                    USERNAME_TOAST, Toast.LENGTH_SHORT);
            toastName.show();
            return false;
        } else if(password.length() < 6){
            Toast toastName = Toast.makeText(getApplicationContext(),
                    PASSWORD_TOAST, Toast.LENGTH_SHORT);
            toastName.show();
            return false;
        }
        return true;
    }

    private void startSignupButtons(){
        TextView signup_button = (TextView) findViewById(R.id.signup_button);
        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(home);
            }
        });
    }
}
