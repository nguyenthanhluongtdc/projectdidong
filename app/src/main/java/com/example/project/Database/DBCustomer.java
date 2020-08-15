package com.example.project.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project.Model.Bill;
import com.example.project.Model.Customer;
import com.example.project.Model.Product;
import com.example.project.R;

import java.util.ArrayList;

public class DBCustomer {
    DBHelper dbHelper;

    public DBCustomer(Context context){
        dbHelper = new DBHelper(context);
    }

    public void insert(Customer customer){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("CodeCustomer",customer.getCodeCustomer());
        values.put("NameCustomer",customer.getNameCustomer());
        values.put("Address", customer.getAddRess());
        values.put("NumberPhone", customer.getNumberPhone());
        db.insert("Customer",null,values);
    }

    public void delete(String code){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM Customer WHERE CodeCustomer"+"='"+code+"'");
        db.close();
    }

    public void update(Customer customer){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("UPDATE Customer SET " +"CodeCustomer"+"='"+customer.getCodeCustomer()+"'"
                +",NameCustomer"+"='"+customer.getNameCustomer()+"'"
                +",Address"+"='"+customer.getAddRess()+"'"
                +",NumberPhone"+"='"+customer.getNumberPhone()+"'"
                +"WHERE "+"CodeCustomer"+"='"+customer.getCodeCustomer()+"'");
        db.close();
    }

    public ArrayList getCustomer(){
        ArrayList<Customer> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT  * FROM Customer";
        Cursor cursor = db.rawQuery(sql,null);
        try{
            cursor.moveToFirst();
            do{
                Customer customer = new Customer();
                customer.setCodeCustomer(cursor.getString(0));
                customer.setNameCustomer(cursor.getString(1));
                customer.setAddRess(cursor.getString(2));
                customer.setNumberPhone(cursor.getString(3));
                list.add(customer);
            }while (cursor.moveToNext());
        }catch (Exception ex){

        }
        return  list;
    }

}
