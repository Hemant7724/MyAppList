package com.florasoft.myapplist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView app_list_recycler;
    AppAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        app_list_recycler=findViewById(R.id.app_list_recycler);
        app_list_recycler.setLayoutManager(new LinearLayoutManager(this));
        InstalledAppsManager installedAppsManager = new InstalledAppsManager();
        List<PackageInfo> installedApps = installedAppsManager.getInstalledApps(getPackageManager());

        for (PackageInfo packageInfo : installedApps) {
            String appName = packageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
            String packageName = packageInfo.packageName;
            Log.d("Installed App", "App Name: " + appName + ", Package Name: " + packageName);

            if (packageInfo.requestedPermissions != null) {
                for (String permission : packageInfo.requestedPermissions) {
                    try {
                        PermissionInfo permissionInfo = getPackageManager().getPermissionInfo(permission, 0);
                        Log.d("Permission", "Permission Name: " + permissionInfo.name);
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        adapter = new AppAdapter(this, installedApps);
        app_list_recycler.setAdapter(adapter);
    }
}