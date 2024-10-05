package edu.psu.sweng888.itemexplorer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private List<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        itemList = new ArrayList<>();

        // Add items to the list with name, description, and imageUrl
        itemList.add(new Item("Item 1", "This is the description of item 1", "https://placehold.co/600x600.png"));
        itemList.add(new Item("Item 2", "This is the description of item 2", "https://placehold.co/600x400.png"));

        // Custom adapter to display name and description
        ArrayAdapter<Item> adapter = new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_2, android.R.id.text1, itemList) {
            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = view.findViewById(android.R.id.text1);
                TextView text2 = view.findViewById(android.R.id.text2);

                Item currentItem = itemList.get(position);
                text1.setText(currentItem.getName());  // Set name
                text2.setText(currentItem.getDescription());  // Set description

                return view;
            }
        };
        listView.setAdapter(adapter);

        // Set onClick listener
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Item clickedItem = itemList.get(position);

            // Start second activity and pass the item data
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("item", clickedItem);
            startActivityForResult(intent, 1);  // Use startActivityForResult
        });

    }
    // Handle result from DetailActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Set the flag in the Intent to show Snackbar
            getIntent().putExtra("fromDetailActivity", true);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();

        // Check if we returned from DetailActivity
        Intent intent = getIntent();
        boolean fromDetailActivity = intent.getBooleanExtra("fromDetailActivity", false);
        if (fromDetailActivity) {
            // Check for current focus to avoid NullPointerException
            View view = this.getCurrentFocus();
            if (view != null) {
                Snackbar.make(view, "Returned to Main Activity", Snackbar.LENGTH_SHORT).show();
            }
            // Reset the flag so that Snackbar doesn't show again
            intent.putExtra("fromDetailActivity", false);
        }
    }
}

