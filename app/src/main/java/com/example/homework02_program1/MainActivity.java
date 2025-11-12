//===================================================================
//Name:Nicholas Voigt
//Date:09/30/25
//Desc:Homework02 for Mobile App Development, RGB color slider
//===================================================================

package com.example.homework02_program1;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ColorInfo> listOfColors;

    View mainView;

    //seekbars
    SeekBar sb_j_red;
    SeekBar sb_j_green;
    SeekBar sb_j_blue;

    //textviews
    TextView tv_j_rednum;
    TextView tv_j_greennum;
    TextView tv_j_bluenum;
    TextView tv_j_hex;

    //label textviews (for text color changing)
    TextView tv_j_rednumLabel;
    TextView tv_j_greennumLabel;
    TextView tv_j_bluenumLabel;
    TextView tv_j_hexLabel;

    Button btn_v_saveColor;

    ListView lv_j_listOfColors;

    ColorListAdapter clAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mainView = findViewById(R.id.main);

        sb_j_red = findViewById(R.id.sb_v_red);
        sb_j_green = findViewById(R.id.sb_v_green);
        sb_j_blue = findViewById(R.id.sb_v_blue);

        tv_j_rednum = findViewById(R.id.tv_v_rednum);
        tv_j_greennum = findViewById(R.id.tv_v_greennum);
        tv_j_bluenum = findViewById(R.id.tv_v_bluenum);
        tv_j_hex = findViewById(R.id.tv_v_hex);

        tv_j_rednumLabel = findViewById(R.id.tv_v_rednumLabel);
        tv_j_greennumLabel = findViewById(R.id.tv_v_greennumLabel);
        tv_j_bluenumLabel = findViewById(R.id.tv_v_bluenumLabel);
        tv_j_hexLabel = findViewById(R.id.tv_v_hexLabel);

        btn_v_saveColor = findViewById(R.id.btn_v_saveColor);
        lv_j_listOfColors = findViewById(R.id.lv_v_listOfColors);

        listOfColors = new ArrayList<>();

        //this will change the number associated with the seekbar
        setOnClickListenerForSeekbar(sb_j_red, tv_j_rednum);
        setOnClickListenerForSeekbar(sb_j_green, tv_j_greennum);
        setOnClickListenerForSeekbar(sb_j_blue, tv_j_bluenum);



        setOnClickListenerForButton();
        fillListView();
        setOnClickListenerForListView();

    }

    public void setOnClickListenerForSeekbar(SeekBar sb, TextView tv)
    {
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv.setText(String.valueOf(progress));
                //Log.d("Testing: ", Integer.toHexString(progress));
                setHex();
                ColorInfo.ColorChangeBackground.changeBackground(mainView, tv_j_hex.getText().toString());


                //text changin' time
                //this changes text colors (it aint gonna be pretty)
                if(sb_j_red.getProgress() < 130 && sb_j_green.getProgress() < 130 && sb_j_blue.getProgress() < 130)
                {
                    ColorInfo.ColorChangeBackground.changeTextColors(Color.WHITE,
                                                                     tv_j_rednum,tv_j_rednumLabel,
                                                                     tv_j_greennum, tv_j_greennumLabel,
                                                                     tv_j_bluenum, tv_j_bluenumLabel,
                                                                     tv_j_hex, tv_j_hexLabel             );
                }
                else
                {
                    ColorInfo.ColorChangeBackground.changeTextColors(Color.BLACK,
                                                                     tv_j_rednum,tv_j_rednumLabel,
                                                                     tv_j_greennum, tv_j_greennumLabel,
                                                                     tv_j_bluenum, tv_j_bluenumLabel,
                                                                     tv_j_hex, tv_j_hexLabel            );
                }


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    public void setHex()
    {
        int red = Integer.parseInt(tv_j_rednum.getText().toString());
        int green = Integer.parseInt(tv_j_greennum.getText().toString());
        int blue = Integer.parseInt(tv_j_bluenum.getText().toString());

        tv_j_hex.setText(String.format("%02X%02X%02X", red, green, blue));
    }

    public void setOnClickListenerForButton()
    {
        btn_v_saveColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //make a new ColorInfo object
                ColorInfo color = new ColorInfo();

                //and add it's four attributes: Numbers for Red, Green, and Blue,
                //as well as the hexadecimal value

                color.setRedNum(Integer.parseInt(tv_j_rednum.getText().toString()));
                color.setGreenNum(Integer.parseInt(tv_j_greennum.getText().toString()));
                color.setBlueNum(Integer.parseInt(tv_j_bluenum.getText().toString()));
                color.setHexVal(tv_j_hex.getText().toString());

                //add color to the array list
                listOfColors.add(color);

                clAdapter.notifyDataSetChanged();

                //this resets the color to white
                ColorInfo.ColorChangeBackground.changeBackground(mainView, "FFFFFF");
                //resets seekbar progress to 255
                sb_j_red.setProgress(255);
                sb_j_green.setProgress(255);
                sb_j_blue.setProgress(255);

            }
        });
    }

    public void fillListView()
    {
        clAdapter = new ColorListAdapter(this, listOfColors);
        lv_j_listOfColors.setAdapter(clAdapter);
    }
    public void setOnClickListenerForListView()
    {
        lv_j_listOfColors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                ColorInfo colorSelected = listOfColors.get(position);

                sb_j_red.setProgress(colorSelected.getRedNum());
                sb_j_green.setProgress(colorSelected.getGreenNum());
                sb_j_blue.setProgress(colorSelected.getBlueNum());
            }
        });
    }
}