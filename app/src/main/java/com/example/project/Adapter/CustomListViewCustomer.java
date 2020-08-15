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
import com.example.project.Model.Customer;
import com.example.project.Model.Product;
import com.example.project.R;

import java.util.ArrayList;
import java.util.List;

public class CustomListViewCustomer extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Customer> listCustomer = new ArrayList<>();

    public CustomListViewCustomer(@NonNull Context context, int resource,ArrayList<Customer> listCustomer) {
        super(context, resource, listCustomer);
        this.context = context;
        this.resource = resource;
        this.listCustomer = listCustomer;
    }

    public class Holder{
        ImageView imgUser;
        TextView tvCodeCustomer;
        TextView tvNameCustomer;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Holder holder = null;
        if(convertView == null){
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(resource,null);
            holder.imgUser = convertView.findViewById(R.id.imgBilll);
            holder.tvCodeCustomer = convertView.findViewById(R.id.tvListCodeBilll);
            holder.tvNameCustomer = convertView.findViewById(R.id.tvDateBilll);
            convertView.setTag(holder);
        }else holder = (Holder)convertView.getTag();

        Customer customer = listCustomer.get(position);

        holder.imgUser.setImageResource(R.drawable.ic_baseline_person_24);
        holder.tvCodeCustomer.setText(customer.getCodeCustomer());
        holder.tvNameCustomer.setText(customer.getNameCustomer());

        return convertView;
    }
}

