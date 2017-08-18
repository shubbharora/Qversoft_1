package com.example.shubbh.qversoft_1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.shubbh.qversoft_1.Database.DatabaseHelper;

import java.util.List;

public class CompanyName extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public EditText EtCompanyName, EtBillDate, EtBillNo, EtEmail, EtAddress, EtInfo;
    Button bAdd, bSelect;
    Spinner companySpinner;
    public static final String COMPANY_NAME = "companyName";
    public String s,bd,bn,ea,a,i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_name);

        companySpinner = (Spinner) findViewById(R.id.searchSpinner);
        EtCompanyName = (EditText) findViewById(R.id.companyNameEt);
        bAdd = (Button) findViewById(R.id.addBt);
        bSelect = (Button) findViewById(R.id.selectBt);
        EtBillDate = (EditText) findViewById(R.id.billdateEt);
        EtBillNo = (EditText) findViewById(R.id.billnoEt);
        EtEmail = (EditText) findViewById(R.id.emailEt);
        EtAddress = (EditText) findViewById(R.id.addressEt);
        EtInfo = (EditText) findViewById(R.id.infoEt);

       // s=companySpinner.getSelectedItem().toString().trim();
        bd=EtBillDate.getText().toString().trim();
        bn=EtBillNo.getText().toString().trim();
        ea=EtEmail.getText().toString().trim();
        a=EtAddress.getText().toString().trim();
        i=EtInfo.getText().toString().trim();



        bSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              boolean isValidate = validateSpinner();
                if(isValidate == true  && EtBillDate.toString().trim() != null && EtBillNo.toString().trim() != null &&
                EtBillNo.toString().trim() != null && EtEmail.toString().trim() != null && EtAddress.toString().trim() != null) {
                    Intent inve_intent = new Intent(CompanyName.this, CreateInvoice.class);
                    inve_intent.putExtra(COMPANY_NAME, companySpinner.getSelectedItem().toString().trim());
                    startActivity(inve_intent);
                }else Toast.makeText(CompanyName.this, "Please Select a Company", Toast.LENGTH_SHORT).show();
            }
        });

        loadSpinnerData();
        bAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String label = EtCompanyName.getText().toString();

                if (label.trim().length() > 0) {
                    DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                    db.insertData(label);

                    EtCompanyName.setText("");

                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(EtCompanyName.getWindowToken(), 0);

                    loadSpinnerData();

                } else {
                    // Toast.makeText(getApplicationContext(), "Please Enter Label Name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadSpinnerData() {
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        List<String> labels= db.getAllLabels();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, labels);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        companySpinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       /*
       //On selecting a spinner item
        String label = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        *//*Toast.makeText(parent.getContext(), "You selected: " + label,
                Toast.LENGTH_LONG).show();
                */
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg) {
        //Toast.makeText(this, "Please Select a Company", Toast.LENGTH_SHORT).show();
    }

    private boolean validateSpinner() {
        if (companySpinner.getSelectedItem() == null) {
            return false;
        }
         else return true;
    }
}
