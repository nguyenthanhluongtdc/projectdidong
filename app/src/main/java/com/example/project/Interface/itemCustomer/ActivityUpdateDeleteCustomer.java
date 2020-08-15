package com.example.project.Interface.itemCustomer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.Database.DBCustomer;
import com.example.project.Model.Customer;
import com.example.project.R;

public class ActivityUpdateDeleteCustomer extends AppCompatActivity {
    Button btnDeleteCustomer, btnUpdateCustomer;
    EditText  txtTenKH, txtDiaChi, txtSDT;
    TextView tvMaKH;
    DBCustomer dbCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_customer);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnDeleteCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbCustomer.delete(tvMaKH.getText().toString());
                Toast.makeText(ActivityUpdateDeleteCustomer.this,"Xóa thành công",Toast.LENGTH_LONG).show();
                finish();
            }
        });

        btnUpdateCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Customer customer = new Customer(tvMaKH.getText().toString(),
                        txtTenKH.getText().toString(),
                        txtDiaChi.getText().toString(),
                        txtSDT.getText().toString());
                dbCustomer.update(customer);
                Toast.makeText(ActivityUpdateDeleteCustomer.this,"Sửa thành công",Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    private void setData(){
        Bundle bundle = getIntent().getExtras();
        String ma = bundle.getString("code");
        String ten = bundle.getString("name");
        String address = bundle.getString("address");
        String sdt = bundle.getString("sdt");

        tvMaKH.setText(ma);
        txtTenKH.setText(ten);
        txtDiaChi.setText(address);
        txtSDT.setText(sdt);
    }

    private void setControl() {
        btnDeleteCustomer = findViewById(R.id.btnDeleteCustomer);
        btnUpdateCustomer = findViewById(R.id.btnUpdateCustomer);
        tvMaKH = findViewById(R.id.tvMaKH);
        txtTenKH = findViewById(R.id.txtTenKH);
        txtDiaChi = findViewById(R.id.txtDiaChi);
        txtSDT = findViewById(R.id.txtSDT);
        dbCustomer = new DBCustomer(this);

        setData();
    }
}