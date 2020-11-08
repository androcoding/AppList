package com.example.apkshare

import android.graphics.drawable.Drawable

class App {
    private var name: String
    private var icon: Drawable
    private var apkPath: String
    private var apkSize: Long

    constructor(name: String, icon: Drawable, apkPath: String, apkSize: Long) {
        this.name = name
        this.icon = icon
        this.apkPath = apkPath
        this.apkSize = apkSize
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getName(): String {
        return name
    }

    fun setIcon(icon: Drawable) {
        this.icon = icon
    }

    fun getIcon(): Drawable {
        return icon
    }

    fun setApkPath(apkPath: String) {
        this.apkPath = apkPath
    }

    fun getApkPath(): String {
        return apkPath
    }

    fun setApkSize(apkSize: Long) {
        this.apkSize = apkSize
    }

    fun getApkSize(): Long {
        return apkSize
    }
}