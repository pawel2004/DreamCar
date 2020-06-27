package com.example.grybos.dreamcar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomAdapter extends ArrayAdapter {

    private ArrayList<Record> _list;
    private Context _context;
    private int _resource;
    private Bitmap bmp;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList objects, DatabaseManager db) {
        super(context, resource, objects);

        this._list = objects;
        this._context = context;
        this._resource = resource;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(_resource, null);

        final ImageView image = (ImageView) convertView.findViewById(R.id.image);

        if (_list.get(position).getPath() == null){

            image.setImageResource(R.drawable.baseline_clear_black_36dp);

        }else {

            bmp = betterImageDecode(_list.get(position).getPath(), 1);

            image.setImageBitmap(bmp);
        }

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ImageView delete = (ImageView) convertView.findViewById(R.id.delete);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ImageView edit = (ImageView) convertView.findViewById(R.id.edit);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;

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
