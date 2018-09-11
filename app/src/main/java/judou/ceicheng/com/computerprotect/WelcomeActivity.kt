package judou.ceicheng.com.computerprotect

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import judou.ceicheng.com.computerprotect.util.FullScreen
import judou.ceicheng.com.computerprotect.util.util
import kotlinx.android.synthetic.main.activity_welcome.*
import java.util.*


class WelcomeActivity : AppCompatActivity() {

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
        }




    }
}
