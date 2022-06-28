package com.ciencias.tarea2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    private String USER_ID = "USER_ID";
    private String USERNAME_TOAST = "¡Datos inválidos! \nEl nombre de usuario debe tener mínimo 4 caracteres";
    private String PASSWORD_TOAST = "¡Datos inválidos! \nLa contraseña debe tener mínimo 6 caracteres";
    private String CARD_TOAST = " ¡Datos inválidos! \nEl número de tarjeta debe tener 16 dígitos";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        startSignupButtons();

    }

    private void startSignupButtons(){
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio);
        ((RadioButton)radioGroup.getChildAt(0)).setChecked(true);
        Button signup_button = (Button) findViewById(R.id.signup_button);
        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name = (EditText) findViewById(R.id.user_name);
                EditText password = (EditText) findViewById(R.id.password);
                EditText card = (EditText) findViewById(R.id.card);
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton gender = (RadioButton) findViewById(selectedId);
                int genderValue;
                if( gender.getText().toString().equals("Hombre"))
                    genderValue=0;
                else if( gender.getText().toString().equals("Mujer"))
                    genderValue=1;
                else
                    genderValue=2;


                User user = new User();
                user.setNombre(name.getText().toString());
                user.setPassword(password.getText().toString());

                String numTarjeta = card.getText().toString();
                if (numTarjeta == "")
                    user.setTarjeta(-1);
                else
                    user.setTarjeta(Long.parseLong(card.getText().toString()) );
                user.setGenero(genderValue);

                long userId = createUser(user);
                if (userId == -1){
                    finish();
                    startActivity(getIntent());
                }
                else {
                    Intent home = new Intent(SignupActivity.this, MainActivity.class);
                    home.putExtra(USER_ID, userId);
                    startActivity(home);
                }
            }
        });



    }

    private long createUser(User newUser){
        if(!validateData(newUser)){
            Log.d("LoginActivity", "¡Datos Invalidos!");
            return -1;
        }
        ManagerBDD managerBdd = new ManagerBDD(SignupActivity.this);
        managerBdd.openForWrite();
        long user_id = managerBdd.insertUser(newUser);
        managerBdd.close();
        return user_id;
    }

    private boolean validateData(User user){
        Log.d("LoginActivity", user.getNombre());
        if(user.getNombre().length() < 4){
            Toast toastName = Toast.makeText(getApplicationContext(),
                    USERNAME_TOAST, Toast.LENGTH_LONG);
            toastName.show();
            return false;
        } else if(user.getPassword().length() < 6){
            Toast toastName = Toast.makeText(getApplicationContext(),
                    PASSWORD_TOAST, Toast.LENGTH_LONG);
            toastName.show();
            return false;
        } else if(String.valueOf(user.getTarjeta()).length() != 16){
            Toast toastName = Toast.makeText(getApplicationContext(),
                    CARD_TOAST, Toast.LENGTH_LONG);
            toastName.show();
            return false;
        }
        return true;
    }
}
