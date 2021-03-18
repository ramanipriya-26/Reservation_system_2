package com.example.reservationsystem2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Transaction extends AppCompatActivity {
    String a,b,c,d,e,f,time;
    TextView name,source,destination,date,tname,timetext,pltfno;
    String[] name1;
    int[] age;
    String[] gender;
    TableLayout t1;
    DatabaseReference mref;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        database=FirebaseDatabase.getInstance();
        mref=database.getReference();
        source=(TextView)findViewById(R.id.textView9);
        name=(TextView)findViewById(R.id.textView16);
        destination=(TextView)findViewById(R.id.textView10);
        date=(TextView)findViewById(R.id.textView14);
        tname=(TextView)findViewById(R.id.textView11);
        timetext=findViewById(R.id.textView19);
        pltfno=findViewById(R.id.textView20);
        t1=findViewById(R.id.passengers);
        Intent i=getIntent();

        SharedPreferences bb = getSharedPreferences("BOOKING", 0);
        a = bb.getString("NAME", "");
        b = bb.getString("SOURCE", "");
        c = bb.getString("DEST", "");
        d= bb.getString("DATE", "");
        e= bb.getString("TNAME", "");
        f=bb.getString("no_of_persons","");
        name1=new String[Integer.parseInt(f)];
        age=new int[Integer.parseInt(f)];
        gender=new String[Integer.parseInt(f)];
        name1=i.getStringArrayExtra("name");
        age=i.getIntArrayExtra("age");
        gender=i.getStringArrayExtra("gender");
        TableRow tableRow1 = new TableRow(this);
        TextView name11= new TextView(this);
        name11.setText("Name");
        name11.setPadding(10,10,10,10);
        tableRow1.addView(name11);
        TextView agetv1=new TextView(this);
        agetv1.setText("Age");
        agetv1.setPadding(10,10,10,10);
        tableRow1.addView(agetv1);
        TextView gendertv1=new TextView(this);
        gendertv1.setText("Gender");
        gendertv1.setPadding(10,10,10,10);
        tableRow1.addView(gendertv1);
        t1.addView(tableRow1);
        for(int i1=0;i1<Integer.parseInt(f);i1++){
            TableRow tableRow = new TableRow(this);
            TextView name= new TextView(this);
            name.setText(name1[i1]);
            name.setPadding(10,10,10,10);
            tableRow.addView(name);
            TextView agetv=new TextView(this);
            agetv.setText(Integer.toString(age[i1]));
            agetv.setPadding(10,10,10,10);
            tableRow.addView(agetv);
            TextView gendertv=new TextView(this);
            gendertv.setText(gender[i1]);
            gendertv.setPadding(10,10,10,10);
            tableRow.addView(gendertv);
            t1.addView(tableRow);
        }
        name.setText(a);
        source.setText(b);
        destination.setText(c);
        date.setText(d);
        tname.setText(e);
        mref.child("Trains").addValueEventListener(new ValueEventListener()
        {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for(DataSnapshot postsnapshot:dataSnapshot.getChildren())
                {
                    Trains to1=postsnapshot.getValue(Trains.class);
                    assert to1 != null;
                    if(to1.tname.equals(e))
                    {
                        timetext.setText(to1.time);
                        pltfno.setText(to1.platform);
                    }
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                Toast.makeText(Transaction.this, "Failed to load post", Toast.LENGTH_SHORT).show();
            }


        });
        List<Integer> agel=new ArrayList<Integer>();
        for(int i2=0;i2<Integer.parseInt(f);i2++)
        {
            agel.add(age[i2]);
        }
        List<String> namel;
               namel= Arrays.asList(name1);

        List<String> genderl;
                genderl=Arrays.asList(gender);

       Ticket temp=new Ticket(a,Integer.parseInt(f), namel,agel,genderl,b,c,d);
       mref.child("Tickets").push().setValue(temp);
    }
}
