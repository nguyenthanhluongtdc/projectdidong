package com.example.project.Interface.interfaceMain;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.project.Interface.itemBill.ActivityAddBill;
import com.example.project.Interface.itemBill.ActivityListBill;
import com.example.project.R;

public class BillFragment extends Fragment {
    private View rootView;
    Button btnAddBill, btnDetailBill, btnListBill;

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
    }

    private void setControl() {
        btnAddBill = rootView.findViewById(R.id.btnAddBill);
        btnDetailBill = rootView.findViewById(R.id.btnDetailBill);
        btnListBill = rootView.findViewById(R.id.btnListBill);
    }


}
