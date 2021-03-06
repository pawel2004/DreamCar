package com.example.grybos.dreamcar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.grybos.dreamcar.MainActivity.dir;

public class CreateActivity extends AppCompatActivity {

    //zmienne
    private ImageView image;
    private ImageView confirm;
    private EditText name;
    private EditText year;
    private EditText power;
    private EditText engine;
    private EditText price;
    private int entry;
    private String path_string, name_string, year_string, power_string, engine_string, price_string;
    private Bitmap b;
    private boolean isAdded;
    private File img_file;
    private ArrayList<Record> list;
    private int position;
    private String path;
    private Record record;
    private boolean isChanged = false;
    private boolean isNewAdded = false;

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
        confirm = findViewById(R.id.confirm);

        Bundle bundle = getIntent().getExtras();

        entry = bundle.getInt("key");

        final DatabaseManager db = new DatabaseManager(
                CreateActivity.this,
                "Samochody.db",
                null,
                1
        );

        if (entry == 1){

            list = (ArrayList<Record>) getIntent().getExtras().getSerializable("list");
            position = bundle.getInt("position");
            path = bundle.getString("path");
            record = list.get(position);

        }

        if (entry == 0){
            isAdded = false;
            isNewAdded = false;
        }
        else {
            if (path != null)
            {
                isAdded = true;
            }
            else {
                isAdded = false;
                isNewAdded = false;

            }

            Log.d("isAdded", "isAdded" + isAdded);
        }

        if (entry == 1)
        {
            placing();
        }

        image.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 100);

                return true;
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alert = new AlertDialog.Builder(CreateActivity.this);
                alert.setTitle("Czy chcesz zapisać?");
                alert.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        name_string = name.getText().toString();
                        year_string = year.getText().toString();
                        power_string = power.getText().toString();
                        engine_string = engine.getText().toString();
                        price_string = price.getText().toString();

                        if (entry == 0){

                            if (isAdded) {

                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                b.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                                byte[] byteArray = stream.toByteArray();

                                SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
                                String d = df.format(new Date());

                                path_string = dir.getPath() + "/" + d + ".jpg";

                                FileOutputStream fs = null;

                                try {
                                    fs = new FileOutputStream(path_string);
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    fs.write(byteArray);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    fs.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }

                            db.insert(path_string, name_string, year_string, power_string, engine_string, price_string);
                            finish();

                        }
                        else {

                            if (isAdded) {

                                if (isNewAdded) {

                                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                    b.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                                    byte[] byteArray = stream.toByteArray();

                                    SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
                                    String d = df.format(new Date());

                                    path_string = dir.getPath() + "/" + d + ".jpg";

                                    FileOutputStream fs = null;

                                    try {
                                        fs = new FileOutputStream(path_string);
                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        fs.write(byteArray);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        fs.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    db.edit(record.getId(), path_string, name_string, year_string, power_string, engine_string, price_string);
                                }

                                if (isChanged) {

                                    ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
                                    b.compress(Bitmap.CompressFormat.JPEG, 100, stream1);
                                    byte[] byteArray1 = stream1.toByteArray();

                                    SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
                                    String d = df.format(new Date());

                                    path_string = dir.getPath() + "/" + d + ".jpg";

                                    FileOutputStream fs = null;

                                    try {
                                        fs = new FileOutputStream(path_string);
                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        fs.write(byteArray1);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        fs.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    db.edit(record.getId(), path_string, name_string, year_string, power_string, engine_string, price_string);
                                }

                                if (!isChanged && !isNewAdded)
                                {db.edit(record.getId(), path, name_string, year_string, power_string, engine_string, price_string);}
                                finish();

                            }

                        }

                    }
                });
                alert.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.show();

            }
        });

    }

    private void placing(){

        if (isAdded){
            img_file = new File(path);
            b = betterImageDecode(path, 1);    // funkcja betterImageDecode opisana jest poniżej
            image.setImageBitmap(b);
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

        if (record.getName().length() > 0){
            name.setText(record.getName());
        }

        if (record.getYear().length() > 0){

            year.setText(record.getYear());

        }

        if (record.getPower().length() > 0){

            power.setText(record.getPower());

        }

        if (record.getEngine().length() > 0){

            engine.setText(record.getEngine());

        }

        if (record.getPrice().length() > 0){

            price.setText(record.getPrice());

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100){

            if (resultCode == RESULT_OK){

                Uri imgData = data.getData();
                try {
                    InputStream stream = getContentResolver().openInputStream(imgData);
                    b = BitmapFactory.decodeStream(stream);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                if (entry == 1){

                    if (isAdded){

                        img_file.delete();
                        isChanged = true;

                    }

                    if (path == null){

                        isNewAdded = true;

                    }

                }

                image.setImageBitmap(b);
                image.setScaleType(ImageView.ScaleType.CENTER_CROP);

                isAdded = true;

            }

        }

    }

    private Bitmap betterImageDecode(String filePath, int size) {
        Bitmap myBitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();    //opcje przekształcania bitmapy
        options.inSampleSize = size; // zmniejszenie jakości bitmapy 4x
        options.inScaled = true;
        //
        myBitmap = BitmapFactory.decodeFile(filePath, options);
        return myBitmap;
    }
}
