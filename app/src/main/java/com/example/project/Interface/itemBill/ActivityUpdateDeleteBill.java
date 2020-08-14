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

public class ActivityUpdateDeleteBill extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    Button btnDelete, btnUpdate, btnAddProduct, btnDateNgayLap;
    TextView tvCodeHD, tvPrice, tvThanhTien, tvTotalMoney, tvDateNgayLapUpdate;
    NumberPicker numberAmout;
    Spinner spCodeCustomer, spNameProduct;
    GridView gvProduct;
    DBDetailBill dbDetailBill;
    DBProduct dbProduct;
    DBBill dbBill;
    int index = 0;
    int indexDetail = -1;
    float totalMoney;
    float price;
    float totalThanhTien;
    Product product1;

    ArrayList <Customer> listCustomer = new ArrayList<>();
    ArrayList <String> listCodeCustomer = new ArrayList<>();
    ArrayList<DetailBill> listDetailBill = new ArrayList<>();
    ArrayList<Product> listProduct = new ArrayList<>();
    ArrayList<DetailBill> chooseProduct = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_bill);
        setControl();
        setData();
        setEvent();
    }

    private void setEvent() {
        btnDateNgayLap.setOnClickListener(new View.OnClickListener() {
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
                tvThanhTien.setText(price + "");
                numberAmout.setValue(1);
                index = i;
                product1 = product;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        numberAmout.setValueChangedListener(new ValueChangedListener() {
            @Override
            public void valueChanged(int value, ActionEnum action) {
                Product product = listProduct.get(index);
                float result = product.getPrice() * value;
                tvThanhTien.setText(result + "");
            }
        });

        gvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                spNameProduct.setSelection(chooseProduct.indexOf(chooseProduct.get(i).getNameProduct()));
                tvPrice.setText(chooseProduct.get(i).getPrice()+"");
                tvThanhTien.setText(chooseProduct.get(i).getTotalMoney()+"");
                numberAmout.setValue(chooseProduct.get(i).getAmount());
                indexDetail = i;
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
                totalThanhTien = Float.parseFloat(tvThanhTien.getText().toString());
                chooseProduct.add(new DetailBill(tvCodeHD.getText().toString()
                        ,product1.getCodeProduct(),product1.getNameProduct()
                        ,numberAmout.getValue(), price, totalThanhTien));
                //grid view
                setDataGridView();
                //tinh tong tien
                totalMoney = Float.parseFloat(tvThanhTien.getText().toString()) +
                        Float.parseFloat(tvTotalMoney.getText().toString());
                tvTotalMoney.setText(totalMoney + "");
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chooseProduct.size() == 0){
                    Toast.makeText(ActivityUpdateDeleteBill.this,"Vui lòng thêm sản phẩm",Toast.LENGTH_LONG).show();
                }else {
                    Bill bill = new Bill(tvCodeHD.getText().toString(),
                            spCodeCustomer.getSelectedItem().toString(),
                            tvDateNgayLapUpdate.getText().toString(),
                            totalMoney);
                    //insert detailBill database
                    for(DetailBill detailBill : chooseProduct){
                        dbDetailBill.UpdateBill(detailBill);
                    }
                    //insert bill database
                    dbBill.UpdateBill(bill);
                    Toast.makeText(ActivityUpdateDeleteBill.this, "Successfuly", Toast.LENGTH_SHORT).show();
                }

                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbBill.DeleteBill(tvCodeHD.getText().toString());
                dbDetailBill.DeleteDetailBill(tvCodeHD.getText().toString());
                Toast.makeText(ActivityUpdateDeleteBill.this, "Successfuly",Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    private void setData() {
        listCustomer.add(new Customer("KH01","thi a","TPHCM","08898239823"));
        listCustomer.add(new Customer("KH02","thi b","HA NOI","0873474744"));
        listCustomer.add(new Customer("KH03","thi c","NINH THUAN","098984445"));
        for(Customer cus: listCustomer){
            listCodeCustomer.add(cus.getCodeCustomer());
        }

        listProduct = dbProduct.getProducts();
        CustomSpiner customSpiner = new CustomSpiner(this,listProduct);
        spNameProduct.setAdapter(customSpiner);
        customSpiner.notifyDataSetChanged();


        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listCodeCustomer);
        spCodeCustomer.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        Bundle bundle = getIntent().getExtras();
        String maHD = bundle.getString("codeBill");
        String maKH = bundle.getString("codeCustomer");
        chooseProduct = dbDetailBill.GetCodeBill(maHD);

        tvCodeHD.setText(maHD);
        spCodeCustomer.setSelection(listCodeCustomer.indexOf(maKH));
        tvTotalMoney.setText(bundle.getFloat("totalMoney")+"");
        tvDateNgayLapUpdate.setText(bundle.getString("date"));
        setDataGridView();

    }
    private void setDataGridView(){
        CustomGridViewChooseProduct customGridView = new CustomGridViewChooseProduct(this, R.layout.custom_gridview_chooseproduct,chooseProduct);
        gvProduct.setAdapter(customGridView);
        customGridView.notifyDataSetChanged();
    }


    private void setControl() {
        btnDelete = findViewById(R.id.btnDeleteHD);
        btnAddProduct = findViewById(R.id.btnAddProduct);
        btnUpdate = findViewById(R.id.btnUpdateHD);
        btnDateNgayLap = findViewById(R.id.btnDateNgayLap);
        spCodeCustomer = findViewById(R.id.spMaKH);
        spNameProduct = findViewById(R.id.spTenSanPham);
        tvCodeHD = findViewById(R.id.tvCodeHD);
        tvPrice = findViewById(R.id.resultDonGia);
        tvThanhTien = findViewById(R.id.resultThanhTien);
        tvDateNgayLapUpdate = findViewById(R.id.tvDateNgayLapUpdate);
        numberAmout = findViewById(R.id.resultSoLuong);
        tvTotalMoney = findViewById(R.id.tvTotalMoney);
        gvProduct = findViewById(R.id.gvProduct);

        dbDetailBill = new DBDetailBill(this);
        dbProduct = new DBProduct(this);
        dbBill = new DBBill(this);
    }

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        tvDateNgayLapUpdate.setText(currentDateString);
    }
}