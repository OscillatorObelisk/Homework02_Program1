package com.example.homework02_program1;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

public class ColorInfo implements Serializable
{
    //variables
    private int redNum;
    private int greenNum;
    private int blueNum;
    private String hexVal;


    public int getRedNum() {
        return redNum;
    }

    public void setRedNum(int redNum) {
        this.redNum = redNum;
    }

    public int getGreenNum() {
        return greenNum;
    }

    public void setGreenNum(int greenNum) {
        this.greenNum = greenNum;
    }

    public int getBlueNum() {
        return blueNum;
    }

    public void setBlueNum(int blueNum) {
        this.blueNum = blueNum;
    }

    public String getHexVal() {
        return hexVal;
    }

    public void setHexVal(String hexVal) {
        this.hexVal = hexVal;
    }




    static class ColorChangeBackground
    {
        //this function follows the zmoore philosophy about having to change something
        //as little as possible, so this can be called in both the ColorListAdapter and
        //MainActivity for their respective uses
        static public void changeBackground(View v, String hex)
        {
            v.setBackgroundColor(android.graphics.Color.parseColor("#" + hex));
        }

        //we do some overloading around these parts, as i have more textviews in the mainview then the color cells
        //i should have just used the recursion code i found online...
        static public void changeTextColors(int tc, TextView r, TextView g, TextView b, TextView h)
        {
            r.setTextColor(tc);
            g.setTextColor(tc);
            b.setTextColor(tc);
            h.setTextColor(tc);
        }
        static public void changeTextColors(int tc,TextView r,TextView rl,TextView g,TextView gl,TextView b,TextView bl,TextView h, TextView hl)
        {
            r.setTextColor(tc);
            rl.setTextColor(tc);
            g.setTextColor(tc);
            gl.setTextColor(tc);
            b.setTextColor(tc);
            bl.setTextColor(tc);
            h.setTextColor(tc);
            hl.setTextColor(tc);
        }
    }
}
