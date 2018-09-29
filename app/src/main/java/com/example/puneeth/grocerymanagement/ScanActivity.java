package com.example.puneeth.grocerymanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ScanActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    String typeOfAction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
//        Toast.makeText(this, getIntent().getExtras().getString("content") + " : " + getIntent().getExtras().getString("formatName"), Toast.LENGTH_SHORT).show();
//        GroceryItem groceryItem = new GroceryItem(getIntent().getExtras().getString("content"));
        //if found in the database, no need to create new entry
        mDatabase = FirebaseDatabase.getInstance().getReference();


    }
    public void submitHandler(View view){
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(radioButtonId);
        typeOfAction = radioButton.getText().toString();
        
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                GroceryItem groceryItem = dataSnapshot.child(getIntent().getExtras().getString("content")).getValue(GroceryItem.class);
                if(groceryItem == null){
                    //object not found, create a new one.
                    if(typeOfAction.equals("Add")){
                        Intent intent = new Intent(ScanActivity.this, NewGroceryItemActivity.class);
                        intent.putExtra("content", getIntent().getExtras().getString("content"));
                        intent.putExtra("formatName", getIntent().getExtras().getString("formatName"));
                        startActivity(intent);
                    }
                    else if(typeOfAction.equals("Remove")){
                        Toast.makeText(ScanActivity.this, "Sorry the item was not there in the godown at all!!!", Toast.LENGTH_SHORT).show();
                    }


                }
                else{
                    //object, found
                    Toast.makeText(ScanActivity.this, "Object already found!!!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(ScanActivity.this, "content : " + groceryItem.getQrCode() + " item Name : " + groceryItem.getItemName(), Toast.LENGTH_SHORT).show();
                    // ...
                    if(typeOfAction.equals("Add")){
                        mDatabase.child(getIntent().getExtras().getString("content")).child("count").setValue(groceryItem.getCount() + 1);
                    }
                    else if(typeOfAction.equals("Remove")){
                        if(groceryItem.getCount() == 0){
                            Toast.makeText(ScanActivity.this, "Sorry the item was not there in the godown at all!!!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            mDatabase.child(getIntent().getExtras().getString("content")).child("count").setValue(groceryItem.getCount() - 1);
                        }
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
//                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        mDatabase.addListenerForSingleValueEvent(postListener);
    }
}
