package judou.ceicheng.com.computerprotect

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import judou.ceicheng.com.computerprotect.R.layout.item
import judou.ceicheng.com.computerprotect.util.FullScreen
import judou.ceicheng.com.computerprotect.util.util
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        FullScreen.fullScreen(this,0x7e555555)
        initEvent()

    }


    private fun initEvent(){
        btn_login.setOnClickListener {

            var username= et_username.text.toString()
            var password =et_password.text.toString()
           if(username.isEmpty()||password.isEmpty()){
               util.Toastshow(this,"输入有空，请检查！")
           }
          else{
               if(username.equals("kuaixiu")&&password.equals("android258")) {
                   startActivity(Intent(this, MainActivity::class.java))
                   finish()
               }
               else
                   util.Toastshow(this,"账号或密码错误，请检查！")
           }
        }
          tv_register.setOnClickListener {
              startActivity(Intent(this,RegisterActivity::class.java))
          }

      }




}
