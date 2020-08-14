package com.example.project.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project.Model.Bill;
import com.example.project.Model.Product;
import com.example.project.R;

import java.util.ArrayList;

public class DBProduct {
    DBHelper dbHelper;

    public DBProduct(Context context){
        dbHelper = new DBHelper(context);
    }

    public void insert(Product product){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("CodeProduct",product.getCodeProduct());
        values.put("ImgProduct",product.getImgProduct());
        values.put("NameProduct", product.getNameProduct());
        values.put("Origin", product.getOrigin());
        values.put("Price", product.getPrice());
        db.insert("Product",null,values);
    }

    public void delete(String code){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM Product WHERE CodeProduct"+"='"+code+"'");
        db.close();
    }

    public void update(Product product){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("UPDATE Product SET " +"CodeProduct"+"='"+product.getCodeProduct()+"'"
                +",ImgProduct"+"='"+product.getImgProduct()+"'"
                +",NameProduct"+"='"+product.getNameProduct()+"'"
                +",Origin"+"='"+product.getOrigin()+"'"
                +",Price"+"='"+product.getPrice()+"'"
                +"WHERE "+"CodeProduct"+"='"+product.getCodeProduct()+"'");
        db.close();
    }

    public ArrayList getProducts(){
        ArrayList<Product> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT  * FROM Product";
        Cursor cursor = db.rawQuery(sql,null);
        try{
            cursor.moveToFirst();
            do{
                Product product = new Product();
                product.setCodeProduct(cursor.getString(0));
                product.setImgProduct(cursor.getBlob(1));
                product.setNameProduct(cursor.getString(2));
                product.setOrigin(cursor.getString(3));
                product.setPrice(cursor.getFloat(4));
                list.add(product);
            }while (cursor.moveToNext());
        }catch (Exception ex){

        }
        return  list;
    }

}
