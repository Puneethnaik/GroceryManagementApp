package com.example.puneeth.grocerymanagement;

import android.support.v7.app.AppCompatActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button scanButton, searchButton, orderButton;
    IntentIntegrator removeIntegrator;
    IntentIntegrator scanIntegrator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanButton = findViewById(R.id.addButton);
        searchButton = findViewById(R.id.search);
        orderButton = findViewById(R.id.order);
    }
    public void handleScan(View view){
        scanIntegrator = new IntentIntegrator(this);
        scanIntegrator.initiateScan();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve scan result

        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if(scanningResult != null){
            String content = scanningResult.getContents();
            String format = scanningResult.getFormatName();
            Intent scanActivity = new Intent(this, ScanActivity.class);
            scanActivity.putExtra("content", content);
            scanActivity.putExtra("formatName", format);
            scanActivity.putExtra("typeOfAction", "Add");
            startActivity(scanActivity);

        }
        else{
            Toast.makeText(this, "No value recieved", Toast.LENGTH_SHORT).show();
        }
    }
}
