package com.example.grybos.dreamcar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //zmienne
    private ListView listView;
    private ImageView add;
    private ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        listView = findViewById(R.id.list1);
        add = findViewById(R.id.add);

        CustomAdapter adapter = new CustomAdapter(
                MainActivity.this,
                R.layout.listview_layout,
                list
        );
        listView.setAdapter(adapter);

    }
}
