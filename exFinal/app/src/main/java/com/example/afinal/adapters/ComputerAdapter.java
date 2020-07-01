package com.example.afinal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.afinal.R;
import com.example.afinal.models.ComputerModel;

import java.util.ArrayList;

public class ComputerAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ComputerModel> modelArrayList;

    public ComputerAdapter(Context context, ArrayList<ComputerModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @Override
    public int getCount() {
        return modelArrayList.size();
    }

    @Override
    public ComputerModel getItem(int position) {
        return modelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(R.layout.computer_list_item, parent, false);
        }
        TextView tv_computer_list_item_description = convertView.findViewById(R.id.tv_computer_list_item_description);
        TextView tv_computer_list_item_serial = convertView.findViewById(R.id.tv_computer_list_item_serial);

        tv_computer_list_item_description.setText(getItem(position).getDescription());
        tv_computer_list_item_serial.setText(getItem(position).getSerial());
        
        return convertView;
    }
}
