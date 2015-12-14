package com.example.administrator.myapplication;

import android.content.*;
import android.os.*;
import android.support.v7.app.*;
import android.view.View;
import android.widget.*;

public class SecondActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        setTitle("Second Activity");

        Intent inIntent = getIntent();
        String calc = inIntent.getStringExtra("calc");
        final double first = inIntent.getDoubleExtra("first", 0);
        final double second = inIntent.getDoubleExtra("second", 0);
        final double result;
        Button ret = (Button) findViewById(R.id.ret);

        if (calc.equals("plus"))
            result = Double.parseDouble(String.format("%.3f", first + second));

        else if (calc.equals("minus"))
            result = Double.parseDouble(String.format("%.3f", first - second));

        else if (calc.equals("multiply"))
            result = Double.parseDouble(String.format("%.3f", first * second));

        else if (calc.equals("divider"))
            result = Double.parseDouble(String.format("%.3f", first / second));
        else
            result = 0;

        ret.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent outIntent = new Intent(getApplicationContext(), MainActivity.class);
                outIntent.putExtra("result", result);
                setResult(RESULT_OK, outIntent);
                finish();
            }
        });
    }
}