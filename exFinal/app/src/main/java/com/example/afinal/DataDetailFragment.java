package com.example.afinal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.afinal.models.ComputerModel;

public class DataDetailFragment extends Fragment {
    private static String serial, description, brand;
    private boolean active;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data_detail, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        TextView tv_data_detail_serial, tv_data_detail_description, tv_data_detail_brand;

        tv_data_detail_serial = view.findViewById(R.id.tv_data_detail_serial);
        tv_data_detail_description = view.findViewById(R.id.tv_data_detail_description);
        tv_data_detail_brand = view.findViewById(R.id.tv_data_detail_brand);

        tv_data_detail_serial.setText(serial);
        tv_data_detail_description.setText(description);
        tv_data_detail_brand.setText(brand);




        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(DataDetailFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }
    static void recivedata(Bundle bundle){
        ComputerModel model = (ComputerModel) bundle.getSerializable("model");
        if (model != null){
            serial = model.getSerial();
            description = model.getDescription();
            brand = model.getBrand();
        }
    }
}