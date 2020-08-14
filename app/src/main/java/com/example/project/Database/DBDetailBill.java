package com.example.project.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project.Model.Bill;
import com.example.project.Model.DetailBill;

import java.util.ArrayList;

public class DBDetailBill {
    DBHelper dbHelper;

    public DBDetailBill(Context context){
        dbHelper = new DBHelper(context);
    }

    public void InsertDetailBill(DetailBill detail){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("CodeBill",detail.getCodeBill());
        values.put("CodeProduct",detail.getCodeProduct());
        values.put("NameProduct",detail.getNameProduct());
        values.put("Amount",detail.getAmount());
        values.put("Price",detail.getPrice());
        values.put("TotalMoney",detail.getTotalMoney());
        db.insert("DetailBill",null,values);
    }

    public void DeleteDetailBill(String code){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM DetailBill " +"WHERE "+"CodeBill"+"='"+code+"'");
        db.close();
    }

    public void UpdateBill(DetailBill detailBill){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("UPDATE DetailBill SET " +"CodeBill"+"='"+detailBill.getCodeBill()+"'"
                +",CodeProduct"+"='"+detailBill.getCodeProduct()+"'"
                +",NameProduct"+"='"+detailBill.getNameProduct()+"'"
                +",Amount"+"='"+detailBill.getAmount()+"'"
                +",Price"+"='"+detailBill.getPrice()+"'"
                +",TotalMoney"+"='"+detailBill.getTotalMoney()+"'"
                +"WHERE "+"CodeBill"+"='"+detailBill.getCodeBill()+"'");
        db.close();
    }

    public ArrayList<DetailBill> GetDetailBill(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<DetailBill> list = new ArrayList<>();
        String sql = "select * from DetailBill";
        Cursor cursor = db.rawQuery(sql,null);
        try{
            cursor.moveToFirst();
            do{
                DetailBill detailBill = new DetailBill();
                detailBill.setCodeBill(cursor.getString(0));
                detailBill.setCodeProduct(cursor.getString(1));
                detailBill.setNameProduct(cursor.getString(2));
                detailBill.setAmount(cursor.getInt(3));
                detailBill.setPrice(cursor.getFloat(4));
                detailBill.setTotalMoney(cursor.getFloat(5));
                list.add(detailBill);
            }while (cursor.moveToNext());
        }catch (Exception ex){

        }
        return list;
    }

    public ArrayList<DetailBill> GetCodeBill(String code){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<DetailBill> list = new ArrayList<>();
        String sql = "select * from DetailBill WHERE CodeBill "+"='"+code+"'";
        Cursor cursor = db.rawQuery(sql,null);
        try{
            cursor.moveToFirst();
            do{
                DetailBill detailBill = new DetailBill();
                detailBill.setCodeBill(cursor.getString(0));
                detailBill.setCodeProduct(cursor.getString(1));
                detailBill.setNameProduct(cursor.getString(2));
                detailBill.setAmount(cursor.getInt(3));
                detailBill.setPrice(cursor.getFloat(4));
                detailBill.setTotalMoney(cursor.getFloat(5));
                list.add(detailBill);
            }while (cursor.moveToNext());
        }catch (Exception ex){

        }
        return list;
    }
}
