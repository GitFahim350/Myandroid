package com.example.invisible.learnc;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {

    private Button startquiz;
    private Button set,show;
    private Button tutorials;
    private Button video;
    database d;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        d=new database(this);
        startquiz=findViewById(R.id.Startquiz);
        set=    findViewById(R.id.setbutton);
        video= findViewById(R.id.video);
        show=findViewById(R.id.Show);
        tutorials=(Button) findViewById(R.id.tutorial);
        startquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(MainActivity.this,Loginactivity.class);
                startActivity(in);

            }
        });
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in1=new Intent(MainActivity.this,Admin.class);
                startActivity(in1);

            }
        });
        tutorials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in2=new Intent(MainActivity.this,Tutorialsactivity.class);
                startActivity(in2);
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cr=d.getCr();
                if(cr.getCount()==0)
                {
                    Showdata("Error","No data");
                    return;
                }
                else
                {
                    Toast.makeText(MainActivity.this,"There is data",Toast.LENGTH_SHORT).show();
                    StringBuffer Sb=new StringBuffer();
                    while(cr.moveToNext())
                    {
                        Sb.append(" Question "+cr.getString(0)+"\n");
                        Sb.append(" Option1 "+cr.getString(1)+"\n");
                        Sb.append(" Option2 "+cr.getString(2)+"\n");
                        Sb.append(" Option3 "+cr.getString(3)+"\n");
                        Sb.append(" Corans "+cr.getString(4)+"\n");
                    }
                    Showdata("Result",Sb.toString());
                }
            }
        });
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in2=new Intent(MainActivity.this,Webactivity.class);
                startActivity(in2);
            }
        });
    }

    public void Showdata(String g,String s)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(g);
        builder.setMessage(s);
        builder.setCancelable(true);
        builder.show();
    }

}
