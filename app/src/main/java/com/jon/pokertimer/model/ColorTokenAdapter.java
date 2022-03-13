package com.jon.pokertimer.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jon.pokertimer.R;

import java.util.List;

public class ColorTokenAdapter extends BaseAdapter {

    private Context context;
    private List<ColorToken> colorList;

    public ColorTokenAdapter(Context context, List<ColorToken> colorList) {
        this.context = context;
        this.colorList = colorList;
    }

    @Override
    public int getCount() {
        if (colorList == null) {
            return 0;
        }
        return colorList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.color_token_item, parent, false);
        ColorToken colorToken = colorList.get(position);

        TextView textView = view.findViewById(R.id.textColor);
        textView.setText(colorToken.getName());

        View viewColor = view.findViewById(R.id.viewColor);
        viewColor.setBackgroundColor(colorToken.getColorValue());

        return view;
    }
}
