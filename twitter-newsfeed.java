package com.example.madlabproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.telephony.SmsManager;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;



public class Main6Activity extends AppCompatActivity {


    List<Status> statuses;
    ArrayList<String> stati = new ArrayList<String>();
    chetta thoo;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        if (android.os.Build.VERSION.SDK_INT > 23) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        ListView k1 = findViewById(R.id.popsicle);
        String p ="";
        final Twitter twitter = getTwitterinstance();
        try{
            statuses = twitter.getHomeTimeline();
            thoo = new chetta(this, statuses);
            k1.setAdapter(thoo);
            final SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefresh);

            //setting an setOnRefreshListener on the SwipeDownLayout
            pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                //Counting how many times user have refreshed the layout

                @Override
                public void onRefresh() {
                    try{
                        statuses = twitter.getHomeTimeline();
                        ListView k1 = findViewById(R.id.popsicle);
                        thoo = new chetta(Main6Activity.this, statuses);
                        k1.setAdapter(thoo);
                        pullToRefresh.setRefreshing(false);
                    }
                    catch(Exception e)
                    {
                        Toast.makeText(Main6Activity.this, "chee ni bathuku", Toast.LENGTH_SHORT);
                    }
                }
            });
        }
        catch(Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public static Twitter getTwitterinstance()
    {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("0evYz57zNh8SQ6nmUHGsuLXJL")
                .setOAuthConsumerSecret("yABxOGlcMtV5bc2CGdZDQQ6SXAU8cybNfTcVGEgKcklskY2SLX")
                .setOAuthAccessToken("1115272400013103105-zfM7pdyUAVRtAxyDfcH78Ci5S5Fw6Q")
                .setOAuthAccessTokenSecret("pTR6Q4ThDmu0v5Be0FOsjybrjNFZLfNWWfNjuUMP6SxHB");

        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        return twitter;
    }


    public void statustexts()
    {
        ListView k1 = findViewById(R.id.popsicle);
        Twitter twitter = getTwitterinstance();
        ArrayList<String> stati = new ArrayList<String>();
        try{
            List<Status> statuses = twitter.getHomeTimeline();
            for (Status status : statuses) {
                Date m=status.getCreatedAt();
                stati.add(status.getText());
            }
            String[] ppp = new String[stati.size()];
            for(int i =0;i<stati.size();i++)
            {
                ppp[i]=stati.get(i);
            }
        }
        catch(Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}