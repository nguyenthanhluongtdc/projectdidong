package com.example.project.Interface.interfaceMain;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.project.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class MainFragment extends Fragment {
    private View rootView;
    LineChart lineChart;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main,container,false);
        lineChart = (LineChart)rootView.findViewById(R.id.chart);
        LineDataSet lineDataSet = new LineDataSet(dataValue(),"data set");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);
        LineData data = new LineData(dataSets);

        lineChart.setData(data);
        lineChart.invalidate();

        return rootView;
    }

    private ArrayList<Entry> dataValue(){
        ArrayList<Entry> data = new ArrayList<>();
        data.add(new Entry(0,10));
        data.add(new Entry(1,20));
        data.add(new Entry(2,7));
        data.add(new Entry(3,29));
        data.add(new Entry(4,14));
        return data;
    }

}
