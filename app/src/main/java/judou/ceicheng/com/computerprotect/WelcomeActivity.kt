package judou.ceicheng.com.computerprotect

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import judou.ceicheng.com.computerprotect.util.FullScreen
import judou.ceicheng.com.computerprotect.util.util
import kotlinx.android.synthetic.main.activity_welcome.*
import java.util.*
import android.content.pm.PackageManager
import android.Manifest.permission
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.support.v4.content.ContextCompat
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CALL_PHONE
import android.app.Activity
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.widget.Toast
import android.support.annotation.NonNull




class WelcomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        FullScreen.fullScreen(this,0x7e555555)
        val it = Intent(this, LoginActivity::class.java)
        val timer = Timer()
        val task = object : TimerTask() {
            override fun run() {

                startActivity(it)
                finish()
            }
        }
        timer.schedule(task, 1000 * 2)

        tv_tiaoguo.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
            timer.cancel()
        }
    }


}
