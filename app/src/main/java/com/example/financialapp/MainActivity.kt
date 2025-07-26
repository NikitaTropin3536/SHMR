package com.example.financialapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import com.example.financialapp.navigation.FinancialApp
import com.example.common.core.converter.convertMillisToDate
import com.example.common.core.model.versions.AppInfo
import com.example.storage.data.sync.AppSyncStorage

/**
 * Главный Actitivy приложения
 * устанавливает навигацию и ui
 * */

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val syncer = AppSyncStorage(context = this)
        val color = syncer.getPrimaryColor()

        setAppInfo()

        setContent {
            FinancialApp(
                theme = syncer.getThemeMode(),
                primaryColor = if (color != null) Color(color) else Color(0xFF2AE881),
            )
        }
    }

    // привязываем язык, выбранный в приложении
    override fun attachBaseContext(base: Context) {
        val syncer = AppSyncStorage(context = base)
        val context = syncer.setLocale(syncer.getSavedLocale())

        super.attachBaseContext(context)
    }

    // устанавливаем версию приложения
    fun setAppInfo() {
        val syncer = AppSyncStorage(context = this)
        val packageInfo = this.packageManager.getPackageInfo(this.packageName, 0)

        syncer.saveAppInfo(
            AppInfo(
            version = packageInfo.versionName ?: "Unknown",
            lastUpdate = convertMillisToDate(packageInfo.lastUpdateTime)
        )
        )
    }
}
