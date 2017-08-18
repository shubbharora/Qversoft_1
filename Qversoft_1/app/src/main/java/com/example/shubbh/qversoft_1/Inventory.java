package com.example.shubbh.qversoft_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shubbh.qversoft_1.Database.DatabaseHelper;
import com.example.shubbh.qversoft_1.adapters.InventoryAdapter;
import com.example.shubbh.qversoft_1.models.InventoryModel;

import java.util.ArrayList;

public class Inventory extends AppCompatActivity {

    private EditText minvProductTv, minvQtyTv, minvCostTv, minvGST;
    private ImageButton maddBt, mdelBt, mupdBt;
    private SearchView msearchBar;
    private ListView mInventoryListView;
    private ArrayList<InventoryModel> mInventoryModelList;
    private InventoryAdapter mInvntoryAdapter;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        mInventoryListView = (ListView) findViewById(R.id.inventoryList);
//        mInventoryListView.setAdapter(mInvntoryAdapter);

        msearchBar = (SearchView) findViewById(R.id.inventory_search);
        msearchBar.setActivated(true);
        msearchBar.setQueryHint("Search for Product");
        msearchBar.onActionViewExpanded();
        msearchBar.setIconified(false);
        msearchBar.clearFocus();

        minvProductTv = (EditText) findViewById(R.id.invProductTv);
        minvQtyTv = (EditText) findViewById(R.id.invQtyTv);
        minvCostTv = (EditText) findViewById(R.id.invCostTv);
        minvGST = (EditText) findViewById(R.id.invGSTTv);
        maddBt = (ImageButton) findViewById(R.id.invAddBt);
        mdelBt = (ImageButton) findViewById(R.id.invDelBt);
        mupdBt = (ImageButton) findViewById(R.id.invUpdBt);

        myDb = new DatabaseHelper(this);
        ArrayList<String> arrayList = myDb.getAllProductList();

        searchView();

        maddBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String product = minvProductTv.getText().toString();
                String qty = minvQtyTv.getText().toString();
                String cost = minvCostTv.getText().toString();
                String gst = minvGST.getText().toString();

                if (product.trim().length() > 0 && qty.trim().length() > 0 && cost.trim().length() > 0 && gst.trim().length() > 0) {
                    DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                    boolean x = db.populateData(product, qty, cost, gst);
                    if (x == true) {
                        Toast.makeText(Inventory.this, "Inserted", Toast.LENGTH_SHORT).show();
                        populateInventory();
                    } else
                        Toast.makeText(Inventory.this, "Not Inserted", Toast.LENGTH_SHORT).show();

                    minvProductTv.setText("");
                    minvQtyTv.setText("");
                    minvCostTv.setText("");
                    minvGST.setText("");

                } else if (product.trim().length() < 0 || qty.trim().length() > 0 ||
                        cost.trim().length() > 0 || gst.trim().length() > 0) {
                    minvProductTv.setError("Please fill all the Values");
                    minvQtyTv.setError("Please fill all the Values");
                    minvCostTv.setError("Please fill all the Values");
                    minvGST.setError("Please fill all the Values");
                }
            }
        });
        deleteData();
        populateInventory();
        updateData();
    }

    private void searchView() {
        msearchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                mInvntoryAdapter.getFilter().filter(newText);

                return false;
            }
        });
    }

    public void deleteData() {
        mdelBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = myDb.deleteData(minvProductTv.getText().toString());
                if (deletedRows > 0) {
                    Toast.makeText(Inventory.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                    mInvntoryAdapter.deleteListItem(minvProductTv.getText().toString());
                } else
                    Toast.makeText(Inventory.this, "Product Not Found", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateInventory() {
//        mInventoryModelList = new ArrayList<>();
        mInventoryModelList = myDb.getAllRecords();
        mInvntoryAdapter = new InventoryAdapter(Inventory.this, mInventoryModelList);
        mInventoryListView.setAdapter(mInvntoryAdapter);
//        mInventoryModelList.add(new InventoryModel(minvProductTv.getText().toString(), minvQtyTv.getText().toString(), minvCostTv.getText().toString()));
    }

    public void updateData() {
        mupdBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (minvProductTv.getText().toString().length() > 0 && minvQtyTv.getText().toString().length() > 0 &&
                        minvCostTv.getText().toString().length() > 0 && minvGST.getText().toString().length() > 0) {
                    boolean isUpdated = myDb.updateData(minvProductTv.getText().toString(), minvQtyTv.getText().toString(),
                            minvCostTv.getText().toString(), minvGST.getText().toString());
                    if (isUpdated == true) {
                        int x = mInvntoryAdapter.updateData(minvProductTv.getText().toString());
                        if (x == 0)
                            Toast.makeText(Inventory.this, "Data Updated", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(Inventory.this, "Product Not Found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    minvProductTv.setError("Please fill all the Values");
                    minvQtyTv.setError("Please fill all the Values");
                    minvCostTv.setError("Please fill all the Values");
                    minvGST.setError("Please fill all the Values");
                }
            }
        });
    }
}