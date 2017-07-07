package com.example.kusuma20.renting;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.example.kusuma20.renting.R.id.fab;
import static com.example.kusuma20.renting.R.id.start;

public class MyAccount extends AppCompatActivity {
TextView mm,nn,ll;
    Button bedit,bnet;
    String sname,semail,smobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mm=(TextView) findViewById(R.id.namekk);
        nn=(TextView) findViewById(R.id.emailkk);
        ll=(TextView) findViewById(R.id.mobilekk);
        bedit=(Button) findViewById(R.id.bedit);

        Intent nnn=getIntent();
        sname= nnn.getStringExtra("name");
        semail=nnn.getStringExtra("email");
        smobile=nnn.getStringExtra("mobile");
        mm.setText(sname);
        nn.setText(semail);
        ll.setText(smobile);

        bedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent intent=new Intent(MyAccount.this,MyAccountEdit.class);
                intent.putExtra("names",sname);
                intent.putExtra("emails",semail);
                intent.putExtra("mobiles",smobile);
                startActivity(intent);
            }
        });
        bnet=(Button) findViewById(R.id.bnet);
        bnet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MyAccount.this,Nothing.class);
                startActivity(intent);
            }
        });




    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
    }


}
