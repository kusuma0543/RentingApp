package com.example.kusuma20.renting;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ncorti.slidetoact.SlideToActView;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class RentalBooking extends AppCompatActivity {
EditText date_pic,time_pic,description;
    String sdate_pic,stime_pic,sdescription;
    Button bdate_pic,btime_pic;
    SlideToActView sta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_booking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        sta = (SlideToActView) findViewById(R.id.renterbook);
        date_pic = (EditText) findViewById(R.id.datepic);
        time_pic = (EditText) findViewById(R.id.timepic);
        description=(EditText) findViewById(R.id.description);
        bdate_pic = (Button) findViewById(R.id.bdatepic);
        btime_pic = (Button) findViewById(R.id.btimepic);
        bdate_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // datepickerkk();
            }
        });
        btime_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // timepickerkk();
            }
        });
        sta.setOnSlideCompleteListener(new SlideToActView.OnSlideCompleteListener() {
            @Override
            public void onSlideComplete(@NotNull SlideToActView view) {
                sdate_pic = date_pic.getText().toString();
                stime_pic = time_pic.getText().toString();
                sdescription = description.getText().toString();
                Intent intent = getIntent();
                final String sid = intent.getStringExtra("sid");
                final String uid = intent.getStringExtra("uid");
                insertme(uid,sid,sdate_pic, stime_pic, sdescription);
                Intent mainIntent = new Intent(RentalBooking.this, Second.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(mainIntent);
                sta.resetSlider();
            }
        });
    }
    public void insertme(final String s1, final String s2,final String s3,final String s4,final String s5) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, GlobalUrl.Urloh, new Response.Listener<String>() {
            public void onResponse(String response) {
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            { }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("uid", s1);
                params.put("sid", s2);
                params.put("dateo", s3);
                params.put("timeo", s4);
                params.put("desco",s5);

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }
}
