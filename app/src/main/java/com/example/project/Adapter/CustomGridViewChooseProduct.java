package com.example.project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.project.Model.DetailBill;
import com.example.project.Model.Product;
import com.example.project.R;

import java.util.ArrayList;

public class CustomGridViewChooseProduct extends ArrayAdapter<DetailBill> {
    Context context;
    int resource;
    ArrayList<DetailBill> listDetailProduct ;

    public CustomGridViewChooseProduct( Context context, int resource,  ArrayList<DetailBill> listDetailProduct) {
        super(context, resource, listDetailProduct);
        this.context = context;
        this.resource = resource;
        this.listDetailProduct = listDetailProduct;
    }

    public class Holder {
        TextView tvCodeProduct;
        TextView tvAmount;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Holder holder = null;
        if(convertView == null){
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(resource,null);
            holder.tvCodeProduct = convertView.findViewById(R.id.tvCodeProduct);
            holder.tvAmount = convertView.findViewById(R.id.tvAmountChooseProduct);
            convertView.setTag(holder);
        }else holder = (Holder) convertView.getTag();
        DetailBill str = listDetailProduct.get(position);
        holder.tvCodeProduct.setText(str.getCodeProduct() + "");
        holder.tvAmount.setText(str.getAmount() + "");
        return convertView;
    }
}
