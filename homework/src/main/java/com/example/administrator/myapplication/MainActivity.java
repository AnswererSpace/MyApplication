package com.example.administrator.myapplication;

import android.app.*;
import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.*;
import android.os.*;
import android.view.View;
import android.widget.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class MainActivity extends AppCompatActivity
{
    TextView selectDate; // 날짜선택
    Button btnSave; // 저장버튼
    EditText edtContext; // 일기내용
    DatePicker datePicker;
    DatePickerDialog datePickerDialog; // 다이얼로그
    Calendar calendar;
    String fileName, sdPath, sdState;
    int cYear, cMonth, cDay;
    File myDir;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("조금 복잡한 일기장");

        selectDate = (TextView) findViewById(R.id.date);
        btnSave = (Button) findViewById(R.id.save);
        edtContext = (EditText) findViewById(R.id.context);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        calendar = Calendar.getInstance();
        cYear = calendar.get(Calendar.YEAR);
        cMonth = calendar.get(Calendar.MONTH);
        cDay = calendar.get(Calendar.DAY_OF_MONTH);
        sdState = Environment.getExternalStorageState();
        sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        myDir = new File(sdPath + "/mydiary");
        myDir.mkdir();
        selectDate.setText(cYear + "년 " + (cMonth + 1) + "월 " + cDay + "일");


        datePicker.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() // 초기날짜에(오늘) 저장된 파일 있으면 내용 불러오기
        {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                fileName = Integer.toString(year) + "_" + Integer.toString(monthOfYear + 1) + "_" + Integer.toString(dayOfMonth) + ".txt"; // 년_월_일.txt
                Toast.makeText(getApplicationContext(), fileName, Toast.LENGTH_SHORT).show();
                String str = readDiary(fileName);
                edtContext.setText(str);
            }
        });

        selectDate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                datePickerDialog = new DatePickerDialog(v.getContext(), myDateSetListener, cYear, cMonth, cDay);
                datePickerDialog.show();
            }

            DatePickerDialog.OnDateSetListener myDateSetListener = new DatePickerDialog.OnDateSetListener()
            {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                {
                    selectDate.setText(year + "년 " + (monthOfYear + 1) + "월 " + dayOfMonth + "일");
                    fileName = Integer.toString(year) + "_" + Integer.toString(monthOfYear) + "_" + Integer.toString(dayOfMonth) + ".txt"; // 년_월_일.txt
                    String str = readDiary(fileName);
                    edtContext.setText(str);
                }
            };
        });

        btnSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FileOutputStream outputS;
                try
                {
                    File file = new File(myDir + fileName);
                    outputS = new FileOutputStream(file);
                    String str = edtContext.getText().toString();
                    outputS.write(str.getBytes());
                    outputS.close();
                    Toast.makeText(getApplicationContext(), fileName+"이 저장됨", Toast.LENGTH_SHORT).show();
                }

                catch (FileNotFoundException fe)
                {
                    Toast.makeText(getApplicationContext(), "저장을 할 수 없습니다.", Toast.LENGTH_SHORT).show();
                }

                catch (IOException ioe)
                {
                    Toast.makeText(getApplicationContext(), "저장을 할 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    String readDiary(String fileName)
    {
        String diaryStr = null;
        FileInputStream inputS;

        try
        {
            inputS = new FileInputStream(myDir + fileName);
            byte txt[] = new byte[512];
            inputS.read(txt);
            inputS.close();
            diaryStr = (new String(txt)).trim();
        }

        catch (IOException ioe)
        {
            Toast.makeText(getApplicationContext(), "파일을 읽을 수 없습니다.", Toast.LENGTH_SHORT).show();
        }

        return diaryStr;
    }
}