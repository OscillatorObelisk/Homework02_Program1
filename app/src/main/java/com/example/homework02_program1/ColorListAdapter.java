package com.example.homework02_program1;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ColorListAdapter extends BaseAdapter
{
    Context context;
    ArrayList<ColorInfo> listOfColors;
    public ColorListAdapter(Context c, ArrayList<ColorInfo> ls)
    {
        context = c;
        listOfColors = ls;
    }
    @Override
    public int getCount()
    {
        return listOfColors.size();
    }

    @Override
    public Object getItem(int position)
    {
        return listOfColors.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        if(view == null)
        {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(MainActivity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.color_cell, null);
        }

        TextView colorRed = view.findViewById(R.id.tv_v_cell_red);
        TextView colorGreen = view.findViewById(R.id.tv_v_cell_green);
        TextView colorBlue = view.findViewById(R.id.tv_v_cell_blue);
        TextView colorHex = view.findViewById(R.id.tv_v_cell_hex);

        ColorInfo color = listOfColors.get(position);

        colorRed.setText("Red: " + Integer.toString(color.getRedNum()));
        colorGreen.setText("Green: " + Integer.toString(color.getGreenNum()));
        colorBlue.setText("Blue: " + Integer.toString(color.getBlueNum()));
        colorHex.setText("Hex: " + color.getHexVal());

        //this changes text colors
        if(color.getRedNum() < 130 && color.getGreenNum() < 130 && color.getBlueNum() < 130)
        {
            ColorInfo.ColorChangeBackground.changeTextColors(Color.WHITE, colorRed, colorGreen, colorBlue, colorHex);
        }
        else
        {
            ColorInfo.ColorChangeBackground.changeTextColors(Color.BLACK, colorRed, colorGreen, colorBlue, colorHex);
        }


        //view.setBackgroundColor(android.graphics.Color.parseColor("#" + color.getHexVal()));
        ColorInfo.ColorChangeBackground.changeBackground(view, color.getHexVal());

        return view;
    }
}
