package com.example.apkshare

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.pow


class AppAdapter(private val context: Context, private val apps: List<App>) :
    RecyclerView.Adapter<AppAdapter.AppViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return AppViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        val model = apps[position]
        holder.app_name.text = model.getName()
        val apkSize: Long = model.getApkSize()
        holder.apk_size.text = getHumanReadableSize(apkSize)
        holder.appIcon.setImageDrawable(model.getIcon())
    }

    private fun getHumanReadableSize(apkSize: Long): String {
        val humanReadableSize: String
        if (apkSize < 1024) {
            humanReadableSize =
                String.format(context.getString(R.string.app_size_b), apkSize.toDouble())
        } else if (apkSize < 1024.0.pow(2.0)) {
            humanReadableSize =
                String.format(context.getString(R.string.app_size_kib), (apkSize / 1024).toDouble())
        } else if (apkSize < 1024.0.pow(3.0)) {
            humanReadableSize =
                String.format(context.getString(R.string.app_size_mib), (apkSize / 1024.0.pow(2.0)))
        } else {
            humanReadableSize =
                String.format(context.getString(R.string.app_size_gib), (apkSize / 1024.0.pow(3.0)))
        }
        return humanReadableSize
    }

    override fun getItemCount(): Int {
        return apps.size
    }


    class AppViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var appIcon = itemView.findViewById<ImageView>(R.id.app_icon)
        val app_name = itemView.findViewById<TextView>(R.id.app_name)
        val apk_size = itemView.findViewById<TextView>(R.id.apk_size)
    }
}