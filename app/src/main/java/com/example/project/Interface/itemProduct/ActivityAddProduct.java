package com.example.project.Interface.itemProduct;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.project.Database.DBProduct;
import com.example.project.Database.DBStatistical;
import com.example.project.Model.Product;
import com.example.project.R;

import java.io.FileInputStream;
import java.util.ArrayList;

public class ActivityAddProduct extends AppCompatActivity {
    ArrayList <String> listOrigin = new ArrayList<>();
    EditText txtCodeProduct, txtNameProduct, txtPrice;
    Spinner spOrigin;
    Button btnAddImageProduct, btnAddProduct;
    private static final int PICK_IMAGE = 100;
    DBProduct dbProduct;
    DBStatistical dbStatistical;
    byte [] imgProduct = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        setControl();
        setData();
        setEvent();
    }

    private void setEvent() {
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgProduct == null){
                    Toast.makeText(ActivityAddProduct.this,"Vui lòng chọn hình ảnh",Toast.LENGTH_LONG).show();
                }else {
                    Product product = new Product(txtCodeProduct.getText().toString(),
                            imgProduct,txtNameProduct.getText().toString(),
                            spOrigin.getSelectedItem().toString(),
                            Float.parseFloat(txtPrice.getText().toString()));
                    dbProduct.insert(product);
                    txtCodeProduct.setHint("Mã sản phẩm");
                    txtNameProduct.setHint("Tên sản phẩm");
                    txtPrice.setHint("Giá");
                    spOrigin.setSelection(listOrigin.indexOf(listOrigin.get(0)));
                    imgProduct = null;

                    dbStatistical.newInsert(txtCodeProduct.getText().toString());

                    Toast.makeText(ActivityAddProduct.this,"Thêm thành công",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnAddImageProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, Uri.parse("content://media//internal/images/media"));
                startActivityForResult(intent,PICK_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE ){
            Uri uri = data.getData();
            String x = getPath(uri);

            try {
                FileInputStream fs = new FileInputStream(x);
                imgProduct = new byte[fs.available()];
                fs.read(imgProduct);
                Toast.makeText(ActivityAddProduct.this,"Đã chọn",Toast.LENGTH_LONG).show();
            }catch (Exception ex){
                Toast.makeText(ActivityAddProduct.this,ex.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
    }

    private String getPath(Uri uri) {
        if(uri  == null) return null;
        String [] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri,projection,null,null,null);
        if(cursor != null){
            int colum = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(colum);
        }
        return uri.getPath();
    }

    private void setData() {
        listOrigin.add("Ninh Thuận");
        listOrigin.add("Bình Thuận");
        listOrigin.add("Quảng Nam");
        listOrigin.add("Quảng Ngãi");
        listOrigin.add("Bình Định");
        listOrigin.add("Phú Yên");
        listOrigin.add("Khánh Hòa");
        listOrigin.add("Đà Nẵng");
        listOrigin.add("TPHCM");
        listOrigin.add("Hà Nội");
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, listOrigin);
        spOrigin.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void setControl() {
        txtCodeProduct = findViewById(R.id.txtCodeProduct);
        txtNameProduct = findViewById(R.id.txtNameProduct);
        txtPrice = findViewById(R.id.txtPrice);
        spOrigin = findViewById(R.id.spOrigin);
        btnAddImageProduct = findViewById(R.id.btnAddImage);
        btnAddProduct = findViewById(R.id.btnAddProduct);
        dbProduct = new DBProduct(this);
        dbStatistical = new DBStatistical(this);
    }
}