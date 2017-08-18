package com.example.shubbh.qversoft_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mInvoices;
    private Button mInventory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getIdClick();
    }

    private void getIdClick() {
        mInventory = (Button) findViewById(R.id.inventory);
        mInvoices = (Button) findViewById(R.id.invoice);

        mInventory.setOnClickListener(this);
        mInvoices.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.invoice:
                Intent invo_intent= new Intent(MainActivity.this,Invoice.class);
                startActivity(invo_intent);
                break;

            case R.id.inventory:
                Intent inve_intent= new Intent(MainActivity.this,Inventory.class);
                startActivity(inve_intent);
                break;
        }
    }
}
