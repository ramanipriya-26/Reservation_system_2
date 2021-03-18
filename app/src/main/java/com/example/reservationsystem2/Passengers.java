package com.example.reservationsystem2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class Passengers extends AppCompatActivity {
    int no_of_persons;
    String[] name;
    int[] age;
    String[] gender;
    String starting;
    String destination;
    Date date_of_journey;
    String nop1;
    EditText[][] e1 = new EditText[6][3];
    EditText e2, e3, e4;
    int[] id;
    TextView nop,Name;
    private DatabaseReference mDatabase;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passengers);
        View v;
        nop=findViewById(R.id.textView31);
        SharedPreferences bb = getSharedPreferences("BOOKING", 0);
        nop1=bb.getString("no_of_persons","");
        RelativeLayout linear=findViewById(R.id.passengers1);
        RelativeLayout.LayoutParams[] rp1=new RelativeLayout.LayoutParams[Integer.parseInt(nop1)];
        for(int i1=0;i1<Integer.parseInt(nop1);i1++)
        rp1[i1]=new RelativeLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT);
        rp1[0].setMargins(10,10,0,0);
        RelativeLayout.LayoutParams[] rp2=new RelativeLayout.LayoutParams[Integer.parseInt(nop1)];
        for(int i1=0;i1<Integer.parseInt(nop1);i1++)
        rp2[i1]=new RelativeLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT);
        rp2[0].setMargins(10,100,0,0);
        RelativeLayout.LayoutParams[] rp3=new RelativeLayout.LayoutParams[Integer.parseInt(nop1)];
        for(int i1=0;i1<Integer.parseInt(nop1);i1++)
        rp3[i1]=new RelativeLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT);
        rp3[0].setMargins(10,200,0,0);
        RelativeLayout.LayoutParams[] rp11=new RelativeLayout.LayoutParams[Integer.parseInt(nop1)];
        for(int i1=0;i1<Integer.parseInt(nop1);i1++)
        rp11[i1]=new RelativeLayout.LayoutParams(300,WRAP_CONTENT);
        rp11[0].setMargins(200,-40,0,0);
        RelativeLayout.LayoutParams[] rp21=new RelativeLayout.LayoutParams[Integer.parseInt(nop1)];
        for(int i1=0;i1<Integer.parseInt(nop1);i1++)
        rp21[i1]=new RelativeLayout.LayoutParams(300,WRAP_CONTENT);
        rp21[0].setMargins(200,60,0,0);
        RelativeLayout.LayoutParams[] rp31=new RelativeLayout.LayoutParams[Integer.parseInt(nop1)];
        for(int i1=0;i1<Integer.parseInt(nop1);i1++)
        rp31[i1]=new RelativeLayout.LayoutParams(300,WRAP_CONTENT);
        rp31[0].setMargins(200,150,0,0);
        final DatabaseReference mDatabase;
        FirebaseDatabase Database = FirebaseDatabase.getInstance();
        mDatabase = Database.getReference();
        Name=findViewById(R.id.textView29);
        Name.setText(bb.getString("NAME",""));
        Button submit = (Button) findViewById(R.id.button6);
        //SharedPreferences bb = getSharedPreferences("BOOKING", 0);
        //nop1=bb.getString("no_of_persons","");
        try {
            no_of_persons = Integer.parseInt(nop1);
        }
        catch (NumberFormatException e)
        {
            System.out.println(e);
        }
        id=new int[]{1,2,3,4,5,6};
        nop.setText(nop1);
        name=new String[no_of_persons];
        age=new int[no_of_persons];
        gender=new String[no_of_persons];
        EditText ed1,ed2,ed3;
        TextView t1,t2,t3;
        List<EditText> allEds = new ArrayList<EditText>();
        List<TextView> allTvs = new ArrayList<TextView>();


        for (int i1 = 0; i1 < no_of_persons; i1++) {

            t1 = new TextView(Passengers.this);
            allTvs.add(t1);
            t1.setId(id[i1]);
            t1.setLayoutParams(new ViewGroup.LayoutParams(WRAP_CONTENT,
                    WRAP_CONTENT));
            t1.setGravity(Gravity.START);
            t1.setText("Name");
            linear.addView(t1,rp1[i1]);
            ed1 = new EditText(Passengers.this);
            allEds.add(ed1);
            ed1.setId(id[i1]);
            ed1.setLayoutParams(new ViewGroup.LayoutParams(500,
                    WRAP_CONTENT));
            ed1.setTextSize(TypedValue.COMPLEX_UNIT_SP ,14);
            ed1.setGravity((Gravity.CENTER_HORIZONTAL));
            linear.addView(ed1,rp11[i1]);
            t2 = new TextView(Passengers.this);
            allTvs.add(t2);
            t2.setId(id[i1]);
            t2.setLayoutParams(new ViewGroup.LayoutParams(WRAP_CONTENT,
                    WRAP_CONTENT));
            t2.setGravity(Gravity.START);
            t2.setText("Age");
            linear.addView(t2,rp2[i1]);
            ed2 = new EditText(Passengers.this);
            allEds.add(ed2);
            ed2.setId(id[i1]);
            ed2.setLayoutParams(new ViewGroup.LayoutParams(500,
                    WRAP_CONTENT));
            ed2.setTextSize(TypedValue.COMPLEX_UNIT_SP ,14);
            ed2.setGravity((Gravity.CENTER_HORIZONTAL));
            linear.addView(ed2,rp21[i1]);
            t3 = new TextView(Passengers.this);
            allTvs.add(t3);
            t3.setId(id[i1]);
            t3.setLayoutParams(new ViewGroup.LayoutParams(WRAP_CONTENT,
                    WRAP_CONTENT));
            t3.setGravity(Gravity.START);
            t3.setText("Gender");
            linear.addView(t3,rp3[i1]);
            ed3 = new EditText(Passengers.this);
            allEds.add(ed3);
            ed3.setId(id[i1]);
            ed3.setLayoutParams(new ViewGroup.LayoutParams(500,
                    WRAP_CONTENT));
            ed3.setTextSize(TypedValue.COMPLEX_UNIT_SP ,14);
            ed3.setGravity((Gravity.CENTER_HORIZONTAL));
            linear.addView(ed3,rp31[i1]);
            if(i1+1<no_of_persons) {
                rp1[i1 + 1].setMargins(10, i1*200+310, 0, 0);
                rp2[i1 + 1].setMargins(10, (i1 ) * 200+400, 0, 0);
                rp3[i1 + 1].setMargins(10, (i1 ) * 200+500, 0, 0);
                rp11[i1 + 1].setMargins(200, (i1) *200+ 250, 0, 0);
                rp21[i1 + 1].setMargins(200, (i1 ) *200+ 350, 0, 0);
                rp31[i1 + 1].setMargins(200, (i1 ) *200+ 450, 0, 0);
                if(i1==1){
                    rp1[i1 + 1].setMargins(10, 610, 0, 0);
                    rp2[i1 + 1].setMargins(10, 700, 0, 0);
                    rp3[i1 + 1].setMargins(10, 800, 0, 0);
                    rp11[i1 + 1].setMargins(200, 560, 0, 0);
                    rp21[i1 + 1].setMargins(200, 650, 0, 0);
                    rp31[i1 + 1].setMargins(200, 750, 0, 0);
                }
                if(i1==2){
                    rp1[i1 + 1].setMargins(10, 900, 0, 0);
                    rp2[i1 + 1].setMargins(10, 1000, 0, 0);
                    rp3[i1 + 1].setMargins(10, 1100, 0, 0);
                    rp11[i1 + 1].setMargins(200, 850, 0, 0);
                    rp21[i1 + 1].setMargins(200, 950, 0, 0);
                    rp31[i1 + 1].setMargins(200, 1050, 0, 0);
                }
                if(i1==3) {
                    rp1[i1 + 1].setMargins(10, 1190, 0, 0);
                    rp2[i1 + 1].setMargins(10, 1300, 0, 0);
                    rp3[i1 + 1].setMargins(10, 1400, 0, 0);
                    rp11[i1 + 1].setMargins(200, 1140, 0, 0);
                    rp21[i1 + 1].setMargins(200, 1250, 0, 0);
                    rp31[i1 + 1].setMargins(200, 1350, 0, 0);
                }
                if(i1==4) {
                    rp1[i1 + 1].setMargins(10, 1480, 0, 0);
                    rp2[i1 + 1].setMargins(10, 1600, 0, 0);
                    rp3[i1 + 1].setMargins(10, 1700, 0, 0);
                    rp11[i1 + 1].setMargins(200, 1430, 0, 0);
                    rp21[i1 + 1].setMargins(200, 1550, 0, 0);
                    rp31[i1 + 1].setMargins(200, 1650, 0, 0);
                }
                }
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int j=0;
               for (int i = 0; i < no_of_persons; i++) {
                   EditText ed1=allEds.get(j);
                   EditText ed2=allEds.get(j+1);
                   EditText ed3=allEds.get(j+2);
                   j=j+3;
                    name[i] = ed1.getText().toString();
                    age[i] = Integer.parseInt(ed2.getText().toString());
                    gender[i] = ed3.getText().toString();
                }
               Intent i=new Intent(Passengers.this, Transaction.class);
               i.putExtra("name",name);
               i.putExtra("age",age);
               i.putExtra("gender",gender);
                startActivity(i);
            }
        });
    }
}

