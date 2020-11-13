package com.example.appinformation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import java.util.List;

import static java.lang.System.out;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            getAppInfo();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getAppInfo() {
        Context context= null;
        List<PackageInfo> packageList = getPackageManager().getInstalledPackages(0);
        for (int i=0; i<packageList.size(); i++){
            PackageInfo info= packageList.get(i);
            if ((info.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM)==0){
                String name = info.applicationInfo.loadLabel(getPackageManager()).toString();
                int category = getApplicationInfo().category;
                String appCategory = (String) ApplicationInfo.getCategoryTitle(context, category);
                System.out.println("Name: " + name + "Category: " + appCategory);
            }

        }


    }
}







