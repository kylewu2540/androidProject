package com.example.apt_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.sql.*;
import androidx.appcompat.app.AlertDialog;

import android.content.DialogInterface;
import java.util.regex.*;

public class RegisterActivity extends AppCompatActivity {

    private EditText mFullName;
    private EditText mUsername;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mConfirmPassword;
    private Button mRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFullName = findViewById(R.id.fullName_edit_text);
        mUsername = findViewById(R.id.username_edit_text);
        mEmail = findViewById(R.id.email_edit_text);
        mPassword = findViewById(R.id.password_edit_text);
        mConfirmPassword = findViewById(R.id.confirm_password_edit_text);
        mRegisterButton = findViewById(R.id.register_button);

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String full_name = mFullName.getText().toString();
                String username = mUsername.getText().toString();
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                String confirmPassword = mConfirmPassword.getText().toString();

                if (validateRegistration(full_name, username, email, password, confirmPassword)) {
                    Connection connection = null;
                    try {
                        Class.forName("org.postgresql.Driver");
                        String jdbcUrl = "jdbc:postgresql://localhost:5432/database";
                        String usernameDB = "username";
                        String passwordDB = "password";
                        connection = DriverManager.getConnection(jdbcUrl, usernameDB, passwordDB);
                        Statement statement = connection.createStatement();

                        ResultSet rs = statement.executeQuery("SELECT email FROM users WHERE email = '" + email + "'");

                        if(rs.next())
                        {
                            //dialogbox
                        }
                        else
                        {
                            // insert data
                            String insertDataSQL = "INSERT INTO users (full_name, email, username, password, confirm_password) VALUES ('" + full_name + "', '" + username + "', '" + email + "', '" + password + "', '" + confirmPassword + "')";
                            statement.executeUpdate(insertDataSQL);
                        }

                        statement.close();

                    } catch (SQLException e) {
                        System.out.println("Error connecting to database: " + e.getMessage());
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (connection != null) {
                                connection.close();
                            }
                        } catch (SQLException e) {
                            System.out.println("Error closing connection: " + e.getMessage());
                        }
                    }
                } else {
                    // Show an error message
                    showAlertDialog("The user already exists!");
                }
            }
        });
    }

    private void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
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

    private boolean validateRegistration(String fullName, String username, String email, String password, String confirmPassword) {
        String regexFN = "/^[a-z]([-']?[a-z]+)*( [a-z]([-']?[a-z]+)*)+$";
        Pattern pOne = Pattern.compile(regexFN);
        Matcher matchFN = pOne.matcher(fullName);

        String regexUN = "";
        Pattern pTwo = Pattern.compile(regexUN);
        Matcher matchUN = pTwo.matcher(username);

        String regexEmail = "^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pThree = Pattern.compile(regexEmail);
        Matcher matchEmail = pThree.matcher(email);

        String regexPass = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pFour = Pattern.compile(regexPass);
        Matcher matchPass = pFour.matcher(password);

        if(fullName.isEmpty() || !matchFN.matches())
        {
            mFullName.setError("You must enter a name that contains at least a first and last name separated by a string");
            return false;
        }
        if(username.isEmpty() || !matchUN.matches())
        {
            mUsername.setError("You must enter a name that contains at least a first and last name separated by a string");
            return false;
        }
        if(email.isEmpty() || !matchEmail.matches())
        {
            mEmail.setError("You must enter a valid email address that contains a @");
            return false;
        }
        if(password.isEmpty() || !matchPass.matches())
        {
            mPassword.setError("You must enter a password that is at least 8 characters long, contains at least one uppercase letter, one lowercase letter and one special character");
            return false;
        }
        if(confirmPassword.isEmpty() || confirmPassword != password)
        {
            mConfirmPassword.setError("Confirm password must match password");
            return false;
        }

        return true;
    }
}