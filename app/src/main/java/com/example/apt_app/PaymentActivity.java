package com.example.apt_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.*;
import android.widget.*;
import java.util.*;

public class PaymentActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Payment> items;
    private ArrayAdapter<Payment> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        listView = findViewById(R.id.list_view);
        items = new ArrayList<>();
        adapter = new ArrayAdapter<Payment>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
    }

    public void addItem(View view, double utilities, double rent) {
        Payment payment = new Payment(utilities, rent);
        items.add(payment);
        adapter.notifyDataSetChanged();
    }
}