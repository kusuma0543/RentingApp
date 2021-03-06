package com.example.kusuma20.renting;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ncorti.slidetoact.SlideToActView;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RentalBooking extends AppCompatActivity {
EditText date_pic,time_pic,description;
    String sdate_pic,stime_pic,sdescription;
    Button bdate_pic,btime_pic;
    Button sta;
TextView idid;
    int mYear,mMonth,mDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_booking);
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
        sta = (Button) findViewById(R.id.renterbook);
        date_pic = (EditText) findViewById(R.id.datepic);
        time_pic = (EditText) findViewById(R.id.timepic);
        description=(EditText) findViewById(R.id.description);
        bdate_pic = (Button) findViewById(R.id.bdatepic);
        btime_pic=(Button) findViewById(R.id.btimepic);
        idid=(TextView) findViewById(R.id.idi);
        btime_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(RentalBooking.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time_pic.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        Intent intent = getIntent();
        final String vsid = intent.getStringExtra("sid");

        bdate_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepickerkk();
            }
        });
        sta.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {


             sdate_pic = date_pic.getText().toString();
             stime_pic = time_pic.getText().toString();
             sdescription = description.getText().toString();
             if(sdate_pic.equals("") || stime_pic.equals("") ||sdescription.equals(""))
             {
                 Toast.makeText(getApplicationContext(),"please enter all details",Toast.LENGTH_LONG).show();
             }
             else {


                 final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(RentalBooking.this);
                 SharedPreferences.Editor editor = pref.edit();
                 String unid = pref.getString("uid", "8");
                 String uname = pref.getString("uname", "8");


                 insertme(unid, vsid, uname, sdate_pic, stime_pic, sdescription);
                 Intent mainIntent = new Intent(RentalBooking.this, RentalBooking.class);

                 startActivity(mainIntent);

             }
         }
     });


    }
    public void insertme(final String s1, final String s2,final String s3,final String s4,final String s5,final String s6) {
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
                params.put("uid", s2);
                params.put("sid", s1);
                params.put("bname", s3);
                params.put("dateo", s4);
                params.put("timeo", s5);
                params.put("desco",s6);

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
    }
    public void datepickerkk() {
        final Calendar c=Calendar.getInstance();
        mYear=c.get(Calendar.YEAR);
        mMonth=c.get(Calendar.MONTH);
        mDay=c.get(Calendar.DAY_OF_MONTH);
        final DatePickerDialog datePickerDialog=new DatePickerDialog(RentalBooking.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                view.setMinDate(System.currentTimeMillis() - 1000);
                date_pic.setText(dayOfMonth+"/"+month+"/"+year);

            }
        },mDay,mMonth,mYear);
        datePickerDialog.show();
    }

}
