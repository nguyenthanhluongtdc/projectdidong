package com.example.project.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.project.Model.Bill;

import java.util.ArrayList;

public class DBBill {
    private  DBHelper dbHelper;

    public  DBBill(Context context){
        dbHelper = new DBHelper(context);
    }

    public void InsertBill(Bill bill){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("CodeBill",bill.getCodeBill());
        values.put("CodeCustomer",bill.getCodeCustomer());
        values.put("DateBill",bill.getDateBill());
        values.put("TotalMoney",bill.getTotalMoney());
        db.insert("Bill",null,values);
    }

    public void DeleteBill(String code){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM Bill " +"WHERE "+"CodeBill"+"='"+code+"'");
        db.close();
    }

    public void UpdateBill(Bill bill){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("UPDATE Bill SET " +"CodeBill"+"='"+bill.getCodeBill()+"'"
                +",CodeCustomer"+"='"+bill.getCodeCustomer()+"'"
                +",DateBill"+"='"+bill.getDateBill()+"'"
                +",TotalMoney"+"='"+bill.getTotalMoney()+"'"+"WHERE "+"CodeBill"+"='"+bill.getCodeBill()+"'");
        db.close();
    }

    public ArrayList<Bill> GetDataBill(){
        ArrayList<Bill> listBill = new ArrayList<>();
        String sql = "select * from Bill";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        try{
            cursor. moveToFirst();
            do{
                Bill bill = new Bill();
                bill.setCodeBill(cursor.getString(0));
                bill.setCodeCustomer(cursor.getString(1));
                bill.setDateBill(cursor.getString(2));
                bill.setTotalMoney(cursor.getInt(3));
                listBill.add(bill);
            }while (cursor.moveToNext());

        }catch (Exception ex){
        }
        return listBill;
    }
}
