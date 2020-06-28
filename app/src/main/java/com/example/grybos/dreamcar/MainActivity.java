package com.example.grybos.dreamcar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.amitshekhar.DebugDB;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //zmienne
    private ListView listView;
    private ImageView add;
    private ArrayList<Record> list = new ArrayList<>();
    private File pic;
    public static File dir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        Log.d("xxx", DebugDB.getAddressLog());

        listView = findViewById(R.id.list1);
        add = findViewById(R.id.add);

        pic = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        if (!pic.exists()){

            pic.mkdir();

        }

        dir = new File(pic, "DreamCar");
        dir.mkdir();

        DatabaseManager db = new DatabaseManager(
                MainActivity.this,
                "Samochody.db",
                null,
                1
        );

        list.clear();

        list = db.getAll();

        CustomAdapter adapter = new CustomAdapter(
                MainActivity.this,
                R.layout.listview_layout,
                list,
                db
        );
        listView.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                intent.putExtra("key", 0);
                startActivity(intent);

            }
        });

    }

    private void refresh(){

        DatabaseManager db = new DatabaseManager(
                MainActivity.this,
                "Samochody.db",
                null,
                1
        );

        list.clear();

        list = db.getAll();

        CustomAdapter adapter = new CustomAdapter(
                MainActivity.this,
                R.layout.listview_layout,
                list,
                db
        );
        listView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();

        refresh();

    }
}
