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

import com.example.project.Model.Bill;
import com.example.project.Model.Product;
import com.example.project.R;

import java.util.ArrayList;
import java.util.List;

public class CustomListViewProduct extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Product> listProduct = new ArrayList<>();

    public CustomListViewProduct(@NonNull Context context, int resource,ArrayList<Product> listProduct) {
        super(context, resource, listProduct);
        this.context = context;
        this.resource = resource;
        this.listProduct = listProduct;
    }

    public class Holder{
        ImageView imgProduct;
        TextView tvCodeProduct;
        TextView tvNameProduct;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Holder holder = null;
        if(convertView == null){
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(resource,null);
            holder.imgProduct = convertView.findViewById(R.id.imgBilll);
            holder.tvCodeProduct = convertView.findViewById(R.id.tvListCodeBilll);
            holder.tvNameProduct = convertView.findViewById(R.id.tvDateBilll);
            convertView.setTag(holder);
        }else holder = (Holder)convertView.getTag();

        Product product = listProduct.get(position);

        holder.imgProduct.setImageBitmap(BitmapFactory.decodeByteArray(product.getImgProduct(),0,product.getImgProduct().length));
        holder.tvCodeProduct.setText(product.getCodeProduct());
        holder.tvNameProduct.setText(product.getNameProduct());

        return convertView;
    }
}

