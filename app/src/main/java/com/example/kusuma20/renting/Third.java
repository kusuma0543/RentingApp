package com.example.kusuma20.renting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;


public class Third extends AppCompatActivity {
TextView tcode,tname,ttype,tpickup,treturnm,tcostdiscount;
    ImageView img;
    Button rent;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
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

        tcode=(TextView) findViewById(R.id.code);
        tname=(TextView) findViewById(R.id.name);
        ttype=(TextView) findViewById(R.id.type);
        tpickup=(TextView) findViewById(R.id.pickup);
        treturnm=(TextView) findViewById(R.id.creturn);
        tcostdiscount=(TextView) findViewById(R.id.costdiscount);
        img=(ImageView) findViewById(R.id.imgvg);
        rent=(Button) findViewById(R.id.rent);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String type = intent.getStringExtra("type");
        String pickup = intent.getStringExtra("pickup");
        String treturn = intent.getStringExtra("return");
        String code = intent.getStringExtra("code");
        String costdisc=intent.getStringExtra("costdiscount");
        String imgu = intent.getStringExtra("imgurl");
         final String sidk=intent.getStringExtra("sid");

        tcode.setText(code);
        tname.setText(name);
        ttype.setText(type);
        tpickup.setText(pickup);
        treturnm.setText(treturn);
        tcostdiscount.setText(costdisc);
       // Glide.with(this).load(imgu).into(img);
        Picasso.with(context).load(imgu).fit().error(R.drawable.load).fit().into(img);

        rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Third.this,RentalBooking.class);
                intent.putExtra("sid",sidk);

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
