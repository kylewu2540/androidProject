package com.example.apt_app;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import java.sql.*;
import androidx.appcompat.app.AlertDialog;

import android.os.Bundle;

public class ResetPassActivity extends AppCompatActivity {

    private EditText mEmail;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);

        mEmail = findViewById(R.id.etEmail);
        mButton = findViewById(R.id.btnSubmit);
    }
}