package com.example.puneeth.grocerymanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewGroceryItemActivity extends AppCompatActivity {
    private TextView textView;
    private Button submitButton;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_grocery_item);
        textView = findViewById(R.id.itemName);
    }
    public void submitHandler(View view){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        GroceryItem groceryItem = new GroceryItem(getIntent().getExtras().getString("content"), textView.getText().toString(), getIntent().getExtras().getString("formatName"), 1);
        mDatabase.child(groceryItem.getQrCode()).setValue(groceryItem);
    }
}
