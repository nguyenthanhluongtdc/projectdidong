package com.example.project.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context){
        super(context,"ManagementProduct",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql1 = "CREATE TABLE Product(CodeProduct text PRIMARY KEY, ImgProduct blob not null,NameProduct text, Origin text, Price real)";
        String sql2 = "CREATE TABLE Customer(CodeCustomer text PRIMARY KEY, NameCustomer text, Address text, NumberPhone text)";
        String sql3 = "CREATE TABLE Bill(CodeBill text PRIMARY KEY, CodeCustomer text, DateBill text, TotalMoney real)";
        String sql4 = "CREATE TABLE DetailBill(CodeBill text , CodeProduct text, NameProduct text, " +
                "Amount integer,Price real,TotalMoney real)";

        String sql5 = "CREATE TABLE Statistical(Id text PRIMARY KEY, Selling integer)";

        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);
        db.execSQL(sql4);
        db.execSQL(sql5);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
