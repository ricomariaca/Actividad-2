package com.example.afinal;

import android.os.Bundle;

import com.example.afinal.models.ComputerModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentReference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class CreateActivity extends BaseActivity {

    FloatingActionButton fab_create_save, fab_create_clear, fab_create_back;
    ImageView iv_create_image;
    TextView tv_create_click_image;
    EditText et_create_serial, et_create_brand, et_create_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        super.init();
        init();

        fab_create_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToList();
            }
        });


        fab_create_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               clear();
            }
        });
        fab_create_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String serial, description, brand;
                boolean active;

                serial = et_create_serial.getText().toString();
                description = et_create_description.getText().toString();
                brand = et_create_brand.getText().toString();

                if (serial.isEmpty() || description.isEmpty() || brand.isEmpty()){
                    makeSimpleAlertDialog("Alerta", "Porfavor llene todos los datos.");
                }else {
                    model = new ComputerModel();
                    model.setActive(true);
                    model.setDescription(description);
                    model.setBrand(brand);
                    model.setSerial(serial);
                    
                    save(model);

                }
            }
        });
    }

    private void save(ComputerModel model) {
        if (collectionReference != null){
            collectionReference.add(model)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if (task.isSuccessful()){
                                if (task.getResult() != null){
                                    makeSimpleAlertDialog("hola bb que pas pues","Datos guardados correcta mente");

                                }else {
                                    makeSimpleAlertDialog("ojo pa","Los datos no se guardaron correctamente");

                                }


                            }else {
                                makeSimpleAlertDialog("Error","No hay conexion con la base de datos");
                            }
                        }
                    });

        }else {
            makeSimpleAlertDialog("error", "No hay conxion .");
        }
    }

    protected void init(){
        fab_create_back = findViewById(R.id.fab_create_back);
        fab_create_clear = findViewById(R.id.fab_create_clear);
        fab_create_save = findViewById(R.id.fab_create_save);
        iv_create_image = findViewById(R.id.iv_create_image);
        tv_create_click_image = findViewById(R.id.tv_create_click_image);
        et_create_serial = findViewById(R.id.et_create_serial);
        et_create_brand = findViewById(R.id.et_create_brand);
        et_create_description = findViewById(R.id.et_create_description);

    }
    private void clear (){
        et_create_brand.setText("");
        et_create_description.setText("");
        et_create_serial.setText("");
        et_create_serial.requestFocus();
        iv_create_image.setImageResource(R.drawable.ic_fastfood_black_18dp);
    }

}
