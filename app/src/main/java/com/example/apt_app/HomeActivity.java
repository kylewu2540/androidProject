package com.example.apt_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.content.*;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Create a list of items to be displayed in the table view
        String[] items = {"Games", "Book room", "Laundry", "Payment Options (utilities/rent)", "Community", "Maintenance", "Rooftop Bar"};

        // Connect the ListView in the layout to a Java object
        ListView listView = findViewById(R.id.list_view);

        // Create an ArrayAdapter to populate the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);

        // Set the adapter to the ListView
        listView.setAdapter(adapter);

        // Set an OnItemClickListener on the ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the item that was clicked
                String item = (String) parent.getItemAtPosition(position);

                // Start the DetailActivity, passing the item as an extra
                /*Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("item", item);
                startActivity(intent);*/
            }
        });
    }
}