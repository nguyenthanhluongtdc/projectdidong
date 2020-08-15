package com.example.project.Interface.itemBill;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.project.Adapter.CustomListViewDetailBill;
import com.example.project.Database.DBBill;
import com.example.project.Model.Bill;
import com.example.project.R;

import java.util.ArrayList;

public class ActivityDetailBill extends AppCompatActivity {
    ListView lvDetailBill;
    DBBill dbBill;
    ArrayList<Bill> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bill);
        dbBill = new DBBill(this);
        list = dbBill.GetDataBill();

        lvDetailBill = findViewById(R.id.lvDetailBill);
        CustomListViewDetailBill customListViewDetailBill =
                new CustomListViewDetailBill(this,R.layout.custom_detail_bill,list);
        lvDetailBill.setAdapter(customListViewDetailBill);
    }
}