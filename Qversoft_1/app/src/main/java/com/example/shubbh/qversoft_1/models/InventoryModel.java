package com.example.shubbh.qversoft_1.models;

/**
 * Created by Shubbh on 7/31/2017.
 */

public class InventoryModel {
    private String mProductName;
    private String mAvailableStock;
    private String mUnitCost;
    private String mGST;

    public InventoryModel(String mProductName, String mAvailableStock, String mUnitCost, String mGST) {
        this.mProductName = mProductName;
        this.mAvailableStock = mAvailableStock;
        this.mUnitCost = mUnitCost;
        this.mGST = mGST;
    }

    public String getmProductName() {
        return mProductName;
    }

    public void setmProductName(String mProductName) {
        this.mProductName = mProductName;
    }

    public String getmAvailableStock() {
        return mAvailableStock;
    }

    public void setmAvailableStock(String mAvailableStock) { this.mAvailableStock = mAvailableStock; }

    public String getmUnitCost() { return mUnitCost; }

    public void setmUnitCost(String mUnitCost) {
        this.mUnitCost = mUnitCost;
    }

    public String getmGST() { return mGST; }

    public void setmGST(String mGST) {
        this.mGST = mGST;
    }
}
