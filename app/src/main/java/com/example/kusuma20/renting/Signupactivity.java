package com.example.kusuma20.renting;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;



public class Signupactivity extends AppCompatActivity {
    EditText username,email,mobile,password;
    @InjectView(R.id.fab)
    FloatingActionButton fab;
    @InjectView(R.id.cv_add)
    CardView cvAdd;
    Button signin;
    String sname,semail,smobile,spassword;
    String emailInput,emailPattern;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupactivity);

        ButterKnife.inject(this);


        if (isConnectedToNetwork()) {
            // Toast.makeText(getApplicationContext(),"Internet Connection Established",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), " Not Internet Connection", Toast.LENGTH_SHORT).show();
        }
        username=(EditText) findViewById(R.id.et_username);

        mobile=(EditText) findViewById(R.id.et_mobile);
        password=(EditText) findViewById(R.id.et_password);
        signin=(Button) findViewById(R.id.bt_go);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mobile.length() == 10) {
                    email=(EditText) findViewById(R.id.et_email);
                    emailInput = email.getText().toString().trim();

                    emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


                    if (emailInput.matches(emailPattern)) {

                        if (isConnectedToNetwork()) {

                            sname=username.getText().toString();

                            semail = email.getText().toString();
                            smobile = mobile.getText().toString();
                            spassword=password.getText().toString();

                            insertme(sname,semail, smobile,spassword);
                            Toast.makeText(Signupactivity.this, "THANK YOU!!!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Signupactivity.this, LoginActivity.class);
                            startActivity(intent);

                        } else
                        {
                            Toast.makeText(Signupactivity.this, "Please connect to Internet", Toast.LENGTH_SHORT).show();
                            if (email.getText().toString().equals("") && mobile.getText().toString().equals("")) {
                                Toast.makeText(getApplicationContext(), "Please enter fields", Toast.LENGTH_LONG).show();
                            }


                        }


                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid email address",
                                Toast.LENGTH_SHORT).show();
                        // mail.setBackgroundResource(R.drawable.edit_red_line);
                    }


                }
                else
                {
                    Toast.makeText(getApplicationContext(), "enter Valid number",
                            Toast.LENGTH_SHORT).show();
                }
            }

        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ShowEnterAnimation();
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                animateRevealClose();
            }
        });

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void ShowEnterAnimation() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.fabtransition);
        getWindow().setSharedElementEnterTransition(transition);

        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                cvAdd.setVisibility(View.GONE);
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                animateRevealShow();
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }


        });
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void animateRevealShow() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd, cvAdd.getWidth()/2,0, fab.getWidth() / 2, cvAdd.getHeight());
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                cvAdd.setVisibility(View.VISIBLE);
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void animateRevealClose() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd,cvAdd.getWidth()/2,0, cvAdd.getHeight(), fab.getWidth() / 2);
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                cvAdd.setVisibility(View.INVISIBLE);
                super.onAnimationEnd(animation);
                fab.setImageResource(R.drawable.plus);
                Signupactivity.super.onBackPressed();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBackPressed() {
        animateRevealClose();
    }
    public void insertme(final String s1, final String s2,final String s3,final String s4) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, GlobalUrl.Url, new Response.Listener<String>() {
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
                params.put("user", s1);
                params.put("email", s2);
                params.put("mobile",s3);
                params.put("password",s4);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    private boolean isConnectedToNetwork() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
