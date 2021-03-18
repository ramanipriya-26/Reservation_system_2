package com.example.reservationsystem2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FormTicket extends AppCompatActivity {
    TextView bform,dojsd,name,email,phone,dojF;
    TextView nop;
    String cls,tot,DT,tname,source,destination,DOJ;
    Button bookticket,submit;
    String nop1;
    String m,n,p;
    private RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_ticket);
        bform=(TextView)findViewById(R.id.BookingFor);
        dojsd=(TextView)findViewById(R.id.journey);
        name=(TextView)findViewById(R.id.input_nameF);
        email=(TextView)findViewById(R.id.input_email);
        phone=(TextView)findViewById(R.id.input_contactno);
        nop=(TextView)findViewById(R.id.input_not);

        System.out.println(nop1);
        dojF=(TextView)findViewById(R.id.DoJ);
        bookticket=(Button)findViewById(R.id.BtnGoTicket);

        cls=getIntent().getStringExtra("Class");
        tot=getIntent().getStringExtra("TotSeats");
        tname=getIntent().getStringExtra("TNAME");

        SharedPreferences bb = getSharedPreferences("my_prefs", 0);
        m = bb.getString("SOURCE", "");
        n = bb.getString("DEST", "");
        p = bb.getString("DOJ", "");

        bform.setText("Booking Form For "+tname);
        dojsd.setText("Reservation For "+cls+" Berth From "+m+" To "+n);
        dojF.setText(p);
        bookticket.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i= new Intent(FormTicket.this,Passengers.class);
                //i.putExtra("no_of_persons",nop1);
                startActivity(i);
                SharedPreferences prefs = getSharedPreferences("BOOKING", MODE_PRIVATE);
                SharedPreferences.Editor edit = prefs.edit();
                edit.putString("NAME", name.getText().toString() );
                edit.putString("SOURCE", m );
                edit.putString("DEST", n );
                edit.putString("DATE", p );
                edit.putString("TNAME", tname );
                edit.putString("no_of_persons",nop.getText().toString());
                edit.commit();
            }
        });

    }
}