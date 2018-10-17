package judou.ceicheng.com.computerprotect.fragment


import android.os.Bundle
import android.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.sunbo.excel.RewardAdater

import judou.ceicheng.com.computerprotect.R
import judou.ceicheng.com.computerprotect.bean.RewordBean
import kotlinx.android.synthetic.main.fragment_blank_fragment2.*


/**
 * A simple [Fragment] subclass.
 */
class BlankFragment2 : Fragment() {

    var items: MutableList<RewordBean> = ArrayList<RewordBean>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank_fragment2, container, false)

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initDate()
        initRecy()
    }

    private fun initDate() {
        val names = arrayOf("凹凸曼", "大司马", "洞箫肖", "小蚂蚁",
                "张无忌", "但丁牛")

        val words = arrayOf("外星人英雄联盟S8珍藏版高配置笔记本，低价20000人民币",
                "2015款mac pro 维修悬赏，更换键盘",
                "我的thinkpad笔记本周六在西校区丢失，如有捡到，奖赏1000人民币",
                "2018款mac pro 马可波罗高内存珍藏版笔记本，低价66666人民币",
                "华硕飞行堡垒 维修悬赏，更换固态硬盘，加内存条",
                "戴尔黄金典藏版笔记本，内存999G，拍卖低价99999人民币")

        val types= arrayOf("珍品拍卖","维修悬赏","失物寻找","珍品拍卖","维修悬赏","珍品拍卖")

        var pics= intArrayOf(R.drawable.waixingren,R.drawable.macpro,R.drawable.thinkpad,R.drawable.mapro,R.drawable.asus,R.drawable.dell)

        var phones= arrayOf("13910599102","13910519707","13911775723","13910599938","13910056666","13911395552")
        var n: Int = 0
        while (n < words.size) {
            val stu = RewordBean()
            stu.name=names[n]
            stu.describe=words[n]
            stu.type=types[n]
            stu.pic=pics[n]
            stu.phone=phones[n]
            items.add(stu)
            n++
        }
    }


    fun initRecy() {
        reword.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL,false)
        reword.adapter= RewardAdater(items,activity)
    }

}// Required empty public constructor
