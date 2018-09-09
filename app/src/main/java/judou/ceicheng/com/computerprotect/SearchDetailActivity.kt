package judou.ceicheng.com.computerprotect

import android.Manifest
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import judou.ceicheng.com.computerprotect.adapter.SearchDetailCommentAdapter
import kotlinx.android.synthetic.main.activity_search_detail.*
import android.widget.Toast
import android.Manifest.permission
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CALL_PHONE
import android.support.v4.app.ActivityCompat
import android.os.Build
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import android.app.Activity
import android.support.annotation.NonNull
import android.app.job.JobScheduler.RESULT_SUCCESS
import kotlinx.android.synthetic.main.item_searechdetail_bottom.*


class SearchDetailActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_detail)
        getData()
        initRecyData()
        initEvent()
    }

    fun getData(){
        var img= intent.getIntExtra("img",R.drawable.ic_launcher_background)
        var shopintro=intent.getStringExtra("intro")
        var shopprice=intent.getStringExtra("price")
        iv_searchdetail_pic.setImageResource(img)
        tv_searchdetail_intro.setText(shopintro)
        tv_searchdetail_price.setText(shopprice)
    }

    fun initEvent(){
        iv_back.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }
        btn_searchdetail_purchase.setOnClickListener {
            //点击购买
            if (Build.VERSION.SDK_INT > 23) {
                //申请动态权限啊兄弟
                if (checkPermission(this))
                    ActivityCompat.requestPermissions(this, arrayOf( Manifest.permission.CAMERA), 1)
             else
                startActivity(Intent(this,SimpleScannerActivity::class.java))
                //finish()
                //Toast.makeText(this@SearchDetailActivity, "直接进行逻辑", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun checkPermission(activity: Activity): Boolean {
        return (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> if (grantResults.size >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //权限被用户同意了，执行后面逻辑
                startActivity(Intent(this,SimpleScannerActivity::class.java))
                //finish()
            } else
                Toast.makeText(this, "用户否定了这个权限", Toast.LENGTH_LONG).show()
        }
    }



    fun  initRecyData(){
        val items = listOf(
                "1:看着非常好看反应也很快，谢谢店家，以后还来这家买",
                "2:真的实惠 电脑运行速度快 没有想象的那么慢！大大赞",
                "3:让店家的发货的时候下载lol还有cf都给我下载了很满意 玩lol真的很顺畅一点都不卡开了最高配置 很好",
                "4:用了这些天才知道很不错了，很高大上，速度也很快给的东西也很多",
                "5:电脑收到了，店家很细心，包装很好无损坏，价钱实惠，对得起这价位",
                "6:配置和描述的相符，电脑外观漂亮， 开机速度很快，办公和一般的游戏足够了",
                "7:很棒棒，物流也很给力，直接送到了村路口，用过一段时间后再来追评吧，给店家打call！",
                "8:受到了电脑，感觉还不错，颜值也挺高的物流也比较快，先用一段时间看看吧",
                "9:电脑打开就运行开机试了下，下载游戏玩很流畅，不卡机，而且材质质量都不错"
        )


        rv_searchdetail_commment.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rv_searchdetail_commment.adapter = SearchDetailCommentAdapter(items)
    }


}
