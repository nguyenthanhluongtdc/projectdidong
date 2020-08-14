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
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.Database.DBProduct;
import com.example.project.Model.Product;
import com.example.project.R;

import java.io.FileInputStream;
import java.util.ArrayList;

public class ActivityUpdateDeleteProduct extends AppCompatActivity {
    EditText txtName, txtPrice;
    Spinner spOrigin;
    TextView tvCode;
    Button btnUpdate, btnDelete, btnUpdateImg;
    byte[] imgProduct;
    ArrayList<String> listOrigin = new ArrayList<>();
    private static final int PICK_IMAGE = 100;
    DBProduct dbProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_product);
        setControl();
        setData();
        setEvent();
    }

    private void setEvent() {
        btnUpdateImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, Uri.parse("content://media//internal/images/media"));
                startActivityForResult(intent,PICK_IMAGE);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product product = new Product(tvCode.getText().toString(),
                        imgProduct,txtName.getText().toString(),
                        spOrigin.getSelectedItem().toString(),
                        Float.parseFloat(txtPrice.getText().toString()));
                ///update
                dbProduct.update(product);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ma = tvCode.getText().toString();
                //delete
                dbProduct.delete(ma);
                finish();
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
                Toast.makeText(ActivityUpdateDeleteProduct.this,"successfuly",Toast.LENGTH_LONG).show();
            }catch (Exception ex){
                Toast.makeText(ActivityUpdateDeleteProduct.this,ex.getMessage(),Toast.LENGTH_LONG).show();
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

    private void setData(){
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

        Bundle bundle = getIntent().getExtras();
        String ma = bundle.getString("code");
        imgProduct = bundle.getByteArray("img");
        String name = bundle.getString("name");
        String origin = bundle.getString("origin");
        float price = bundle.getFloat("price");

        tvCode.setText(ma);
        txtName.setText(name);
        spOrigin.setSelection(listOrigin.indexOf(origin));
        txtPrice.setText(price +"");

    }

    private void setControl() {
        txtName = findViewById(R.id.txtNameProduct);
        txtPrice = findViewById(R.id.txtPrice);
        spOrigin = findViewById(R.id.spOrigin);
        tvCode = findViewById(R.id.tvCodeProduct);
        btnUpdate = findViewById(R.id.btnUpdateProduct);
        btnDelete = findViewById(R.id.btnDeleteProduct);
        btnUpdateImg = findViewById(R.id.btnUpdateImage);
        dbProduct = new DBProduct(this);
    }

}