package com.example.project.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project.Model.Customer;
import com.example.project.Model.DetailBill;
import com.example.project.Model.Product;
import com.example.project.Model.Statistical;

import java.util.ArrayList;

public class DBStatistical {
    DBHelper dbHelper;

    public DBStatistical(Context context){
        dbHelper = new DBHelper(context);
    }

    public void newInsert(String codeProduct){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Id",codeProduct);
        values.put("Selling",0);
        db.insert("Statistical",null,values);
    }

    public void update(String codeProduct , int number){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("UPDATE Statistical SET " +"Id"+"='"+codeProduct+"'"
                +",Selling"+"='"+number+"'"
                +"WHERE "+"Id"+"='"+codeProduct+"'");
        db.close();
    }

    public int getOne(String code){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        int value = 0;
        String sql = "select * from Statistical WHERE Id "+"='"+code+"'";
        Cursor cursor = db.rawQuery(sql,null);
        try{
            value = cursor.getInt(1);
        }catch (Exception ex){

        }
        return value;
    }

    public ArrayList getStatisticalAll(){
        ArrayList<Statistical> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT  * FROM Statistical";
        Cursor cursor = db.rawQuery(sql,null);
        try{
            cursor.moveToFirst();
            do{
                Statistical statistical = new Statistical(cursor.getString(0),cursor.getInt(1));
                list.add(statistical);
            }while (cursor.moveToNext());
        }catch (Exception ex){

        }
        return  list;
    }
}
