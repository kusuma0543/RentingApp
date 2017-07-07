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

import java.util.HashMap;
import java.util.Map;

public class MyAccountEdit extends AppCompatActivity {
    EditText mm,nn;
    TextView lll;
    Button bupdate;
    String sname,semail,smobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
setTitle("Edit Details");
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
        mm=(EditText) findViewById(R.id.namekkk);
        nn=(EditText) findViewById(R.id.emailkkk);
        lll=(TextView) findViewById(R.id.mobilekkk);

        bupdate=(Button) findViewById(R.id.bupdatee);

        bupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sname=mm.getText().toString();
                semail=nn.getText().toString();
                smobile=lll.getText().toString();
                updateme(sname,semail,smobile);
                Intent intent=new Intent(MyAccountEdit.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        Intent nnn=getIntent();
        String a=nnn.getStringExtra("names");
        String b=nnn.getStringExtra("emails");
        String k=nnn.getStringExtra("mobiles");
        mm.setText(b);
        nn.setText(a);
        lll.setText(k);


    }
    public void updateme(final String s1, final String s2,final String s3) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, GlobalUrl.Urlupdate, new Response.Listener<String>() {
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
                params.put("uname", s1);
                params.put("uemail", s2);
                params.put("umobilenumber",s3);

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

}
