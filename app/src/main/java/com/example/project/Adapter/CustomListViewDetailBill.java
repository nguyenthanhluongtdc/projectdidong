package com.example.project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.project.Model.Bill;
import com.example.project.Model.DetailBill;
import com.example.project.R;

import java.util.ArrayList;
import java.util.List;

public class CustomListViewDetailBill extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Bill> listBill = new ArrayList<>();

    public CustomListViewDetailBill (@NonNull Context context, int resource,ArrayList<Bill> listBill) {
        super(context, resource, listBill);
        this.context = context;
        this.resource = resource;
        this.listBill = listBill;
    }

    public class Holder{
        ImageView imgBill;
        TextView tvCodeBill;
        TextView tvCodeCustomer;
        TextView tvTotalMoney;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Holder holder = null;
        if(convertView == null){
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(resource,null);
            holder.imgBill = convertView.findViewById(R.id.imgBilll);
            holder.tvCodeBill = convertView.findViewById(R.id.tvListCodeBilll);
            holder.tvCodeCustomer = convertView.findViewById(R.id.tvCodeKH);
            holder.tvTotalMoney = convertView.findViewById(R.id.tvTotalMoney);
            convertView.setTag(holder);
        }else holder = (Holder)convertView.getTag();

        Bill bill = listBill.get(position);

        holder.imgBill.setImageResource(R.drawable.bill);
        holder.tvCodeBill.setText(bill.getCodeBill());
        holder.tvCodeCustomer.setText(bill.getCodeCustomer());
        holder.tvTotalMoney.setText("Total: "+bill.getTotalMoney());

        return convertView;
    }
}

