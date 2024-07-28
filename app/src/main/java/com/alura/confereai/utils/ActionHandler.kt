package com.alura.confereai.utils

import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import android.provider.Settings
import com.alura.confereai.data.CalendarEventBarcode
import com.alura.confereai.data.Emblem
import com.alura.confereai.data.GeoBarcode
import com.alura.confereai.data.UrlBarcode
import com.alura.confereai.data.WifiBarcode

class ActionHandler(private val context: Context) {
    fun handleBarcodeAction(emblem: Emblem) {
        when (emblem) {
            is WifiBarcode -> {
                openWifiSettings(context)
            }

            is UrlBarcode -> {
                openUrl(context, emblem.url)
            }

            is GeoBarcode -> {
                openMap(context, emblem.lat, emblem.lng)
            }

            is CalendarEventBarcode -> {
                openCalendar(context, emblem)
            }
        }
    }

    private fun openWifiSettings(context: Context) {
        val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    private fun openUrl(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = android.net.Uri.parse(url)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    private fun openMap(context: Context, lat: Double, lng: Double) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            android.net.Uri.parse("geo:$lat,$lng")
        )
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    private fun openCalendar(context: Context, event: CalendarEventBarcode) {
        val intent = Intent(Intent.ACTION_INSERT)
            .setData(CalendarContract.Events.CONTENT_URI)
            .putExtra(CalendarContract.Events.TITLE, event.summary)
            .putExtra(CalendarContract.Events.DESCRIPTION, event.eventDescription)
            .putExtra(CalendarContract.Events.EVENT_LOCATION, event.location)
            .putExtra(CalendarContract.Events.STATUS, event.status)
            .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, event.start?.toMillis())
            .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, event.end?.toMillis())
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }
}