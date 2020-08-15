package com.example.project.Interface.interfaceMain;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.project.Database.DBBill;
import com.example.project.Interface.itemBill.ActivityAddBill;
import com.example.project.Interface.itemBill.ActivityDetailBill;
import com.example.project.Interface.itemBill.ActivityListBill;
import com.example.project.R;

import java.util.ArrayList;

public class BillFragment extends Fragment {
    private View rootView;
    Button btnAddBill, btnDetailBill, btnListBill;
    TextView tvAmountBill;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_bill,container,false);
        setControl();
        setEvent();

        return rootView;
    }

    private void setEvent() {
        btnAddBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ActivityAddBill.class);
                startActivity(intent);
            }
        });
        btnListBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ActivityListBill.class);
                startActivity(intent);
            }
        });
        btnDetailBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ActivityDetailBill.class);
                startActivity(intent);
            }
        });
    }

    public void loadSoLuong(){
        DBBill  dbBill = new DBBill(getContext());
        tvAmountBill.setText("Hóa đơn hiện có ("+dbBill.GetDataBill().size()+")");
    }

    private void setControl() {
        btnAddBill = rootView.findViewById(R.id.btnAddBill);
        btnDetailBill = rootView.findViewById(R.id.btnDetailBill);
        btnListBill = rootView.findViewById(R.id.btnListBill);
        tvAmountBill = rootView.findViewById(R.id.numberBill);
        loadSoLuong();
    }


}
