package com.example.administrator.myapplication;

 import android.app.Activity;
 import android.os.Bundle;
 import android.view.View;
 import android.widget.*;

public class MainActivity extends Activity
{
    int i;
    EditText edit1, edit2;
    Button button1, button2, button3, button4;
    TextView textResult;
    String num1, num2;
    Integer result;
    Button[] numButtons=new Button[10];
    Integer[] numBtnIDs={R.id.BtnNum0, R.id.BtnNum1, R.id.BtnNum2, R.id.BtnNum3, R.id.BtnNum4, R.id.BtnNum5, R.id.BtnNum6, R.id.BtnNum7, R.id.BtnNum8, R.id.BtnNum9};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("테이블레이아웃 계산기");

        edit1=(EditText) findViewById(R.id.Edit1);
        edit2=(EditText) findViewById(R.id.Edit2);
        button1=(Button) findViewById(R.id.Button1);
        button2=(Button) findViewById(R.id.Button2);
        button3=(Button) findViewById(R.id.Button3);
        button4=(Button) findViewById(R.id.Button4);

        textResult=(TextView) findViewById(R.id.TextResult);

        for (i=0; i<numBtnIDs.length; i++) {
            numButtons[i]=(Button) findViewById(numBtnIDs[i]);
        }
        for (i=0; i<numBtnIDs.length; i++) {
            final int index;
            index = i;
            numButtons[index].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (edit1.isFocused() == true) {
                        num1 = edit1.getText().toString() + numButtons[index].getText().toString();
                        edit1.setText(num1);
                    } else if (edit2.isFocused() == true) {
                        num2 = edit2.getText().toString() + numButtons[index].getText().toString();
                        edit2.setText(num2);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"먼저 텍스트를 선택하세요",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        button1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                num1=edit1.getText().toString();
                num2=edit2.getText().toString();
                if (num1.length()<=0 || num2.length()<=0) {
                    Toast.makeText(getApplicationContext(), "값이 입력되지 않았습니다.",Toast.LENGTH_SHORT).show();
                }
                else {
                    result = Integer.parseInt(num1) + Integer.parseInt(num2);
                    textResult.setText("계산결과 : " + result.toString());
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                num1=edit1.getText().toString();
                num2=edit2.getText().toString();
                if (num1.length()<=0 || num2.length()<=0) {
                    Toast.makeText(getApplicationContext(), "값이 입력되지 않았습니다.",Toast.LENGTH_SHORT).show();
                }
                else {
                    result = Integer.parseInt(num1) - Integer.parseInt(num2);
                    textResult.setText("계산결과 : " + result.toString());
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                num1=edit1.getText().toString();
                num2=edit2.getText().toString();
                if (num1.length()<=0 || num2.length()<=0) {
                    Toast.makeText(getApplicationContext(), "값이 입력되지 않았습니다.",Toast.LENGTH_SHORT).show();
                }
                else {
                    result = Integer.parseInt(num1) * Integer.parseInt(num2);
                    textResult.setText("계산결과 : " + result.toString());
                }
            }
        });
        button4.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                num1=edit1.getText().toString();
                num2=edit2.getText().toString();
                if (num1.length()<=0 || num2.length()<=0) {
                    Toast.makeText(getApplicationContext(), "값이 입력되지 않았습니다.",Toast.LENGTH_SHORT).show();
                }
                else if (Double.parseDouble(num2)==0) {
                    Toast.makeText(getApplicationContext(), "0으로 나눌 수 없습니다.",Toast.LENGTH_SHORT).show();
                }
                else {
                    result = Integer.parseInt(num1) / Integer.parseInt(num2);
                    textResult.setText("계산결과 : " + result.toString());
                }
            }
        });
    }

}
