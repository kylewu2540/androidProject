package com.example.apt_app;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import java.sql.*;
import androidx.appcompat.app.AlertDialog;

import android.os.Bundle;

import javax.mail.*;
import java.util.*;
import javax.mail.internet.*;

public class ForgotPassActivity extends AppCompatActivity {

    private EditText mEmail;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        mEmail = findViewById(R.id.etEmail);
        mButton = findViewById(R.id.btnSubmit);

        mButton.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        String email = mEmail.getText().toString();
                        final String username = "your_email@gmail.com";
                        final String password = "your_email_password";

                        Properties prop = new Properties();
                        prop.put("mail.smtp.host", "smtp.gmail.com");
                        prop.put("mail.smtp.port", "587");
                        prop.put("mail.smtp.auth", "true");
                        prop.put("mail.smtp.starttls.enable", "true");

                        Session session = Session.getInstance(prop,
                                new javax.mail.Authenticator() {
                                    protected PasswordAuthentication getPasswordAuthentication() {
                                        return new PasswordAuthentication(username, password);
                                    }
                                });

                        try {
                            Message message = new MimeMessage(session);
                            message.setFrom(new InternetAddress("from@gmail.com"));
                            message.setRecipients(
                                    Message.RecipientType.TO,
                                    InternetAddress.parse("to@gmail.com")
                            );
                            message.setSubject("Testing Gmail SSL");
                            message.setText("Dear Mail Crawler,"
                                    + "\n\n Please do not spam my email!");

                            Transport.send(message);
                            System.out.println("Done");
                        } catch (MessagingException e) {
                            e.printStackTrace();
                        }


                    }
                }
        );
    }
}