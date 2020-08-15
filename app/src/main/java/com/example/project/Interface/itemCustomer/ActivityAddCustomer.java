package com.example.project.Interface.itemCustomer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project.Database.DBCustomer;
import com.example.project.Model.Customer;
import com.example.project.R;

public class ActivityAddCustomer extends AppCompatActivity {

    Button btnAddCustomer;
    EditText txtMaKH, txtTenKH, txtDiaChi, txtSDT;
    DBCustomer dbCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Customer customer = new Customer(txtMaKH.getText().toString(),
                                                    txtTenKH.getText().toString(),
                                                        txtDiaChi.getText().toString(),
                                                            txtSDT.getText().toString());
                dbCustomer.insert(customer);
                Toast.makeText(ActivityAddCustomer.this,"Thêm thành công",Toast.LENGTH_LONG).show();

                txtMaKH.setHint("Mã khách hàng");
                txtTenKH.setHint("Tên khách hàng");
                txtDiaChi.setHint("Địa chỉ");
                txtSDT.setHint("SDT");
            }
        });
    }

    private void setControl() {
        btnAddCustomer = findViewById(R.id.btnAddCustomer);
        txtMaKH = findViewById(R.id.txtMaKH);
        txtTenKH = findViewById(R.id.txtTenKH);
        txtDiaChi = findViewById(R.id.txtDiaChi);
        txtSDT = findViewById(R.id.txtSDT);
        dbCustomer = new DBCustomer(this);
    }
}