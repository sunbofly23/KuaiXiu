package judou.ceicheng.com.computerprotect.fragment


import android.os.Bundle
import android.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import judou.ceicheng.com.computerprotect.R
import judou.ceicheng.com.computerprotect.adapter.HomeAdapter
import judou.ceicheng.com.computerprotect.adapter.WorkPlaceAdapter
import judou.ceicheng.com.computerprotect.bean.HomeBean
import judou.ceicheng.com.computerprotect.bean.WorkBean
import kotlinx.android.synthetic.main.fragment_blank_fragment3.*
import kotlinx.android.synthetic.main.fragment_home2.*
import org.greenrobot.eventbus.EventBus


/**
 * A simple [Fragment] subclass.
 */
class BlankFragment3 : Fragment() {

    var list: MutableList<WorkBean> = ArrayList<WorkBean>()
    var adapt: WorkPlaceAdapter?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank_fragment3, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initDate()
        initViews()
    }


    private fun initDate() {
        val names = arrayOf("联想", "三星", "苹果", "华硕",
                "神舟", "戴尔")
        val address = arrayOf("中国北京/美国罗利",
                "韩国京畿道城南市盆唐区书岘洞263号三星广场大厦",
                "美国加利福尼亚州库比蒂诺市",
                "上海市闵行区金都路5077号",
                "深圳市龙岗区坂雪岗工业区新天下工业城1号",
                "美国德克萨斯州朗德罗克")
        var phones= arrayOf("400-898-9006","400-810-5858","400-666-8800","400-652-3100","400-106-9999","400-884-9423")

        var n: Int = 0
        while (n < names.size) {
            val stu = WorkBean()
            stu.names = names[n]
            stu.phones = phones[n]
            stu.address = address[n]
            list.add(stu)
            n++
        }
    }

    private fun initViews() {
        connect.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        adapt= WorkPlaceAdapter(list, this!!.activity!!, fragmentManager)
        connect.adapter= adapt
    }


}// Required empty public constructor
