package com.example.afinal;

import android.os.Bundle;

import com.example.afinal.adapters.ComputerAdapter;
import com.example.afinal.models.ComputerModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends BaseActivity {

    private FloatingActionButton fab_list_create;
    private ListView lv_list_comida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        super.init();
        init();


        fab_list_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goCreate();
            }
        });

        lv_list_comida.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                model = modelArrayList.get(position);
              //  makeSimpleAlertDialog("abrindo","comida : "  + model.getSerial());
                goToDetail(model);
            }
        });
    }

    protected void  init(){
        fab_list_create = findViewById(R.id.fab_list_create);
        lv_list_comida = findViewById(R.id.lv_list_comida);

    }
    protected void  getcomida(){
        if (collectionReference != null){
            collectionReference.get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()){
                                if(task.getResult() != null){
                                    modelArrayList = new ArrayList<>();
                                    for (QueryDocumentSnapshot snapshot : task.getResult()){
                                        model = snapshot.toObject(ComputerModel.class);
                                        modelArrayList.add(model);

                                    }
                                    if (modelArrayList.size() > 0){
                                        paintComputers(modelArrayList);

                                    }else {
                                        makeSimpleAlertDialog("ojo pa","No hay nada que mostrar");
                                    }

                                }else {
                                    makeSimpleAlertDialog("peligro","f en el chat");
                                }

                            }else {
                                makeSimpleAlertDialog("error", task.getException().getMessage());

                            }

                        }
                    });
        }else {
            makeSimpleToast("error", 1);
        }

    }
    protected void paintComputers(ArrayList<ComputerModel>modelArrayList){
        adapter = new ComputerAdapter(this,modelArrayList);
        lv_list_comida.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getcomida();
    }
}