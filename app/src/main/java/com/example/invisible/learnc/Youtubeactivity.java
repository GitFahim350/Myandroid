package com.example.invisible.learnc;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class Youtubeactivity extends AppCompatActivity {

    RecyclerView rv;
    ArrayList<dataset> arrayList;
    database2 db2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtubeactivity);
        db2=new database2(this);
        SQLiteDatabase sqd=db2.getWritableDatabase();
        rv=findViewById(R.id.recyclerview);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
        arrayList=new ArrayList<dataset>();

        ///I Have to use the table here

        /*
        dataset dset=new dataset("https://youtu.be/RBUbja6V9Aw");
        arrayList.add(dset);
         dset=new dataset("https://youtu.be/RBUbja6V9Aw");
        arrayList.add(dset);
         dset=new dataset("https://youtu.be/RBUbja6V9Aw");
        arrayList.add(dset);
         dset=new dataset("https://youtu.be/RBUbja6V9Aw");
        arrayList.add(dset);
         dset=new dataset("https://youtu.be/RBUbja6V9Aw");
        arrayList.add(dset);

         */

        Cursor cursor=db2.readdata();
        dataset dset;
        if(cursor.getCount()>0)
        {
            StringBuffer stringBuffer=new StringBuffer();

            while(cursor.moveToNext())
            {
                dset=new dataset(cursor.getString(2));
                arrayList.add(dset);
            }
        }
        else
        {
            return;
        }
        youtubeadapter youtubeadapter=new youtubeadapter(arrayList,getApplicationContext());
        rv.setAdapter(youtubeadapter);

    }
}