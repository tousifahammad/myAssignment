package com.tousif.webskitter.ui.activities.splash

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.tousif.webskitter.R
import com.tousif.webskitter.ui.activities.home.HomeActivity
import com.tousif.webskitter.utilities.extensons.gotToActivityWithoutStack
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)

        lifecycleScope.launch {
            delay(1000)
            gotToActivityWithoutStack(HomeActivity::class.java)
        }
    }
}