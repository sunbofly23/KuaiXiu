package judou.ceicheng.com.computerprotect

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import judou.ceicheng.com.computerprotect.util.FullScreen
import judou.ceicheng.com.computerprotect.util.util
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        FullScreen.fullScreen(this,0x7e555555)
        initEvent()
    }

    private fun initEvent() {
        iv_back.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
        btn_regitser.setOnClickListener {
            val username=et_username.text.toString()
            val password=et_password.text.toString()
            val twopassword=et_twopassword.text.toString()
            val captcha=et_captcha.text.toString()
            if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password)
                    ||TextUtils.isEmpty(twopassword)||TextUtils.isEmpty(captcha))
                util.Toastshow(this,"输入的值有空，请检查")
            else if(!checkCaptcha(captcha))
                      util.Toastshow(this,"验证码不正确，刷新后再试")
            else{
                if(!password.equals(twopassword))
                    util.Toastshow(this,"两次密码不一致，请检查")
                else{
                    if(username.equals("kuaixiu")&&password.equals("android258")) {
                        util.Toastshow(this,"注册成功")
                        startActivity(Intent(this, LoginActivity::class.java))
                    }
                    else
                        util.Toastshow(this,"注册失败，请使用测试账号")
                }
            }
        }

        vv_captcha.setOnClickListener {
            vv_captcha.regenerate();
        }
    }
    fun checkCaptcha(captcha:String):Boolean{
        return captcha.equals(vv_captcha.captchaCode)
    }
}
