package com.example.grybos.dreamcar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomAdapter extends ArrayAdapter {

    private ArrayList<Record> _list;
    private Context _context;
    private int _resource;
    private DatabaseManager db;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList objects, DatabaseManager db) {
        super(context, resource, objects);

        this._list = objects;
        this._context = context;
        this._resource = resource;
        this.db = db;

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

            Uri uri = Uri.fromFile(new File(_list.get(position).getPath()));
            image.setImageURI(uri);

        }

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ImageView delete = (ImageView) convertView.findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alert = new AlertDialog.Builder(_context);
                alert.setTitle("Czy chcesz to usunąć?");
                alert.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        db.delete(_list.get(position).getId());
                        _list.remove(position);
                        notifyDataSetChanged();

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

        ImageView edit = (ImageView) convertView.findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;

    }

}
