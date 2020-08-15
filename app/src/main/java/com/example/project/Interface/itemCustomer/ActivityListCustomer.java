package com.example.project.Interface.itemCustomer;

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
import com.example.project.Adapter.CustomListViewCustomer;
import com.example.project.Adapter.CustomListViewProduct;
import com.example.project.Database.DBCustomer;
import com.example.project.Database.DBProduct;
import com.example.project.Interface.itemBill.ActivityListBill;
import com.example.project.Interface.itemBill.ActivityUpdateDeleteBill;
import com.example.project.Interface.itemCustomer.ActivityUpdateDeleteCustomer;
import com.example.project.Model.Bill;
import com.example.project.Model.Customer;
import com.example.project.Model.Product;
import com.example.project.R;

import java.util.ArrayList;

public class ActivityListCustomer extends AppCompatActivity {
    TextView tvNumerKH;
    ListView lvListKH;
    ArrayList <Customer> listCustomer = new ArrayList<>();
    DBCustomer dbCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_customer);
        setControl();
        setData();
        setEvent();
    }

    private void setEvent() {
        lvListKH.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Customer customer = (Customer) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(ActivityListCustomer.this, ActivityUpdateDeleteCustomer.class);
                Bundle bundle = new Bundle();
                bundle.putString("code",customer.getCodeCustomer());
                bundle.putString("name",customer.getNameCustomer());
                bundle.putString("address",customer.getAddRess());
                bundle.putString("sdt",customer.getNumberPhone());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    public void setData() {
        listCustomer = dbCustomer.getCustomer();

        CustomListViewCustomer customListViewCustomer = new CustomListViewCustomer(this,R.layout.custom_listview_bill,listCustomer);
        lvListKH.setAdapter(customListViewCustomer);
        customListViewCustomer.notifyDataSetChanged();

        tvNumerKH.setText(listCustomer.size() +" Khách hàng");
    }

    private void setControl() {
        lvListKH = findViewById(R.id.lvListKH);
        tvNumerKH = findViewById(R.id.tvNumberKH);
        dbCustomer = new DBCustomer(this);
    }
}