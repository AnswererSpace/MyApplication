package com.example.administrator.myapplication;

import android.content.*;
import android.support.v7.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends AppCompatActivity
{
    EditText inputFirst, inputSecond;
    RadioGroup gCalc;
    RadioButton rPlus, rMinus, rMul, rDiv;
    Button calc;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Main Activity");

        inputFirst = (EditText) findViewById(R.id.first);
        inputSecond = (EditText) findViewById(R.id.second);
        gCalc = (RadioGroup) findViewById(R.id.calcGroup);
        rPlus = (RadioButton) findViewById(R.id.plus);
        rMinus = (RadioButton) findViewById(R.id.minus);
        rMul = (RadioButton) findViewById(R.id.multiply);
        rDiv = (RadioButton) findViewById(R.id.divider);
        calc = (Button) findViewById(R.id.calc);

        calc.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("first", Double.parseDouble(inputFirst.getText().toString()));
                intent.putExtra("second", Double.parseDouble(inputSecond.getText().toString()));

                switch (gCalc.getCheckedRadioButtonId())
                {
                    case R.id.plus:
                        intent.putExtra("calc", "plus");
                        break;

                    case R.id.minus:
                        intent.putExtra("calc", "minus");
                        break;

                    case R.id.multiply:
                        intent.putExtra("calc", "multiply");
                        break;

                    case R.id.divider:
                        if (Integer.parseInt(inputFirst.getText().toString()) == 0 || Integer.parseInt(inputSecond.getText().toString()) == 0)
                            Toast.makeText(getApplicationContext(), "0으로 나눌 수 없습니다.", Toast.LENGTH_SHORT).show();

                        else
                            intent.putExtra("calc", "divider");

                        break;

                    default:
                        Toast.makeText(getApplicationContext(), "연산을 선택해주세요.", Toast.LENGTH_SHORT).show();
                }

                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == RESULT_OK)
        {
            double result = data.getDoubleExtra("result", 0);

            if (result == 0)
                Toast.makeText(getApplicationContext(), "계산과정 오류", Toast.LENGTH_SHORT).show();

            else
                Toast.makeText(getApplicationContext(), "결과 : " + result, Toast.LENGTH_SHORT).show();
        }
    }
}