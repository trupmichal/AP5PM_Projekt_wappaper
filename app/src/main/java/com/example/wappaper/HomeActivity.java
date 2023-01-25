package com.example.wappaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4000;
    private ArrayList<class_img_model> modelClassList;
    private RecyclerView recyclerView;
    Adapter adapter;
    CardView _new,_3d,_architecture,_cars,_people;
    EditText editText;
    ImageButton search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        recyclerView=findViewById(R.id.recycler_view);
        _new = findViewById(R.id.button_new);
        _3d = findViewById(R.id.button_3D);
        _architecture = findViewById(R.id.button_architecture);
        _cars = findViewById(R.id.button_cars);
        _people = findViewById(R.id.button_people);
        editText = findViewById(R.id.search_text);
        search = findViewById(R.id.search_image);

        modelClassList = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);
        adapter = new Adapter(getApplicationContext(),modelClassList);
        recyclerView.setAdapter(adapter);
        findphotos();

        _new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findphotos();
            }
        });

        _3d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="3D";
                getsearchimage(query);
            }
        });

        _architecture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="Architecture";
                getsearchimage(query);
            }
        });

        _cars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="car";
                getsearchimage(query);
            }
        });

        _people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="people";
                getsearchimage(query);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query=editText.getText().toString().trim().toLowerCase();
                if(query.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Before searching, please enter a keyword", Toast.LENGTH_SHORT).show();
                }
                else {
                    getsearchimage(query);
                }
            }
        });


    }

    private void getsearchimage(String query) {
        ApiUtilities.getApiInterface().getSearchImage(query,1,80).enqueue(new Callback<class_search_model>() {
            @Override
            public void onResponse(Call<class_search_model> call, Response<class_search_model> response) {
                modelClassList.clear();
                if (response.isSuccessful()) {
                    modelClassList.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Couldn't find what you were looking for...",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<class_search_model> call, Throwable t) {

            }
        });
    }

    private void findphotos() {

        ApiUtilities.getApiInterface().getImage(1, 80).enqueue(new Callback<class_search_model>() {
            @Override
            public void onResponse(Call<class_search_model> call, Response<class_search_model> response) {
                if (response.isSuccessful()) {
                    modelClassList.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Couldn't find what you were looking for...",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<class_search_model> call, Throwable t) {

            }
        });

    }
}