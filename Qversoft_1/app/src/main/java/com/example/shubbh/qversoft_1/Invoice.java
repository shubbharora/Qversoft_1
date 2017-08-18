package com.example.shubbh.qversoft_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.shubbh.qversoft_1.adapters.InvoiceAdapter;
import com.example.shubbh.qversoft_1.models.InvoicesModel;

import java.util.ArrayList;

import static com.example.shubbh.qversoft_1.R.drawable.bck;

public class Invoice extends AppCompatActivity  {

    public Button mCreateInvoice;
    private ListView mInvoiceListView;
    private ArrayList<InvoicesModel>  mInvoiceModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        populateData();
        mInvoiceListView = (ListView) findViewById(R.id.invoiceList);
        mInvoiceListView.setAdapter(new InvoiceAdapter(this, mInvoiceModelList));

        mCreateInvoice = (Button) findViewById(R.id.createInvoice);
    }

    public void createInvo(View v)
    {
        Intent invo_intent= new Intent(Invoice.this,CompanyName.class);
        startActivity(invo_intent);
    }

    private void populateData() {
        mInvoiceModelList = new ArrayList<>();

        mInvoiceModelList.add(new InvoicesModel("Invoice No. : 12","30/07/2017", "Amount = ₹ 6196/-"));
        mInvoiceModelList.add(new InvoicesModel("Invoice No. : 829","08/06/2017", "Amount = ₹ 100/-"));
        mInvoiceModelList.add(new InvoicesModel("Invoice No. : 73","11/01/2016", "Amount = ₹ 2579/-"));
        mInvoiceModelList.add(new InvoicesModel("Invoice No. : 127","18/03/2016", "Amount = ₹ 32458/-"));
        mInvoiceModelList.add(new InvoicesModel("Invoice No. : 63","26/06/2015", "Amount = ₹ 1442/-"));
        mInvoiceModelList.add(new InvoicesModel("Invoice No. : 354","19/02/2015", "Amount = ₹ 78/-"));
        mInvoiceModelList.add(new InvoicesModel("Invoice No. : 91","28/05/2015", "Amount = ₹ 3369/-"));
        mInvoiceModelList.add(new InvoicesModel("Invoice No. : 43","06/04/2014", "Amount = ₹ 786/-"));
    }

}
