package com.example.appinformation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import android.os.AsyncTask;
import android.content.Intent;

import java.util.Iterator;
import java.util.List;

import static java.lang.System.out;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getAppInfo app1;
        app1 = new getAppInfo();
        app1.doInBackground();
        app1.execute();
        out.print(app1);
    }

        public final static String GOOGLE_URL = "https://play.google.com/store/apps/details?id=";
        public static final String ERROR = "error";

        public class getAppInfo extends AsyncTask<String, String, String> {


            private final String TAG = MainActivity.getAppInfo.class.getSimpleName();
            protected PackageManager pm;
            protected String result;
            //private ActivityUtil mActivityUtil;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                System.out.println("Pre Executing program");
            }

            @Override
            protected String doInBackground(String... errors) {
                String category;
                String appName;
                String result = "";
                List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
                Iterator<ApplicationInfo> iterator = packages.iterator();
                while (iterator.hasNext()) {
                    ApplicationInfo packageInfo = iterator.next();
                    appName = (String) pm.getApplicationLabel(packageInfo);
                    String query_url = GOOGLE_URL + packageInfo.packageName;
                    category = getCategory(query_url);
                    Log.i(TAG, query_url);
                    result= "App: " + appName + "Category:" + category;
//                    for (int i = 0; i<= packages.size(); i++) {
//                        if (appName == packageInfo.packageName)
//                            result="App: " + appName + "Category:" + category;
//                    }
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result) {
                out.print(result);
            }

            protected String getCategory(String query_url) {
                try {
                    Document doc = Jsoup.connect(query_url).get();
                    Element link = doc.select("span[itemprop=genre]").first();
                    return link.text();
                } catch (Exception e) {
                    return ERROR;
                }
            }
        }
    }







