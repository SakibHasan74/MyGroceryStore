package com.example.mygrocerystore.adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toolbar;

import com.example.mygrocerystore.R;
import com.example.mygrocerystore.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {
    FirebaseFirestore firestore;


    RecyclerView recyclerView;
    ViewAllAdapter viewAllAdapter;
    List< ViewAllModel > viewAllModelList;
//     Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        firestore = FirebaseFirestore.getInstance();

        String type = getIntent().getStringExtra("type");
        recyclerView = findViewById(R.id.view_all_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewAllModelList = new ArrayList<>();
        viewAllAdapter = new ViewAllAdapter(this, viewAllModelList);
        recyclerView.setAdapter(viewAllAdapter);

        ///////Getting Fruits

        if (type != null && type.equalsIgnoreCase("fruit")) {

            firestore.collection("AllProducts").whereEqualTo("type", "fruit").get().addOnCompleteListener(new OnCompleteListener< QuerySnapshot >() {
                @Override
                public void onComplete(@NonNull @NotNull Task< QuerySnapshot > task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
        ///////Getting Vegetable

        if (type != null && type.equalsIgnoreCase("vegetable")) {

            firestore.collection("AllProducts").whereEqualTo("type", "vegetable").get().addOnCompleteListener(new OnCompleteListener< QuerySnapshot >() {
                @Override
                public void onComplete(@NonNull @NotNull Task< QuerySnapshot > task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
        ///////Getting fish

        if (type != null && type.equalsIgnoreCase("fish")) {

            firestore.collection("AllProducts").whereEqualTo("type", "fish").get().addOnCompleteListener(new OnCompleteListener< QuerySnapshot >() {
                @Override
                public void onComplete(@NonNull @NotNull Task< QuerySnapshot > task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
        ///////Getting eggs

        if (type != null && type.equalsIgnoreCase("egg")) {

            firestore.collection("AllProducts").whereEqualTo("type", "egg").get().addOnCompleteListener(new OnCompleteListener< QuerySnapshot >() {
                @Override
                public void onComplete(@NonNull @NotNull Task< QuerySnapshot > task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
        ///////Getting milk

        if (type != null && type.equalsIgnoreCase("milk")) {

            firestore.collection("AllProducts").whereEqualTo("type", "milk").get().addOnCompleteListener(new OnCompleteListener< QuerySnapshot >() {
                @Override
                public void onComplete(@NonNull @NotNull Task< QuerySnapshot > task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
    }}


