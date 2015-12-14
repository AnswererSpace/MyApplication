package com.example.administrator.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity
{
    Integer posterID[] = {R.drawable.image01, R.drawable.image02, R.drawable.image03, R.drawable.image04, R.drawable.image05, R.drawable.image06, R.drawable.image07, R.drawable.image08, R.drawable.image09, R.drawable.image10};
    String posterStr[] = {"감시자들", "초능력자", "열정같은소리하고있네", "검은 사제들", "신세계", "레미제라블", "은밀하게 위대하게", "늑대소년", "국제시장", "괴물의 아이"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("그리드뷰 영화 포스터");

        final GridView gridView = (GridView) findViewById(R.id.grid);
        MyGridAdapter gridAdapter = new MyGridAdapter(this);
        gridView.setAdapter(gridAdapter);
    }

    public class MyGridAdapter extends BaseAdapter
    {
        Context context;

        public MyGridAdapter(Context c)
        {
            this.context = c;
        }

        public int getCount()
        {
            return posterID.length;
        }

        public Object getItem(int arg0)
        {
            return null;
        }

        public long getItemId(int arg0)
        {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent)
        {
            ImageView imageview = new ImageView(context);
            imageview.setLayoutParams(new GridView.LayoutParams(200, 300));
            imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageview.setPadding(5, 5, 5, 5);
            imageview.setImageResource(posterID[position]);

            final int pos = position;

            imageview.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    View dialogView = (View) View.inflate(MainActivity.this, R.layout.dialog, null);
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    ImageView poster = (ImageView) dialogView.findViewById(R.id.poster);
                    poster.setImageResource(posterID[pos]);
                    dlg.setTitle(posterStr[pos]);
                    dlg.setIcon(R.drawable.slate);
                    dlg.setView(dialogView);
                    dlg.setNegativeButton("닫기", null);
                    dlg.show();
                }
            });

            return imageview;
        }
    }
}