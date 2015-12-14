package com.example.administrator.myapplication;

 import android.content.Context;
 import android.support.v7.app.AppCompatActivity;
 import android.os.Bundle;
 import android.view.*;
 import android.widget.*;
 import java.io.*;
 import java.util.*;

public class MainActivity extends AppCompatActivity
{
    DatePicker dp;
    EditText edtDiary;
    Button btnWrite;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dp = (DatePicker) findViewById(R.id.datePicker1);
        edtDiary = (EditText) findViewById(R.id.edtDiary);
        btnWrite = (Button) findViewById(R.id.btnWrite);

        Calendar cal = Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);

        dp.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener()
        {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                fileName = Integer.toString(year) + "_" + Integer.toString(monthOfYear + 1) + "_" + Integer.toString(dayOfMonth) + ".txt";
                String str = readDiary(fileName);
                edtDiary.setText(str);
                btnWrite.setEnabled(true);
            }
        });

        fileName = Integer.toString(dp.getYear()) + "_" + Integer.toString(dp.getMonth() + 1) + "_" + Integer.toString(dp.getDayOfMonth()) + ".txt";
        String str = readDiary(fileName);
        /*/if(!str.equals("")) {
            edtDiary.setText(str);
        }*/

        btnWrite.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    FileOutputStream outFs = openFileOutput(fileName, Context.MODE_WORLD_WRITEABLE);
                    String str = edtDiary.getText().toString();
                    outFs.write(str.getBytes());
                    outFs.close();
                    Toast.makeText(getApplicationContext(), fileName+" 이 저장됨", Toast.LENGTH_SHORT).show();
                }

                catch(IOException ioe)
                {
                }
            }
        });
    }

    private String readDiary(String fName)
    {
        String diaryStr = null;
        FileInputStream inFs;

        try
        {
            inFs = openFileInput(fName);
            byte[] txt = new byte[500];
            inFs.read(txt);
            inFs.close();
            diaryStr = (new String(txt)).trim();
            btnWrite.setText("수정하기");
        }

        catch(IOException ioe)
        {
            edtDiary.setHint("일기 없음");
            btnWrite.setText("새로 저장");
        }

        return diaryStr;
    }
}