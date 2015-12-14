package com.example.administrator.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class MainActivity extends AppCompatActivity
{
    myDBHelper myHelper;
    EditText edtName, edtCount, edtNameResult, edtCountResult;
    Button btnReset, btnInput, btnModify, btnDelete, btnSearch;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("가수 그룹 관리 DB");

        myHelper = new myDBHelper(this);
        edtName = (EditText) findViewById(R.id.name);
        edtCount = (EditText) findViewById(R.id.count);
        edtNameResult = (EditText) findViewById(R.id.nameResult);
        edtCountResult = (EditText) findViewById(R.id.countResult);
        btnReset = (Button) findViewById(R.id.reset);
        btnInput = (Button) findViewById(R.id.input);
        btnModify = (Button) findViewById(R.id.modify);
        btnDelete = (Button) findViewById(R.id.delete);
        btnSearch = (Button) findViewById(R.id.search);

        btnReset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sqlDB = myHelper.getWritableDatabase();
                myHelper.onUpgrade(sqlDB, 1, 2);
                sqlDB.close();
            }
        });

        btnInput.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO groupTBL VALUES ('" + edtName.getText().toString() + "' , " + edtCount.getText().toString() + ");");
                btnSearch.callOnClick();
                sqlDB.close();
                Toast.makeText(getApplicationContext(), "입력됨", Toast.LENGTH_SHORT).show();
            }
        });

        btnModify.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("UPDATE groupTBL SET count = " + edtCount.getText().toString() + " WHERE name = '" + edtName.getText().toString() + "';");
                btnSearch.callOnClick();
                sqlDB.close();
                Toast.makeText(getApplicationContext(), "수정됨", Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("DELETE FROM groupTBL WHERE name = '" + edtName.getText().toString() + "';");
                btnSearch.callOnClick();
                sqlDB.close();
                Toast.makeText(getApplicationContext(), "삭제됨", Toast.LENGTH_SHORT).show();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sqlDB = myHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM groupTBL;", null);

                String strName = "그룹 이름" + "\r\n" + "━━━━" + "\r\n";
                String strCount = "인원" + "\r\n" + "━━━━" + "\r\n";

                while (cursor.moveToNext())
                {
                    strName += cursor.getString(0) + "\r\n";
                    strCount += cursor.getString(1) + "\r\n";
                }

                edtNameResult.setText(strName);
                edtCountResult.setText(strCount);

                cursor.close();
                sqlDB.close();
            }
        });
    }

    public class myDBHelper extends SQLiteOpenHelper
    {
        public myDBHelper(Context context)
        {
            super(context, "groupDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL("CREATE TABLE groupTBL(name CHAR(20) PRIMARY KEY, count INTEGER);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS groupTBL");
            onCreate(db);
        }
    }
}