package com.example.grybos.dreamcar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class PreviewActivity extends AppCompatActivity {

    //zmienne
    private ImageView image;
    private ImageView back;
    private TextView name;
    private TextView year;
    private TextView power;
    private TextView engine;
    private TextView price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        getSupportActionBar().hide();

        image = findViewById(R.id.imageView);
        name = findViewById(R.id.name);
        year = findViewById(R.id.year);
        power = findViewById(R.id.power);
        engine = findViewById(R.id.engine);
        price = findViewById(R.id.price);
        back = findViewById(R.id.back);

        Bundle bundle = getIntent().getExtras();

        ArrayList<Record> list = (ArrayList<Record>) getIntent().getExtras().getSerializable("list");
        int position = bundle.getInt("position");
        String path = bundle.getString("path");
        Record record = list.get(position);

        if (path != null){

            Uri uri = Uri.fromFile(new File(path));
            image.setImageURI(uri);
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);

        }

        if (record.getName().length() > 0){

            name.setText("Nazwa: " + record.getName());

        }

        else {

            name.setText("Nazwa: nieznana");

        }

        if (record.getYear().length() > 0){

            year.setText("Rok produkcji: " + record.getYear());

        }

        else {

            year.setText("Rok produkcji: nieznany");

        }

        if (record.getPower().length() > 0){

            power.setText("Moc: " + record.getPower());

        }

        else {

            power.setText("Moc: nieznana");

        }

        if (record.getEngine().length() > 0){

            engine.setText("Silnik: " + record.getEngine());

        }

        else {

            engine.setText("Silnik: nieznany");

        }

        if (record.getPrice().length() > 0){

            price.setText("Cena: " + record.getPrice());

        }

        else {

            price.setText("Cena: nieznana");

        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

    }
}
