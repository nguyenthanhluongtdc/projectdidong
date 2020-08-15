package com.example.project.Interface.interfaceMain;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project.R;

public class MainActivity extends AppCompatActivity {
    private ProductFragment productFragment;
    private CustomerFragment customerFragment;
    private BillFragment billFragment;
    private MainFragment mainFragment;
    private Button btnHome, btnProduct, btnCustomer, btnBill;
    private TextView tvTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        initFragment();
        showHomeActivity();
        setEvent();
    }

    private void setEvent(){

        btnProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProductFragment();
            }
        });
        btnCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomerFragment();
            }
        });
        btnBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBillFragment();
            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHomeActivity();
            }
        });
    }
    private void showHomeActivity() {
        getSupportFragmentManager().beginTransaction().show(mainFragment).hide(productFragment).hide(customerFragment).hide(billFragment).commit();
        tvTitle.setText("Trang Chính");
    }
    private void showProductFragment() {
        tvTitle.setText("Sản Phẩm");
        getSupportFragmentManager().beginTransaction().show(productFragment).hide(customerFragment).hide(billFragment).hide(mainFragment).commit();
        productFragment.loadProduct();
    }
    private void showCustomerFragment() {
        tvTitle.setText("Khách Hàng");
        getSupportFragmentManager().beginTransaction().show(customerFragment).hide(productFragment).hide(billFragment).hide(mainFragment).commit();
    }
    private void showBillFragment() {
        tvTitle.setText("Hóa Đơn");
        getSupportFragmentManager().beginTransaction().show(billFragment).hide(productFragment).hide(customerFragment).hide(mainFragment).commit();
        billFragment.loadSoLuong();
    }

    private void initFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_main,productFragment,ProductFragment.class.getName())
                .add(R.id.layout_main,customerFragment,CustomerFragment.class.getName())
                .add(R.id.layout_main,billFragment,BillFragment.class.getName())
                .add(R.id.layout_main,mainFragment,MainFragment.class.getName()).commit();
    }

    private void setControl(){
        productFragment = new ProductFragment();
        customerFragment = new CustomerFragment();
        billFragment = new BillFragment();
        mainFragment = new MainFragment();


        btnHome = findViewById(R.id.btnHome);
        btnProduct = findViewById(R.id.btnProduct);
        btnCustomer = findViewById(R.id.btnCustomer);
        btnBill = findViewById(R.id.btnBill);
        tvTitle = findViewById(R.id.tvTitle);
    }
}