package com.example.project.Interface.itemBill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.project.Adapter.CustomListView;
import com.example.project.Database.DBBill;
import com.example.project.Model.Bill;
import com.example.project.Model.DetailBill;
import com.example.project.R;

import java.util.ArrayList;

public class ActivityListBill extends AppCompatActivity {
    ListView lvListHD;
    ArrayList<Bill> listBill = new ArrayList<>();
    ArrayList<DetailBill> detailBill = new ArrayList<>();
    TextView tvNumerHD;
    DBBill dbBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bill);
        setControl();
        setData();
        setEvent();
    }

    private void setEvent() {
        lvListHD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ActivityListBill.this, ActivityUpdateDeleteBill.class);
                Bundle bundle = new Bundle();
                bundle.putString("codeBill",listBill.get(i).getCodeBill());
                bundle.putString("codeCustomer",listBill.get(i).getCodeCustomer());
                bundle.putFloat("totalMoney",listBill.get(i).getTotalMoney());
                bundle.putString("date",listBill.get(i).getDateBill());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void setData() {
        listBill = dbBill.GetDataBill();
        CustomListView customListView = new CustomListView(this,R.layout.custom_listview_bill,listBill);
        lvListHD.setAdapter(customListView);
        customListView.notifyDataSetChanged();

        tvNumerHD.setText(listBill.size() +" hoá đơn");
    }

    private void setControl() {
        lvListHD = findViewById(R.id.lvListHD);
        tvNumerHD = findViewById(R.id.tvNumberHD);
        dbBill = new DBBill(this);
    }
}