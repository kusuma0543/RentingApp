package com.example.kusuma20.renting;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Second extends AppCompatActivity {
    ListView listviewk;
    private ProgressDialog dialog;
    String sid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbars);
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
        dialog=new ProgressDialog(this);
        dialog = new ProgressDialog(this);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Loading. Please wait...");


        listviewk=(ListView) findViewById(R.id.listviewk);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String id = intent.getStringExtra("id");
        setTitle(name);
        String URLL = "http://24x7shoppers.com/kusuma0543/rental/android_files/android_subcategories.php?cid="+ id;
        new kilomilo().execute(URLL);

    }
    public class MovieAdap extends ArrayAdapter {
        private List<Categoriestwo> movieModelList;
        private int resource;
        Context context;
        private LayoutInflater inflater;

        MovieAdap(Context context, int resource, List<Categoriestwo> objects) {
            super(context, resource, objects);
            movieModelList = objects;
            this.context = context;
            this.resource = resource;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (convertView == null) {
                convertView = inflater.inflate(resource, null);
                holder = new ViewHolder();
                holder.textone = (TextView) convertView.findViewById(R.id.texttopic);
                holder.texttwo = (TextView) convertView.findViewById(R.id.texttype);
                holder.textthree = (TextView) convertView.findViewById(R.id.textcost);
                holder.menuimage = (ImageView)convertView.findViewById(R.id.imhu);
                  convertView.setTag(holder);
            }//ino
            else {
                holder = (ViewHolder) convertView.getTag();
            }
            Categoriestwo ccitacc = movieModelList.get(position);
            holder.textone.setText(ccitacc.getSname());
            holder.texttwo.setText(ccitacc.getStype());
        holder.textthree.setText(ccitacc.getSactualcost());
            Picasso.with(context).load(ccitacc.getSimgurl()).fit().error(R.drawable.load).fit().into(holder.menuimage);

            return convertView;
        }

        class ViewHolder {
            public TextView textone, texttwo,textthree;
            private ImageView menuimage;
        }
    }

    public class kilomilo extends AsyncTask<String, String, List<Categoriestwo>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Categoriestwo> doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder buffer = new StringBuilder();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                String finalJson = buffer.toString();
                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray parentArray = parentObject.getJSONArray("result");
                List<Categoriestwo> milokilo = new ArrayList<>();
                Gson gson = new Gson();
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    Categoriestwo catego = gson.fromJson(finalObject.toString(), Categoriestwo.class);
                    milokilo.add(catego);
                }
                return milokilo;
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(final List<Categoriestwo> movieMode) {
            super.onPostExecute(movieMode);
            if (movieMode== null)
            {
                Toast.makeText(getApplicationContext(),"No Recipes available for your category",Toast.LENGTH_SHORT).show();

            }
            else
            {
                MovieAdap adapter = new MovieAdap(getApplicationContext(), R.layout.categorytwo, movieMode);
                listviewk.setAdapter(adapter);
                listviewk.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Categoriestwo item = movieMode.get(position);
                        Intent intent = new Intent(Second.this,Third.class);
                        intent.putExtra("code",item.getScode());
                        intent.putExtra("pickup",item.getSpickup());
                        intent.putExtra("return",item.getSreturn());
                        intent.putExtra("name",item.getSname());
                        intent.putExtra("type",item.getStype());
                        intent.putExtra("imgurl",item.getSimgurl());
                        intent.putExtra("cost",item.getSactualcost());
                        intent.putExtra("costdiscount",item.getSactualcost());
                        intent.putExtra("id",item.getCid());
                        intent.putExtra("sid",item.getSid());



                        startActivity(intent);
                    }
                });
                adapter.notifyDataSetChanged();
            }
        }
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
    }

}
