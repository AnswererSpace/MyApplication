package com.example.administrator.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class MainActivity extends AppCompatActivity
{
    EditText showFirst, showSecond;
    Button addBtn, subBtn, mulBtn, divBtn, modBtn;
    TextView showResult;
    String first, second, temp;
    Double result;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("초간단 계산기(수정)");

        showFirst = (EditText) findViewById(R.id.first);
        showSecond = (EditText) findViewById(R.id.second);
        addBtn = (Button) findViewById(R.id.add);
        subBtn = (Button) findViewById(R.id.sub);
        mulBtn = (Button) findViewById(R.id.mul);
        divBtn = (Button) findViewById(R.id.div);
        modBtn = (Button) findViewById(R.id.mod);
        showResult = (TextView) findViewById(R.id.result);

        addBtn.setOnClickListener(new View.OnClickListener() // 더하기
        {
            @Override
            public void onClick(View v)
            {
                first = showFirst.getText().toString();
                second = showSecond.getText().toString();

                if (checkNull(first, second))
                {
                    result = Double.parseDouble(first) + Double.parseDouble(second);
                    temp = String.format("%.3f", result);
                    showResult.setText("계산결과 : " + temp);
                }
            }
        });

        subBtn.setOnClickListener(new View.OnClickListener() // 빼기
        {
            @Override
            public void onClick(View v)
            {
                first = showFirst.getText().toString();
                second = showSecond.getText().toString();

                if (checkNull(first, second))
                {
                    result = Double.parseDouble(first) - Double.parseDouble(second);
                    temp = String.format("%.3f", result);
                    showResult.setText("계산결과 : " + temp);
                }
            }
        });

        mulBtn.setOnClickListener(new View.OnClickListener() // 곱하기
        {
            @Override
            public void onClick(View v)
            {
                first = showFirst.getText().toString();
                second = showSecond.getText().toString();

                if (checkNull(first, second))
                {
                    result = Double.parseDouble(first) * Double.parseDouble(second);
                    temp = String.format("%.3f", result);
                    showResult.setText("계산결과 : " + temp);
                }
            }
        });

        divBtn.setOnClickListener(new View.OnClickListener() // 나누기
        {
            @Override
            public void onClick(View v)
            {
                first = showFirst.getText().toString();
                second = showSecond.getText().toString();

                if (checkNull(first, second))
                {
                    if (Double.parseDouble(second) == 0)
                    {
                        Toast.makeText(getApplicationContext(), "0으로 나눌 수 없습니다.", Toast.LENGTH_SHORT).show();
                    }

                    else
                    {
                        result = Double.parseDouble(first) / Double.parseDouble(second);
                        temp = String.format("%.3f", result);
                        showResult.setText("계산결과 : " + temp);
                    }
                }
            }
        });

        modBtn.setOnClickListener(new View.OnClickListener() // 모듈러 연산
        {
            @Override
            public void onClick(View v)
            {
                first = showFirst.getText().toString();
                second = showSecond.getText().toString();

                if (checkNull(first, second))
                {
                    if (Double.parseDouble(second) == 0)
                    {
                        Toast.makeText(getApplicationContext(), "0으로 나눌 수 없습니다.", Toast.LENGTH_SHORT).show();
                    }

                    else
                    {
                        result = Double.parseDouble(first) % Double.parseDouble(second);
                        temp = String.format("%.3f", result);
                        showResult.setText("계산결과 : " + temp);
                    }
                }
            }
        });
    }

    boolean checkNull(String first, String second)
    {
        if (first.length() <= 0 || second.length() <= 0)
        {
            Toast.makeText(getApplicationContext(), "숫자를 입력해주세요.", Toast.LENGTH_SHORT).show();
            return false;
        }

        else return true;
    }
}