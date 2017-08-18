package com.example.shubbh.qversoft_1.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.shubbh.qversoft_1.models.InventoryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shubbh on 8/1/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Qversoft.db";

    public static final String TABLE_NAME = "Company";
    public static final String COL_1 = "ID1";
    public static final String COL_2 = "Company_Name";

    public static final String TABLE_NAME1 = "Inventory";
    public static final String COL_11 = "ID2";
    public static final String COL_12 = "Product_Name";
    public static final String COL_13 = "Stock";
    public static final String COL_14 = "Unit_Cost";
    public static final String COL_15 = "GST";

    public static final String TABLE_NAME2 = "Invoice";
    public static final String COL_21 = "ID2";
    public static final String COL_22 = "Product_Name";
    public static final String COL_23 = "Quantity";
    public static final String COL_24 = "Total";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID1 INTEGER PRIMARY KEY AUTOINCREMENT, Company_Name TEXT NOT NULL UNIQUE)");

        db.execSQL("CREATE TABLE " + TABLE_NAME1 + " (ID2 INTEGER PRIMARY KEY AUTOINCREMENT, Product_Name TEXT NOT NULL UNIQUE, Stock INTEGER NOT NULL, Unit_Cost INTEGER NOT NULL, GST INTEGER NOT NULL)");

        db.execSQL("CREATE TABLE " + TABLE_NAME2 + " (ID3 INTEGER PRIMARY KEY AUTOINCREMENT, Product_Name TEXT NOT NULL UNIQUE, Quantity INTEGER NOT NULL, Total INTEGER NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);
    }

    public boolean insertData(String companyName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, companyName);
        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();

        if (result == -1)
            return false;

        else return true;
    }

    public boolean populateData(String invProductName, String invStock, String invCost, String invGST) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_12, invProductName);
        contentValues.put(COL_13, invStock);
        contentValues.put(COL_14, invCost);
        contentValues.put(COL_15, invGST);
        long result = db.insert(TABLE_NAME1, null, contentValues);
        db.close();

        if (result == -1)
            return false;

        else return true;
    }

    public boolean feedData(String invoProductName, String invoQty, String invoTotal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_22, invoProductName);
        contentValues.put(COL_23, invoQty);
        contentValues.put(COL_24, invoTotal);
        long result = db.insert(TABLE_NAME2, null, contentValues);
        db.close();

        if (result == -1)
            return false;

        else return true;
    }


    public Integer deleteData(String productName) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME1, "Product_Name = ?", new String[]{productName});
    }

    public Integer removeData(String productName) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME2, "Product_Name = ?", new String[]{productName});
    }

    public List<String> getAllLabels() {
        List<String> labels = new ArrayList<String>();

        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return labels;
    }

    public ArrayList<InventoryModel> getAllRecords() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<InventoryModel> mInventoryModelList = new ArrayList<>();
        try {
            String[] projection = {
                    COL_12,
                    COL_13,
                    COL_14,
                    COL_15
            };
            Cursor cursor = db.query(TABLE_NAME1, projection, null, null, null, null, null);

            while (cursor.moveToNext()) {
                String item1 = cursor.getString(cursor.getColumnIndex(COL_12));
                String item2 = cursor.getString(cursor.getColumnIndex(COL_13));
                String item3 = cursor.getString(cursor.getColumnIndex(COL_14));
                String item4 = cursor.getString(cursor.getColumnIndex(COL_15));
                mInventoryModelList.add(new InventoryModel(item1, item2, item3, item4));
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return mInventoryModelList;
        }
    }

    public boolean updateData(String Product_Name, String Stock, String Unit_Cost, String GST)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_12, Product_Name);
        contentValues.put(COL_13, Stock);
        contentValues.put(COL_14, Unit_Cost);
        contentValues.put(COL_15, GST);
        db.update(TABLE_NAME1, contentValues, " (Product_Name = ?)",
                new String[] { Product_Name });
        db.close();
        return true;
    }

    public ArrayList<String> getAllProductList() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> productList = new ArrayList<>();
        try {
            String[] projection = {
                    COL_12,
            };
            String sorted=COL_12 +" ASC";
            Cursor cursor = db.query(TABLE_NAME1, projection, null, null, null, null, sorted);

            while (cursor.moveToNext()) {
                String item1 = cursor.getString(cursor.getColumnIndex(COL_12));
                productList.add(item1);
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return productList;
        }
    }
}