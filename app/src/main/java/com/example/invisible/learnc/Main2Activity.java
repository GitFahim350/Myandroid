package com.example.invisible.learnc;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv,sc,ques;
    private Button confirmbutton;
    private Button STARTBUTTON;
    private Button NextButton;
    Question[] q=new Question[100];
    private RadioGroup grp;
    private RadioButton RB;
    private RadioButton Rb1;
    private RadioButton Rb2;
    private RadioButton Rb3;
    Studentdetails std;
    Random rand;
    Cursor cr;
    int y=0,z=0,score=0,a=1,number=1,count=0,stop=0;
    int check[]=new int[100];
    ///check[0]=0;

    database d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        d=new database(this);
        SQLiteDatabase sqlite =d.getWritableDatabase();
        rand=new Random();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        confirmbutton=findViewById(R.id.ConfirmButton);
        STARTBUTTON=findViewById(R.id.start);
        confirmbutton=findViewById(R.id.ConfirmButton);
        ques=findViewById(R.id.textView3);
        NextButton=findViewById(R.id.Nextid);
        STARTBUTTON.setOnClickListener(this);
        confirmbutton.setOnClickListener(this);
        NextButton.setOnClickListener(this);
        sc=findViewById(R.id.SCORE);
        Rb1=findViewById(R.id.Option_1);
        Rb2=findViewById(R.id.Option_2);
        Rb3=findViewById(R.id.Option_3);
        tv=findViewById(R.id.textView4);
        grp=findViewById(R.id.group);
        //Cursor sr= (Cursor) getIntent().getSerializableExtra("Cursor");
    }
    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.start)
        {
            cr=d.getCr();
            if(cr.getCount()==0)
            {
                Showdata("Error","No data");
                return;
            }
            else
            {
                StringBuffer Sb=new StringBuffer();
                while(cr.moveToNext())
                {
                    q[y]=new Question();
                    Sb.append(" Question "+cr.getString(0)+"\n");
                    q[y].question=cr.getString(0);
                    Sb.append(" Option1 "+cr.getString(1)+"\n");
                    q[y].Option1=cr.getString(1);
                    Sb.append(" Option2 "+cr.getString(2)+"\n");
                    q[y].Option2=cr.getString(2);
                    Sb.append(" Option3 "+cr.getString(3)+"\n");
                    q[y].Option3=cr.getString(3);
                    Sb.append(" Corans "+cr.getString(4)+"\n");
                    q[y].corans= Integer.parseInt(cr.getString(4));
                    y++;
                }
                ques.setText("Question:1/"+y);
                tv.setText(q[z].question);
                Rb1.setText(q[z].Option1);
                Rb2.setText(q[z].Option2);
                Rb3.setText(q[z].Option3);
                STARTBUTTON.setVisibility(View.INVISIBLE);

                check[0]=100;
                for(int i=1;i<y;i++)
                {
                    check[i]=i;
                }
            }
            Toast.makeText(Main2Activity.this,"Total  number of Questions is"+y,Toast.LENGTH_SHORT).show();
        }
        if(v.getId()==R.id.ConfirmButton)
        {
            int selcted=grp.getCheckedRadioButtonId();
            RB=findViewById(selcted);
            confirmbutton.setVisibility(View.INVISIBLE);
            if(q[z].corans==1)
            {
                tv.setText("The Correct answer is"+q[z].Option1);
            }
            else if(q[z].corans==2)
            {
                tv.setText("The Correct answer is"+q[z].Option2);
            }
            else if(q[z].corans==3)
            {
                tv.setText("The Correct answer is"+q[z].Option3);
            }

            if(q[z].corans==1)
            {
                Rb1.setTextColor(Color.GREEN);
                Rb2.setTextColor(Color.RED);
                Rb3.setTextColor(Color.RED);
            }
            else if(q[z].corans==2)
            {
                Rb1.setTextColor(Color.RED);
                Rb2.setTextColor(Color.GREEN);
                Rb3.setTextColor(Color.RED);
            }
            else if(q[z].corans==3)
            {
                Rb1.setTextColor(Color.RED);
                Rb2.setTextColor(Color.RED);
                Rb3.setTextColor(Color.GREEN);
            }
            if(q[z].corans==1&&RB==Rb1)
            {
                score++;
                sc.setText("Score: "+score);
            }
            else if(q[z].corans==2&&RB==Rb2)
            {
                score++;
                sc.setText("Score: "+score);
            }
            else if(q[z].corans==3&&RB==Rb3)
            {
                score++;
                sc.setText("Score: "+score);
            }

            if(z==y-1)
            {
                Toast.makeText(this,"Last score is "+score,Toast.LENGTH_SHORT).show();
            }

        }
        if(v.getId()==R.id.Nextid)
        {
            number++;
            z=rand.nextInt(y);

            while(check[z]!=z)
            {
                z=rand.nextInt(y);

            }



                count++;
                ques.setText("Question:"+number+"/"+y);
                check[z]=100;
                grp.clearCheck();
                if(count==y-1)
                {
                    tv.setText(q[z].question);
                    Rb1.setText(q[z].Option1);
                    Rb2.setText(q[z].Option2);
                    Rb3.setText(q[z].Option3);
                    Rb1.setTextColor(Color.BLACK);
                    Rb2.setTextColor(Color.BLACK);
                    Rb3.setTextColor(Color.BLACK);
                    confirmbutton.setVisibility(View.VISIBLE);
                    //Toast.makeText(Main2Activity.this," Value of z is "+z,Toast.LENGTH_SHORT).show();
                    NextButton.setVisibility(View.INVISIBLE);
                }


                else if(z<y)
                {
                    confirmbutton.setVisibility(View.VISIBLE);
                    tv.setText(q[z].question);
                    Rb1.setText(q[z].Option1);
                    Rb2.setText(q[z].Option2);
                    Rb3.setText(q[z].Option3);
                    Rb1.setTextColor(Color.BLACK);
                    Rb2.setTextColor(Color.BLACK);
                    Rb3.setTextColor(Color.BLACK);
                    // Toast.makeText(Main2Activity.this," Value of z is "+z,Toast.LENGTH_SHORT).show();
                }


        }
    }
    public String getinfo(String s)
    {
        String inf="";

        return inf;
    }
    public void Showdata(String g,String s)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(g);
        builder.setMessage(s);
        builder.setCancelable(true);
        builder.show();
    }
    public void ShowToast()
    {
        Toast.makeText(Main2Activity.this,q[y-1].question+" "+q[y-1].Option1+" "+q[y-1].Option2+" "+q[y-1].Option3+" "+q[y-1].corans,Toast.LENGTH_SHORT).show();
    }
    public void Alert(String alt,String msg)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(alt);
        builder.setMessage(msg);
        builder.setCancelable(true);
        builder.show();
    }
}
