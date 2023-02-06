package com.example.apt_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.sql.*;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private EditText mUsername;
    private EditText mPassword;
    private Button mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsername = findViewById(R.id.username_edit_text);
        mPassword = findViewById(R.id.password_edit_text);
        mLoginButton = findViewById(R.id.login_button);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsername.getText().toString();
                String password = mPassword.getText().toString();

                if (validateLogin(username, password)) {
                    //screen navigation
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    String message = "The user does not exist!";
                    showAlertDialog(message);
                }
            }
        });
    }

    private void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Error");
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private boolean validateLogin(String username, String password) {
        Connection connection = null;
        try {
            String jdbcUrl = "jdbc:postgresql://localhost:5432/database";
            String usernameDB = "username";
            String passwordDB = "password";
            connection = DriverManager.getConnection(jdbcUrl, usernameDB, passwordDB);
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
        return false;
    }
}