package com.florasoft.myapplist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.ViewHolder> {

    List<PackageInfo> apps;
    PackageManager packageManager;
    LayoutInflater inflater;

    public AppAdapter(Context context, List<PackageInfo> apps) {
        this.apps = apps;
        this.packageManager = context.getPackageManager();
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_app, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PackageInfo packageInfo = apps.get(position);
        holder.textAppName.setText("App Name :- "+packageInfo.applicationInfo.loadLabel(packageManager));
        holder.textPackageName.setText("Package Name :- "+packageInfo.packageName);

        StringBuilder permissionsText = new StringBuilder();
        if (packageInfo.requestedPermissions != null) {
            for (String permission : packageInfo.requestedPermissions) {
                try {
                    PermissionInfo permissionInfo = packageManager.getPermissionInfo(permission, 0);
                    permissionsText.append(permissionInfo.name).append("\n");
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        holder.textPermissions.setText(permissionsText.toString().trim());
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textAppName;
        TextView textPackageName;
        TextView textPermissions;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textAppName = itemView.findViewById(R.id.text_app_name);
            textPackageName = itemView.findViewById(R.id.text_package_name);
            textPermissions = itemView.findViewById(R.id.text_permissions);
        }
    }
}