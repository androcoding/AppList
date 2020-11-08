package com.example.apkshare

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class MainActivity : AppCompatActivity() {
    var apps = ArrayList<App>()
    lateinit var recycler: RecyclerView
    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler = findViewById(R.id.recyclerView)

        val pm = applicationContext.packageManager
        val packages = pm.getInstalledApplications(PackageManager.GET_META_DATA)

        for (packageInfo in packages) {
            var name: String?
            if (pm.getApplicationLabel(packageInfo).toString().also { name = it }.isEmpty()) {
                name = packageInfo.packageName
            }
            val icon = pm.getApplicationIcon(packageInfo)
            val apkPath = packageInfo.sourceDir
            val apkSize = File(packageInfo.sourceDir).length()
            apps.add(App(name!!, icon, apkPath, apkSize))
        }
        apps.sortWith(Comparator { p0, p1 ->
            p0!!.getName().toLowerCase().compareTo(
                p1!!.getName().toLowerCase()
            )
        })

        val linearLayoutManager = LinearLayoutManager(this)
        recycler.layoutManager = linearLayoutManager
        recycler.setHasFixedSize(true)

        val appAdapter = AppAdapter(this, apps)
        recycler.adapter = appAdapter
    }
}