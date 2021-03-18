package com.example.reservationsystem2;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kd.dynamic.calendar.generator.ImageGenerator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener
{
    ArrayList<String> items=new ArrayList<>();
    SpinnerDialog spinnerDialog;
    TextView sourceBtn,DestinationBtn,txtShowdate,todayBtn,tomorrowyBtn;
    ImageView btnGetDate;
    Calendar mCalenderdate;
    Bitmap mbitmap;
    ImageGenerator mimagegenerator;
    ImageButton btnswap;
    Button btnBook;

    private FirebaseDatabase mDatabase;
    private DatabaseReference mDbRef;


    TextView Tr1Tr2;

    List<String> listData=new ArrayList<>();

    private RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;

    List<Stations> userlist=new ArrayList<>();
    public String Doj;
    TextView doj;

    TrainsRecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase=FirebaseDatabase.getInstance();
        mDbRef=mDatabase.getReference();
        initFirebase();
        //   AddData();

        sourceBtn=(TextView) findViewById(R.id.textView4);
        DestinationBtn=(TextView) findViewById(R.id.textView6);

        txtShowdate=(TextView) findViewById(R.id.TxtShowdate);
        todayBtn=(TextView) findViewById(R.id.TodayBtn);


        btnGetDate=(ImageView) findViewById(R.id.BtnGetDate);
        btnswap=(ImageButton)findViewById(R.id.BtnSwap);
        btnBook=(Button)findViewById(R.id.BtnBook);

        doj=(TextView)findViewById(R.id.TxtShowdate);

        // Intent intent = new Intent("SAVE").putExtra("DOJ",Doj).putExtra("SOURCE",sourceBtn.getText().toString()).putExtra("DEST",DestinationBtn.getText().toString());



        // Date Bitmap
        mimagegenerator=new ImageGenerator(this);
        mimagegenerator.setIconSize(50, 50);
        mimagegenerator.setDateSize(30);
        mimagegenerator.setMonthSize(10);
        mimagegenerator.setDatePosition(42);
        mimagegenerator.setMonthPosition(14);
        mimagegenerator.setDateColor(Color.parseColor("#3c6eaf"));
        mimagegenerator.setMonthColor(Color.WHITE);

        sourceBtn.setOnClickListener(this);
        DestinationBtn.setOnClickListener(this);
        btnGetDate.setOnClickListener(this);
        todayBtn.setOnClickListener(this);
        btnswap.setOnClickListener(this);
        btnBook.setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);


        spinnerDialog=new SpinnerDialog(MainActivity.this,items,"Select or Search City",R.style.DialogAnimations_SmileWindow,"Close");// With 	Animation

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Refreshing..", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    private void initFirebase()
    {
        FirebaseApp.initializeApp(this);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        mDbRef.child("Stations").addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if(userlist.size()>0)
                    userlist.clear();
                for(DataSnapshot postsnapshot:dataSnapshot.getChildren())
                {
                    Stations users=postsnapshot.getValue(Stations.class);
                    items.add(users.sloc);
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError)
            {
            }
        });
    }

    private void AddData()
    {
        final ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this, "Please wait...", "Processing...", true);
        mDbRef.child("Trains").addValueEventListener(new ValueEventListener()
        {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if(userlist.size()>0)
                    userlist.clear();
                for(DataSnapshot postsnapshot:dataSnapshot.getChildren())
                {
                    Trains to1=postsnapshot.getValue(Trains.class);
                    if(to1.source.equals(sourceBtn.getText().toString()) && to1.destination.equals(DestinationBtn.getText().toString()))
                    {

                        listData.add(to1.tname.toString());
                    }
                    progressDialog.dismiss();
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError)
            {
            }


        });
        recyclerView=(RecyclerView)findViewById(R.id.ReCyclerView);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter=new TrainsRecyclerAdapter(listData,this);
        recyclerView.setAdapter(adapter);

    }
    void rem()
    {
        listData.clear();
    }

    public void onClick(View v)
    {
        if(v.getId()==R.id.textView4)
        {
            spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                @Override
                public void onClick(String item, int position) {
                    //  Toast.makeText(MainActivity.this, item + "  " + position+"", Toast.LENGTH_SHORT).show();
                    sourceBtn.setText(item);
                }
            });
            spinnerDialog.showSpinerDialog();
        }
        if(v.getId()==R.id.textView6)
        {
            spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                @Override
                public void onClick(String item, int position) {
                    Toast.makeText(MainActivity.this, item + "  " + position+"", Toast.LENGTH_SHORT).show();
                    DestinationBtn.setText(item);
                }
            });
            spinnerDialog.showSpinerDialog();
        }
        if(v.getId()==R.id.BtnGetDate)
        {
            mCalenderdate=Calendar.getInstance();
            int year=mCalenderdate.get(Calendar.YEAR);
            int months=mCalenderdate.get(Calendar.MONTH);
            int day=mCalenderdate.get(Calendar.DATE);

            DatePickerDialog mDatePicker =new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener()
            {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2)
                {
                    mCalenderdate.set(i,i1,i2);
                    i1=i1+1;
                    txtShowdate.setText(i2+"/"+i1+"/"+i);
                    mbitmap=mimagegenerator.generateDateImage(mCalenderdate,R.drawable.empty_calendar);
                    btnGetDate.setImageBitmap(mbitmap);
                }
            }, year, months, day);
            mDatePicker.show();
        }
        if(v.getId()==R.id.TodayBtn)
        {
            mCalenderdate=Calendar.getInstance();
            int year=mCalenderdate.get(Calendar.YEAR);
            int months=mCalenderdate.get(Calendar.MONTH);
            int day=mCalenderdate.get(Calendar.DATE);
            months=months+1;
            txtShowdate.setText(day+"/"+months+"/"+year);
            mCalenderdate.set(year, months, day);
            mbitmap=mimagegenerator.generateDateImage(mCalenderdate,R.drawable.empty_calendar);
            btnGetDate.setImageBitmap(mbitmap);
        }
        if(v.getId()==R.id.BtnSwap)
        {
            String AB=DestinationBtn.getText().toString();
            String CD=sourceBtn.getText().toString();
            sourceBtn.setText(AB);
            DestinationBtn.setText(CD);
        }
        if(v.getId()==R.id.BtnBook)
        {
            AddData();
            SharedPreferences prefs = getSharedPreferences("my_prefs", MODE_PRIVATE);
            SharedPreferences.Editor edit = prefs.edit();
            edit.putString("DOJ", doj.getText().toString() );
            edit.putString("SOURCE", sourceBtn.getText().toString() );
            edit.putString("DEST", DestinationBtn.getText().toString() );
            edit.commit();
            //    startActivity(new Intent(MainActivity.this,BookingActivity.class).putExtra("SourceBtn", sourceBtn.getText().toString()).putExtra("DestinationBtn",DestinationBtn.getText().toString()));
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(MainActivity.this, "You have selected the Settings!", Toast.LENGTH_LONG).show();
            return true;
        }
        if (item.getItemId()==R.id.signout) {
            FirebaseAuth.getInstance().signOut();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

