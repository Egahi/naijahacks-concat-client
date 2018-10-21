package com.example.kpose.letty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    // I am of the opinion that the current home should be made the main activity
    // while the signIn button or tab is left on the home page for users to signin at their convenience
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent signIn = new Intent(MainActivity.this, SignIn.class);
        startActivity(signIn);
    }
}
