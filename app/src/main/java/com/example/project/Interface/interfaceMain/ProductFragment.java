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

import com.example.project.Database.DBProduct;
import com.example.project.Interface.itemProduct.ActivityAddProduct;
import com.example.project.Interface.itemProduct.ActivityListProduct;
import com.example.project.R;

public class ProductFragment extends Fragment {
    private View rootView;
    Button btnAddProduct, btnListProduct;
    TextView tvAmountProduct;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_product,container,false);
        setControl();
        setEvent();

        return rootView;
    }

    private void setEvent() {
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ActivityAddProduct.class);
                startActivity(intent);
            }
        });
        btnListProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ActivityListProduct.class);
                startActivity(intent);
            }
        });
    }

    public void loadProduct(){
        DBProduct dbProduct = new DBProduct(getContext());
        tvAmountProduct.setText("Sản phẩm hiện có ("+dbProduct.getProducts().size()+")");
    }

    private void setControl() {
        btnAddProduct = rootView.findViewById(R.id.btnAddProduct);
        btnListProduct = rootView.findViewById(R.id.btnListProduct);
        tvAmountProduct = rootView.findViewById(R.id.numberProduct);
        loadProduct();

    }


}
