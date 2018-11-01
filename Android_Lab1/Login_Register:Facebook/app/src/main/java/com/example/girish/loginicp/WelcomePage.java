package com.example.girish.loginicp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomePage extends AppCompatActivity {


    TextView Welcome;
    Button Logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);


        Welcome = (TextView)findViewById(R.id.welcome);
        String wel1 = getIntent().getStringExtra("WELCOME_USER");
        Welcome.setText(wel1);

        Logout = (Button)findViewById(R.id.logout);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomePage.this,Sign.class);
                startActivity(intent);
            }
        });

    }
}
