package com.example.project.Adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.project.Model.Product;
import com.example.project.R;

import java.util.ArrayList;
public class CustomSpiner extends ArrayAdapter<Product> {

    public CustomSpiner(@NonNull Context context,  ArrayList<Product> listProduct) {
        super(context, 0, listProduct);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return init(position,convertView,parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        return init(position,convertView,parent);
    }

    public View init(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_spiner_product,parent,false);

        }
        ImageView imgProduct = convertView.findViewById(R.id.imgProduct);
        TextView tvNameProduct = convertView.findViewById(R.id.tvNameProduct);

        final Product product = getItem(position);
        if(product != null){
            imgProduct.setImageBitmap(BitmapFactory.decodeByteArray(product.getImgProduct(),0,product.getImgProduct().length));
            tvNameProduct.setText(product.getNameProduct());
        }

        return convertView;
    }
}
