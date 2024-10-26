package com.example.w05_group07;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class CustomArrayAdapter extends ArrayAdapter<String> {
    Context context; Integer[] avatars; String[] ids;
    public CustomArrayAdapter (Context context, int layout, Integer[] avatars, String[] ids) {
        super(context, R.layout.list_item, ids);
        this.context = context;
        this.avatars = avatars;
        this.ids = ids;
    }
    @NonNull
    @Override
    public View getView(int position, View contextView, @NonNull ViewGroup parent) {
        Log.i("DEBUG", "Creating a listview item");
        View row = null;
        if (contextView != null){
            row = contextView;
        } else {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.list_item, null);
        }

        ImageView avatar = row.findViewById(R.id.imageItem);
        TextView id = row.findViewById(R.id.idItem);
        avatar.setImageResource(avatars[position]);
        id.setText(ids[position]);

        return row;
    }


}
