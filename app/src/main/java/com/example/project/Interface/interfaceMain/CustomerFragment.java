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

import com.example.project.Database.DBCustomer;
import com.example.project.Interface.itemCustomer.ActivityAddCustomer;
import com.example.project.Interface.itemCustomer.ActivityListCustomer;
import com.example.project.R;

public class CustomerFragment extends Fragment {
    private View rootView;
    Button btnAddCustomer, btnListCustomer;
    TextView tvNumberCustomer;
    DBCustomer dbCustomer;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_customer,container,false);
        setControl();
        setEvent();
        return rootView;
    }

    private void setEvent() {
        btnAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ActivityAddCustomer.class);
                startActivity(intent);
            }
        });
        btnListCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ActivityListCustomer.class);
                startActivity(intent);
            }
        });
    }

    private void setControl() {
        btnAddCustomer = rootView.findViewById(R.id.btnAddCustomer);
        btnListCustomer = rootView.findViewById(R.id.btnListCustomer);
        tvNumberCustomer = rootView.findViewById(R.id.numberCustomer);
        dbCustomer = new DBCustomer(getContext());
        tvNumberCustomer.setText("Khách hàng hiện có ("+dbCustomer.getCustomer().size()+")");
    }
}
