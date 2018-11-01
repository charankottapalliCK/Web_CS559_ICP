package com.example.girish.loginicp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Sign extends AppCompatActivity {

    private String Gender="";
    EditText LoginId;
    EditText Password;
    EditText University;
    EditText Branch;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button Signup_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        radioGroup = findViewById(R.id.radiogroup);
        LoginId = (EditText)findViewById(R.id.login_sign);
        Password = (EditText)findViewById(R.id.password_sign);
        University = (EditText)findViewById(R.id.univ_sign);
        Branch = (EditText)findViewById(R.id.branch_sign);

        Signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Detials  = "Welcome" + LoginId.toString() +"\n"+
                        "University: "+University.toString()+"\n"+"Branch: "+ Branch.toString()+"\n"+
                        "Gender: "+Gender;
                Intent intent = new Intent(Sign.this,MainActivity.class);
                intent.putExtra("NAME",LoginId.toString());
                intent.putExtra("PASSWORD",Password.toString());
                intent.putExtra("WELCOME",Detials);
                startActivity(intent);
            }
        });
    }

    public void checkRadio(View v){
        int radioId =radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Gender = radioButton.getText().toString();
    }

}
