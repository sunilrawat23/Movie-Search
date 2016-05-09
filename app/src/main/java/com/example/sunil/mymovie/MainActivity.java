package com.example.sunil.mymovie;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private SignUpAsync mSignUpAsync;
    private ListView list = null;
    Button btn;
    EditText ed1,ed2;
    TextView txt_title,txt_year,txt_released,txt_genre,txt_director,txt_actors,txt_plot,txt_country,txt_award;
    LinearLayout linearLayout;
    ImageView img_poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn=(Button)findViewById(R.id.show);
        ed1=(EditText)findViewById(R.id.moviename);
        ed2=(EditText)findViewById(R.id.year);

        btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String name=ed1.getText().toString();
        String year=ed2.getText().toString();
        img_poster=(ImageView)findViewById(R.id.img_poster);
        txt_title=(TextView) findViewById(R.id.txt_title);
        txt_year=(TextView) findViewById(R.id.txt_year);
        txt_released=(TextView) findViewById(R.id.txt_released);
        txt_genre=(TextView) findViewById(R.id.txt_genre);
        txt_director=(TextView) findViewById(R.id.txt_director);
        txt_actors=(TextView) findViewById(R.id.txt_actors);
        txt_plot=(TextView) findViewById(R.id.txt_plot);
        txt_country=(TextView) findViewById(R.id.txt_country);
        txt_award=(TextView) findViewById(R.id.txt_award);

        linearLayout=(LinearLayout)findViewById(R.id.layout_result);

        PrefUtils.saveToPrefs(
                        getApplicationContext(),
                        PrefUtils.PREFS_LOGIN_PRIVACY_KEY, name);
        PrefUtils.saveToPrefs(
                        getApplicationContext(),
                        PrefUtils.PREFS_COMPETITION_KEY, year);

        mSignUpAsync = new SignUpAsync();
        mSignUpAsync.execute(Constant.TXT_BLANK);
        ed1.setText("");
        ed2.setText("");

    }
});
    }
    public class SignUpAsync extends AsyncTask<String, Void, String> {
        // private Dialog mDialog;
        ProgressDialog dialog;
        private String response = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try {
                dialog = new ProgressDialog(MainActivity.this);
                dialog.setTitle("Please Wait..");
                dialog.setMessage("Loading Data...");
                dialog.setCancelable(false);
                dialog.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(String... params) {

            response = CallSignUpService();
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (dialog != null) {
                dialog.dismiss();

            }
            {
                JSONObject movieObject = null;

                try {
                    movieObject = new JSONObject(result);

                    if(movieObject!=null)



                    {

                        try {
                            String title = movieObject.getString("Title");
                            txt_title.setText(title);
                            String year = movieObject.getString("Year");
                            txt_year.setText(year);
                            String released = movieObject.getString("Released");
                            txt_released.setText(released);
                            String genre = movieObject.getString("Genre");
                            txt_genre.setText(genre);
                            String director = movieObject.getString("Director");
                            txt_director.setText(director);
                            String Actors = movieObject.getString("Actors");
                            txt_actors.setText(Actors);
                            String Plot = movieObject.getString("Plot");
                            txt_plot.setText(Plot);
                            String Country = movieObject.getString("Country");
                            txt_country.setText(Country);
                            String award = movieObject.getString("Awards");
                            txt_award.setText(award);

                            String poster=movieObject.getString("Poster");

                            Picasso.with(getBaseContext()).load(poster).into(img_poster);

                            System.out.println("Title "+title+" Poster"+poster);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }


                  else
                    {
                        Toast.makeText(MainActivity.this,"Please Enter The correct Movie Name",Toast.LENGTH_LONG).show();


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                // finish();

            }
        }

        private String CallSignUpService() {

            String name=PrefUtils
                    .getFromPrefs(MainActivity.this,
                            PrefUtils.PREFS_LOGIN_PRIVACY_KEY, null)
                    .toString().trim();
            String year=PrefUtils
                    .getFromPrefs(MainActivity.this,
                            PrefUtils.PREFS_COMPETITION_KEY, null)
                    .toString().trim();
            String plot="short";
            String k="json";
            String ss="http://www.omdbapi.com/?t=";
            String url=ss+name;
            String p=url+"&y="+year;
            String m=p+"&plot="+plot;
            String finalurl=m+"&r="+k;
            try {

                HttpClientWritten client = new HttpClientWritten(finalurl,
                        MainActivity.this);

                client.connectForMultipart();

                client.finishMultipart();
                response = client.getResponse();

                System.out
                        .println("################################################################################ Response:"
                                + response);

            } catch (Throwable t) {
                t.printStackTrace();
            }

            return response;
        }

    }
}

