package com.florasoft.myapplist;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

public class InstalledAppsManager {
   public List<PackageInfo> getInstalledApps(PackageManager packageManager) {
       return packageManager.getInstalledPackages(PackageManager.GET_PERMISSIONS);
   }
}
