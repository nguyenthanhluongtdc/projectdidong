package com.example.project.Interface.itemBill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.Adapter.CustomGridViewChooseProduct;
import com.example.project.Adapter.CustomSpiner;
import com.example.project.Database.DBBill;
import com.example.project.Database.DBDetailBill;
import com.example.project.Database.DBProduct;
import com.example.project.Model.Bill;
import com.example.project.Model.Customer;
import com.example.project.Model.DetailBill;
import com.example.project.Model.Product;
import com.example.project.R;
import com.travijuu.numberpicker.library.Enums.ActionEnum;
import com.travijuu.numberpicker.library.Interface.ValueChangedListener;
import com.travijuu.numberpicker.library.NumberPicker;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ActivityAddBill extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    ArrayList <Customer> listCustomer = new ArrayList<>();
    ArrayList <String> listCodeCustomer = new ArrayList<>();
    ArrayList <Product> listProduct = new ArrayList<>();
    ArrayList <String> listCodeProduct = new ArrayList<>();
    private EditText txtCodeBill;
    private Spinner spCodeCustomer;
    private Spinner spNameProduct;
    TextView tvPrice, tvThanhTienMoney, tvDateNgayLap, tvTotalMoney;
    NumberPicker numberPicker;
    int index;
    int indexDetail = -1;
    Button btnDatePicker, btnAddProduct, btnCreateBill;
    Product product1;
    ArrayList<DetailBill> chooseProduct = new ArrayList<>();
    GridView gvProduct;
    float totalMoney;
    float price;
    float totalThanhTien;
    DBProduct dbProduct;
    DBBill dbBill;
    DBDetailBill dbDetailBill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bill);
        setControl();
        setData();
        setEvent();
    }

    private void setEvent() {
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        spNameProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Product product = (Product) adapterView.getItemAtPosition(i);
                float price = product.getPrice();
                tvPrice.setText(price + "");
                tvThanhTienMoney.setText(price + "");
                numberPicker.setValue(1);
                index = i;
                product1 = product;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        numberPicker.setValueChangedListener(new ValueChangedListener() {
            @Override
            public void valueChanged(int value, ActionEnum action) {
                Product product = listProduct.get(index);
                float result = product.getPrice() * value;
                tvThanhTienMoney.setText(result + "");
            }
        });
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //object detailBill
                if(indexDetail != -1){
                    chooseProduct.remove(indexDetail);
                }
                price = Float.parseFloat(tvPrice.getText().toString());
                totalThanhTien = Float.parseFloat(tvThanhTienMoney.getText().toString());
                chooseProduct.add(new DetailBill(txtCodeBill.getText().toString()
                        ,product1.getCodeProduct(),product1.getNameProduct()
                        ,numberPicker.getValue(), price, totalThanhTien));
                //grid view
                setDataGridView();
                //tinh tong tien
                totalMoney = Float.parseFloat(tvThanhTienMoney.getText().toString()) +
                        Float.parseFloat(tvTotalMoney.getText().toString());
                tvTotalMoney.setText(totalMoney + "");

                //object bill


            }
        });
        btnCreateBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chooseProduct.size() == 0){
                    Toast.makeText(ActivityAddBill.this,"Vui lòng thêm sản phẩm",Toast.LENGTH_LONG).show();
                }else {
                    Bill bill = new Bill(txtCodeBill.getText().toString(),
                            spCodeCustomer.getSelectedItem().toString(),
                            tvDateNgayLap.getText().toString(),
                            totalMoney);
                    //insert detailBill database
                    for(DetailBill detailBill : chooseProduct){
                        dbDetailBill.InsertDetailBill(detailBill);
                    }
                    //insert bill database
                    dbBill.InsertBill(bill);
                    Toast.makeText(ActivityAddBill.this, "Successfuly", Toast.LENGTH_SHORT).show();
                }
            }
        });

        gvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                spNameProduct.setSelection(chooseProduct.indexOf(chooseProduct.get(i).getNameProduct()));
                tvPrice.setText(chooseProduct.get(i).getPrice()+"");
                tvThanhTienMoney.setText(chooseProduct.get(i).getTotalMoney()+"");
                numberPicker.setValue(chooseProduct.get(i).getAmount());
                indexDetail = i;
            }
        });
    }

    private void setDataGridView(){
        CustomGridViewChooseProduct customGridView = new CustomGridViewChooseProduct(this, R.layout.custom_gridview_chooseproduct,chooseProduct);
        gvProduct.setAdapter(customGridView);
        customGridView.notifyDataSetChanged();
    }

    private void setData() {
        listCustomer.add(new Customer("KH01","thi a","TPHCM","08898239823"));
        listCustomer.add(new Customer("KH02","thi b","HA NOI","0873474744"));
        listCustomer.add(new Customer("KH03","thi c","NINH THUAN","098984445"));
        for(Customer cus: listCustomer){
            listCodeCustomer.add(cus.getCodeCustomer());
        }

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listCodeCustomer);
        spCodeCustomer.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listProduct = dbProduct.getProducts();
        CustomSpiner customSpiner = new CustomSpiner(this,listProduct);
        spNameProduct.setAdapter(customSpiner);
        customSpiner.notifyDataSetChanged();
    }


    private void setControl() {
        txtCodeBill = findViewById(R.id.txtMaHD);
        spCodeCustomer = findViewById(R.id.spMaKH);
        spNameProduct = findViewById(R.id.spTenSanPham);
        tvPrice = findViewById(R.id.resultDonGia);
        tvDateNgayLap = findViewById(R.id.tvDateNgayLap);
        tvTotalMoney = findViewById(R.id.tvTotalMoney);
        numberPicker = findViewById(R.id.resultSoLuong);
        tvThanhTienMoney = findViewById(R.id.resultThanhTien);
        btnDatePicker = findViewById(R.id.btnDateNgayLap);
        btnAddProduct = findViewById(R.id.btnAddProduct);
        btnCreateBill = findViewById(R.id.btnCreateHD);
        gvProduct = findViewById(R.id.gvProduct);
        dbProduct = new DBProduct(this);
        dbBill = new DBBill(this);
        dbDetailBill = new DBDetailBill(this);
    }

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        tvDateNgayLap.setText(currentDateString);
    }
}