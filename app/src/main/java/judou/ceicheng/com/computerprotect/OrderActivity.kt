package judou.ceicheng.com.computerprotect

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_order.*
import java.text.SimpleDateFormat
import java.util.*
import android.app.DatePickerDialog
import android.app.FragmentManager
import android.text.format.DateFormat
import android.widget.DatePicker
import android.widget.Toast
import judou.ceicheng.com.computerprotect.fragment.AlipayDialogFragment


class OrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        initEvent()
    }

    private fun initEvent() {
        //价格
        tv_price.setText(intent.getStringExtra("price"))
        //下单时间
        var df:SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        tv_time.setText(df.format(Date()))

        //期望送达
         tv_time1.setOnClickListener {
             val c = Calendar.getInstance()
             val dialog = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
                 override
                 fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
                     c.set(year, monthOfYear, dayOfMonth)
                     tv_time1.setText(DateFormat.format("yyy-MM-dd", c))
                 }
             }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH))
             dialog.show()
         }
          //提交订单
         btn_commit.setOnClickListener {
                  if(et_name.text.toString().isEmpty()||et_phone.text.toString().isEmpty()||
                          et_address.text.toString().isEmpty()||tv_time1.text.toString().isEmpty())
                      Toast.makeText(this,"你的输入有空，检查后在提交",Toast.LENGTH_SHORT).show()
             else{
                  var frag: AlipayDialogFragment= AlipayDialogFragment.getInstance(this)
                      frag.show(fragmentManager,"ffff")
                  }
         }
         //返回
        iv_back.setOnClickListener {
              finish();
        }

    }
}
