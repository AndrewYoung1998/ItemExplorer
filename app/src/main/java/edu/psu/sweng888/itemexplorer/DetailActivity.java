package edu.psu.sweng888.itemexplorer;

import com.bumptech.glide.Glide;  // Add Glide or Picasso for image loading

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Retrieve the item from the intent
        Item item = (Item) getIntent().getSerializableExtra("item");

        // Display item details (e.g., using TextViews and ImageView)
        TextView nameTextView = findViewById(R.id.nameTextView);
        TextView descriptionTextView = findViewById(R.id.descriptionTextView);
        ImageView imageView = findViewById(R.id.imageView);

        nameTextView.setText(item.getName());
        descriptionTextView.setText(item.getDescription());
        // Back button callback
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back press event here
                Intent intent = new Intent();
                intent.putExtra("fromDetailActivity", true);  // Set flag
                setResult(RESULT_OK, intent);
                finish();  // Close the activity
            }
        };
        this.getOnBackPressedDispatcher().addCallback(this, callback);
        // Use Glide or Picasso to load image from the URL into the ImageView
        // Use Glide to load the image from the URL into the ImageView
        Glide.with(this)
                .load(item.getImageUrl())  // Test with a different, known working image URL
                .centerCrop()
                .into(imageView);  // Glide or Picasso can be used for image loading
    }
}

