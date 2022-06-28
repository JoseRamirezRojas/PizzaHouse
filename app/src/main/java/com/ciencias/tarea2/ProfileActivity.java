package com.ciencias.tarea2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;


public class ProfileActivity extends AppCompatActivity {

    private String USER_ID = "USER_ID";
    private long userId = -1;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        activateEvents();
        getIntentData();
        getUser();
        setItems();
    }

    private void getIntentData(){
        Intent intent = getIntent();
        this.userId = intent.getLongExtra(USER_ID, -1);
    }

    private void getUser(){
        ManagerBDD managerBdd = new ManagerBDD(ProfileActivity.this);
        managerBdd.openForRead();
        this.user = managerBdd.getUser(userId);
        managerBdd.close();
    }

    private void setItems(){
        EditText nameEditText = (EditText) findViewById(R.id.user_name);
        EditText passwordEditText = (EditText) findViewById(R.id.password);
        EditText birthdateEditText = (EditText) findViewById(R.id.card);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio);


        nameEditText.setText(user.getNombre());
        passwordEditText.setText(user.getPassword());
        birthdateEditText.setText(String.valueOf(user.getTarjeta()));
        if(user.getGenero() == 0){
            ((RadioButton)radioGroup.getChildAt(0)).setChecked(true);
        } else if (user.getGenero() == 1){
                ((RadioButton)radioGroup.getChildAt(1)).setChecked(true);
        } else
            ((RadioButton)radioGroup.getChildAt(2)).setChecked(true);
    }

    public void activateEvents(){
        Button btn_continue = (Button) findViewById(R.id.continue_button);
        btn_continue.setOnClickListener(new View.OnClickListener() {

            EditText nameEditText = (EditText) findViewById(R.id.user_name);
            EditText passwordEditText = (EditText) findViewById(R.id.password);
            EditText cardEditText = (EditText) findViewById(R.id.card);
            RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio);


            @Override
            public void onClick(View view) {
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                RadioButton gender = (RadioButton) findViewById(selectedId);

                user.setNombre(nameEditText.getText().toString());
                user.setPassword(passwordEditText.getText().toString());
                user.setTarjeta(Long.parseLong(cardEditText.getText().toString()) );

                int genderValue;
                if( gender.getText().toString().equals("Hombre"))
                    genderValue=0;
                else if( gender.getText().toString().equals("Mujer"))
                    genderValue=1;
                else
                    genderValue=2;
                user.setGenero(genderValue);

                ManagerBDD managerBdd = new ManagerBDD(ProfileActivity.this);
                managerBdd.openForWrite();
                managerBdd.updateUser(userId, user);
                managerBdd.close();
                finish();
            }
        });
    }



}
