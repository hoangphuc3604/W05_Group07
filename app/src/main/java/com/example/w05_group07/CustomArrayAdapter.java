package com.example.w05_group07;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomArrayAdapter extends ArrayAdapter<String> {
    Context context; Integer[] avatars; String[] ids;
    public CustomArrayAdapter (Context context, int layout, Integer avatars[], String ids[]) {
        super(context, R.layout.list_item, ids);
        this.context = context;
        this.avatars = avatars;
        this.ids = ids;
    }
    @Override
    public View getView(int position, View contextView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View row = inflater.inflate(R.layout.list_item, null);
        ImageView avatar = (ImageView) row.findViewById(R.id.imageItem);
        TextView id = (TextView) row.findViewById(R.id.idItem);
        avatar.setImageResource(avatars[position]);
        id.setText(ids[position]);
        return (row);
    }
}
