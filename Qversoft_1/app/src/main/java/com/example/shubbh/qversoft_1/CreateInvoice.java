package com.example.shubbh.qversoft_1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shubbh.qversoft_1.Database.DatabaseHelper;
import com.example.shubbh.qversoft_1.adapters.InventoryAdapter;
import com.example.shubbh.qversoft_1.adapters.InvoiceAdapter;
import com.example.shubbh.qversoft_1.models.InventoryModel;
import com.example.shubbh.qversoft_1.models.InvoicesModel;

import java.util.ArrayList;

import static com.example.shubbh.qversoft_1.CompanyName.COMPANY_NAME;

public class CreateInvoice extends AppCompatActivity {

    private EditText newEditText2, newEditText3;
    private AutoCompleteTextView newEditText1;
    private TextView mSelectedCompany;
    private TableLayout mtable;
    private TableRow mtableRow;
    private ScrollView mscrollView;
    private LinearLayout mLinearlayout;
    private DatabaseHelper mDatabaseHelper;
    private ArrayList<String> mAllProductList;
    private ArrayList<InvoicesModel> mInvoiceModelList;
    private InvoiceAdapter mInvoiceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_invoice);
        mDatabaseHelper = new DatabaseHelper(this);

        mscrollView = (ScrollView) findViewById(R.id.scrollView);

        mtable.setColumnStretchable(0, true);
        mtable.setColumnStretchable(1, true);
        mtable.setColumnStretchable(2, true);

        Intent intent = getIntent();
        if (intent != null) {
            String companyName = intent.getStringExtra(COMPANY_NAME);
            mSelectedCompany.setText(companyName);
        }
        mAllProductList = mDatabaseHelper.getAllProductList();
    }

    public void Invo(View v)
    {
        Intent invo = new Intent(CreateInvoice.this, FinalInvoice.class);
        startActivity(invo);
    }
}
