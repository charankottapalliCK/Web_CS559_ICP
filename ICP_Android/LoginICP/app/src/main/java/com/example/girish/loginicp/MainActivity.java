package com.example.girish.loginicp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends AppCompatActivity {

    private EditText LoginID;
    private EditText Password;
    private TextView LoginInfo;
    private Button LoginButton;
    private LoginButton FacebookLogin;
    CallbackManager callbackManager;
    private int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        LoginID = (EditText)findViewById(R.id.loginId);
        Password = (EditText)findViewById(R.id.password);
        LoginInfo = (TextView)findViewById(R.id.tv_info);
        LoginButton =(Button)findViewById(R.id.login_button);
        FacebookLogin  = (LoginButton)findViewById(R.id.login);
        callbackManager = CallbackManager.Factory.create();
        FacebookLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                LoginInfo.setText("Login Success \n" + loginResult.getAccessToken().getUserId()
                        +"\n"+loginResult.getAccessToken().getToken());
            }

            @Override
            public void onCancel() {
                LoginInfo.setText("Facebook Login Cancelled");
            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        LoginInfo.setText("Number of Attempts remaining : 3");

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(LoginID.getText().toString(),Password.getText().toString());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    private void validate(String userLogin, String userPassword){
        if((userLogin.equals("Admin")) && (userPassword.equals("Password"))){
            Intent intent = new Intent(MainActivity.this, WelcomePage.class);
            startActivity(intent);
        }
        else{
            counter--;
            LoginInfo.setText("Number of Attempts Remaining : "+ String.valueOf(counter));
            if(counter == 0){
                LoginButton.setEnabled(false);
            }
        }
    }
}
