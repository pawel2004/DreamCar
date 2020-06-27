package com.example.grybos.dreamcar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

public class CreateActivity extends AppCompatActivity {

    //zmienne
    private ImageView image;
    private EditText name;
    private EditText year;
    private EditText power;
    private EditText engine;
    private EditText price;
    private int entry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        getSupportActionBar().hide();

        image = findViewById(R.id.imageView);
        name = findViewById(R.id.name);
        year = findViewById(R.id.year);
        power = findViewById(R.id.power);
        engine = findViewById(R.id.engine);
        price = findViewById(R.id.price);

        Bundle bundle = getIntent().getExtras();

    }
}
