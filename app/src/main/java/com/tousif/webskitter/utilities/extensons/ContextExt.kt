package com.tousif.webskitter.utilities.extensons

import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build

/**Extension method: to another activity*/
fun Context.gotToActivity(cls: Class<*>) {
    trycatch {
        Intent(this, cls).also {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            this.startActivity(it)
        }
    }
}

/**Extension method: to another activity without stack. it clear all previous stack*/
fun Context.gotToActivityWithoutStack(cls: Class<*>) {
    trycatch {
        Intent(this, cls).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            this.startActivity(it)
        }
    }
}

/**Extension method: to another activity with parameters.*/
fun Context.gotToActivityWithHashMap(cls: Class<*>?, myMap: HashMap<String, String>) {
    trycatch {
        Intent(this, cls).also {
            for (key in myMap.keys) {
                it.putExtra(key, myMap[key])
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            this.startActivity(it)
        }
    }
}

/**Extension method: to play notification sound.*/
fun Context.playNotificationSound() {
    trycatch {
        val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val r = RingtoneManager.getRingtone(this, notification)
        r.play()
    }
}
/*

*/
/**Extension method for vibration.*//*

fun Context.vibrate(duration: Long) {
    val vib = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        vib.vibrate(VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE))
    } else {
        @Suppress("DEPRECATION")
        vib.vibrate(duration)
    }
}

*/
/**Check Connectivity*//*

val Context.isConnected: Boolean
    get() {
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                val nw = connectivityManager?.activeNetwork ?: return false
                val actNw = connectivityManager?.getNetworkCapabilities(nw) ?: return false
                when {
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    else -> false
                }
            }
            else -> {
                // Use depreciated methods only on older devices
                val nwInfo = connectivityManager?.activeNetworkInfo ?: return false
                nwInfo.isConnected
            }
        }
    }

*/
/**Check Internet*//*

val Context.isNetworkConnectionAvailable: Boolean
    get() {
        return if (isConnected) {
            try {
                val command = "ping -c 1 google.com"
                Runtime.getRuntime().exec(command).waitFor() == 0
            } catch (e: java.lang.Exception) {
                false
            }
        } else {
            false
        }
    }

*/
/**Check if the device has an internet connection
 * @return True if the device is connected to a network which also gives it access to the internet.
 * False otherwise.
 *//*

val Context.isInternetConnectionAvailable: Boolean
    get() {
        return if (isConnected) {
            val nw = connectivityManager?.activeNetwork ?: return false
            val actNw = connectivityManager?.getNetworkCapabilities(nw) ?: return false
            */
/*If we check only for "NET_CAPABILITY_INTERNET", we get "true" if we are connected to a wifi
            which has no access to the internet. "NET_CAPABILITY_VALIDATED" also verifies that we are online*//*

            when {
                actNw.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) && actNw.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED) -> true
                else -> false
            }
        } else {
            false
        }
    }

*/
/**Ext for Wifi Manager*//*

inline val Context.wifiManager: WifiManager
    get() = getSystemService(Context.WIFI_SERVICE) as WifiManager

*/
/**Extension method to get connectivityManager for Context.*//*

inline val Context.connectivityManager: ConnectivityManager?
    get() = getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager*/



/**Check Connectivity*/
val Context.isConnected: Boolean
    get() {
        val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                val nw = connectivityManager.activeNetwork ?: return false
                val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
                when {
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    else -> false
                }
            }
            else -> {
                // Use depreciated methods only on older devices
                val nwInfo = connectivityManager.activeNetworkInfo ?: return false
                nwInfo.isConnected
            }
        }
    }

/**Check Internet*/
val Context.isNetworkConnectionAvailable: Boolean
    get() {
        return if (isConnected) {
            try {
                val command = "ping -c 1 google.com"
                Runtime.getRuntime().exec(command).waitFor() == 0
            } catch (e: java.lang.Exception) {
                false
            }
        } else {
            false
        }
    }