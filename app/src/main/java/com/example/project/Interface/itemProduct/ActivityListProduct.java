package com.example.project.Interface.itemProduct;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.Adapter.CustomListView;
import com.example.project.Adapter.CustomListViewProduct;
import com.example.project.Database.DBProduct;
import com.example.project.Interface.itemBill.ActivityListBill;
import com.example.project.Interface.itemBill.ActivityUpdateDeleteBill;
import com.example.project.Model.Bill;
import com.example.project.Model.Product;
import com.example.project.R;

import java.util.ArrayList;

public class ActivityListProduct extends AppCompatActivity {
    EditText txtSearch;
    TextView tvNumerSP;
    ListView lvListSP;
    ArrayList <Product> listProduct = new ArrayList<>();
    DBProduct dbProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        setControl();
        setData();
        setEvent();
    }

    private void setEvent() {
        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                search(editable.toString());
            }
        });

        lvListSP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Product product = (Product) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(ActivityListProduct.this, ActivityUpdateDeleteProduct.class);
                Bundle bundle = new Bundle();
                bundle.putString("code",product.getCodeProduct());
                bundle.putByteArray("img",product.getImgProduct());
                bundle.putString("name",product.getNameProduct());
                bundle.putString("origin",product.getOrigin());
                bundle.putFloat("price",product.getPrice());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void search(String text){
        ArrayList <Product> listSearch = new ArrayList<>();
        for(Product product : listProduct){
            if(product.getNameProduct().toLowerCase().contains(text.toLowerCase())){
                listSearch.add(product);
            }
        }
        if(listSearch.size()>0){
            CustomListViewProduct customListViewProduct = new CustomListViewProduct(this,R.layout.custom_listview_bill,listSearch);
            lvListSP.setAdapter(customListViewProduct);
            customListViewProduct.notifyDataSetChanged();
            tvNumerSP.setText(listSearch.size() +" Sản phẩm");
        }else{
            setData();
        }
    }

    public void setData() {
        listProduct = dbProduct.getProducts();

        CustomListViewProduct customListViewProduct = new CustomListViewProduct(this,R.layout.custom_listview_bill,listProduct);
        lvListSP.setAdapter(customListViewProduct);
        customListViewProduct.notifyDataSetChanged();

        tvNumerSP.setText(listProduct.size() +" Sản phẩm");
    }

    private void setControl() {
        txtSearch = findViewById(R.id.txtSearch);
        lvListSP = findViewById(R.id.lvListSP);
        tvNumerSP = findViewById(R.id.tvNumberSP);
        dbProduct = new DBProduct(this);
    }
}