package judou.ceicheng.com.computerprotect.fragment


import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import judou.ceicheng.com.computerprotect.R
import judou.ceicheng.com.computerprotect.adapter.HomeAdapter
import judou.ceicheng.com.computerprotect.bean.HomeBean
import kotlinx.android.synthetic.main.fragment_home2.*
import kotlinx.android.synthetic.main.fragment_home3.*


/**
 * A simple [Fragment] subclass.
 */
class Home3Fragment : Fragment() {

    var list: MutableList<HomeBean> = ArrayList<HomeBean>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home3, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyData()
        rv_home3.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rv_home3.adapter= HomeAdapter(list, this!!.activity!!, fragmentManager)
    }

    fun initRecyData(){
        val names=arrayOf("风清扬老师","芜湖大司马","RNG-UZI","麻辣香锅",
                "过桥米线","可口可乐")
        val words= arrayOf("这款电脑是真的不错 很流畅 放音乐音质也特别好 还有画质很清晰 总之一句话 很棒",
                "这台也是我哥给我推荐的，，对于不怎么玩游戏的我来这台电脑的性价比已经很可以了，，，而且客服大大也很好客，，，帮我这个电脑小白解决不少的问题",
                "特地试用几天来评价的，电脑运行速度快，操作系统稳定，游戏多开也不卡，客服服务周到，配件齐全，相信戴尔，相信力持",
                "笔记本不错，可以满足日常工作需要，自己贴膜不是很专业，客服热情周到，期待后续使用效果",
                "发货速度快，很快就收到了。电脑挺好的，开机速度快，也不卡。玩个游戏也不会卡，学生用挺好的",
                "用了一段时间才来评论哦！很棒！超高性价比！开机十秒！选的磨砂黑也很赞！客服也很热情❤️跑分十万，这个价格值了")
        val userpics=intArrayOf(R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,R.drawable.pic4
                ,R.drawable.pic5,R.drawable.pic6)
        val pics= intArrayOf(R.drawable.t1,R.drawable.t2,R.drawable.t3,R.drawable.t4,
                R.drawable.t5,R.drawable.t6)
        var n:Int=0
        while(n <pics.size){
            val stu = HomeBean()
            stu.word=words[n]
            stu.name=names[n]
            stu.userpic=userpics[n]
            stu.pic=pics[n]
            list.add(stu)
            n++
        }
    }

}


