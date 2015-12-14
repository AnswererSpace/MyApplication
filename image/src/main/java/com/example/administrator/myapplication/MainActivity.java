package com.example.administrator.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class MainActivity extends AppCompatActivity
{
    TextView textIntro, textVer;
    Switch chk;
    RadioGroup groupVer;
    RadioButton rJelly, rKit, rLolli;
    ImageView changeImg;
    Button btnExit, btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("안드로이드 사진 보기");

        textIntro = (TextView) findViewById(R.id.intro); // 대입
        textVer = (TextView) findViewById(R.id.version);
        chk = (Switch) findViewById(R.id.start);
        groupVer = (RadioGroup) findViewById(R.id.versionGroup);
        rJelly = (RadioButton) findViewById(R.id.radioJellyBean);
        rKit = (RadioButton) findViewById(R.id.radioKitKat);
        rLolli = (RadioButton) findViewById(R.id.radioLolliPop);
        changeImg = (ImageView) findViewById(R.id.changeImage);
        btnExit = (Button) findViewById(R.id.exit);
        btnReset = (Button) findViewById(R.id.reset);

        chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton button, boolean check)
            {
                if (chk.isChecked())
                {
                    textVer.setVisibility(View.VISIBLE);
                    groupVer.setVisibility(View.VISIBLE);
                }

                else
                {
                    textVer.setVisibility(View.INVISIBLE);
                    groupVer.setVisibility(View.INVISIBLE);
                }
            }
        });

        groupVer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int check)
            {
                switch (groupVer.getCheckedRadioButtonId())
                {
                    case R.id.radioJellyBean :
                        changeImg.setImageResource(R.drawable.jellybean);
                        changeImg.setVisibility(View.VISIBLE);
                        break;

                    case R.id.radioKitKat :
                        changeImg.setImageResource(R.drawable.kitkat);
                        changeImg.setVisibility(View.VISIBLE);
                        break;

                    case R.id.radioLolliPop :
                        changeImg.setImageResource(R.drawable.lollipop);
                        changeImg.setVisibility(View.VISIBLE);
                        break;

                    default :
                        Toast.makeText(getApplicationContext(), "버전을 먼저 선택해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                chk.setChecked(false);
                changeImg.setVisibility(View.INVISIBLE);
                textVer.setVisibility(View.INVISIBLE);
                groupVer.setVisibility(View.INVISIBLE);
            }
        });
    }
}
