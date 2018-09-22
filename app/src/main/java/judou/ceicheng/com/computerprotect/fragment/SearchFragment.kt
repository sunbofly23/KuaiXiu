package judou.ceicheng.com.computerprotect.fragment

import android.app.AlertDialog
import android.app.Fragment
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import judou.ceicheng.com.computerprotect.FabuActivity
import judou.ceicheng.com.computerprotect.LoginActivity
import judou.ceicheng.com.computerprotect.R
import judou.ceicheng.com.computerprotect.R.id.rv_search
import judou.ceicheng.com.computerprotect.R.id.sousuo
import judou.ceicheng.com.computerprotect.adapter.SearchAdapter
import judou.ceicheng.com.computerprotect.bean.SearchRecyBean
import judou.ceicheng.com.computerprotect.bean.SearchSelectBean
import judou.ceicheng.com.computerprotect.window.SearchSelectedWindow
import kotlinx.android.synthetic.main.activity_main1.*
import kotlinx.android.synthetic.main.fragment_search.*


@Suppress("UNREACHABLE_CODE")
class SearchFragment : Fragment() {

    private var mListener: OnFragmentInteractionListener? = null
    var beanList: MutableList<SearchRecyBean> = ArrayList(13)
    var window: SearchSelectedWindow? =null
    var searchList :MutableList<SearchSelectBean> = ArrayList<SearchSelectBean>()
    var beanList2: MutableList<SearchRecyBean> = ArrayList(2)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
        toolbar.setNavigationOnClickListener {
            drawlayout?.openDrawer(GravityCompat.START)
        }
        val actionBar = (activity as AppCompatActivity).supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.pichead)
        }

        nav_view.setCheckedItem(R.id.nav_call)
        nav_view.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_call->
                    Toast.makeText(activity,it.title,Toast.LENGTH_SHORT).show()
                R.id.nav_friend->
                    Toast.makeText(activity,it.title,Toast.LENGTH_SHORT).show()
                R.id.nav_location->
                    Toast.makeText(activity,it.title,Toast.LENGTH_SHORT).show()
                R.id.nav_mail->
                    Toast.makeText(activity,it.title,Toast.LENGTH_SHORT).show()
                R.id.nav_task->
                    Toast.makeText(activity,it.title,Toast.LENGTH_SHORT).show()
                R.id.nav_signout->{
                    AlertDialog.Builder(activity)
                            .setMessage("确定要退出吗?")
                            .setPositiveButton("退出", DialogInterface.OnClickListener { _, _ ->
                                startActivity(Intent(activity, LoginActivity::class.java))
                            }
                            )
                            .setNegativeButton("取消", null)
                            .create()
                            .show()
                }


            }
            return@setNavigationItemSelectedListener true
        }
        initData()
        rv_search.layoutManager = GridLayoutManager(activity, 2)
        val a=SearchAdapter(beanList, this!!.activity!!)
        a.notifyDataSetChanged()
        rv_search.adapter=a
        initParam()
    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        if (inflater != null) {
            menu!!.clear()
            inflater.inflate(R.menu.toolbar,menu)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when(item.itemId){
                R.id.sousuo->{
                    Toast.makeText(activity, "暂不支持店铺和品牌一起选择", Toast.LENGTH_SHORT).show()
                    if(beanList2.size>0){
                        for(i in  beanList2)
                            beanList2.removeAt(0)
                    }
                    window= SearchSelectedWindow(activity,searchList)
                    window!!.showAsDropDown(toolbar)
                    window!!.setOnConfirmClickListener(SearchSelectedWindow.OnConfirmClickListener {
                        val sb: StringBuffer = StringBuffer()
                        for (fb in searchList) {
                            val cdList = fb.getChildren()
                            for (x in cdList.indices) {
                                val children = cdList.get(x)
                                if (children.isSelected)
                                    sb.append(children.getValue())
                            }
                        }
                        if (!TextUtils.isEmpty(sb.toString())){
                            for (i in beanList.indices) {
                                if (beanList[i].shopname == sb.toString()) {
                                    beanList2.add(beanList[i])
                                    searchMethod(beanList2)
                                    break
                                }
                                if (beanList[i].brand == sb.toString()) {
                                    beanList2.add(beanList[i])
                                    searchMethod(beanList2)
                                    break
                                }
                            }

                        }
                    })
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private  fun searchMethod(search :MutableList<SearchRecyBean>){
        rv_search.layoutManager = GridLayoutManager(activity, 1)
        val a = SearchAdapter(search, this!!.activity!!)
        a.notifyDataSetChanged()
        rv_search.adapter = a
//            initData()
//            rv_search.layoutManager = GridLayoutManager(activity, 2) as RecyclerView.LayoutManager?;
//            val a1=SearchAdapter(beanList, this!!.activity!!)
//            a.notifyDataSetChanged()
//            rv_search.adapter=a1

    }

    //初始化搜索窗口数据
    private fun initParam() {
        val windowshops=arrayOf("行星电脑店","北斗电脑店","游戏电脑店","灰色电脑店",
                "一背电脑店","风行电脑店","赛格电脑城","红星电脑城"
                ,"星星电脑店","雷神游戏店","超人电脑店","亿人电脑城","星际电脑城")
        val windowbrands= arrayOf("联想","宏基","微星","苹果","惠普","雷神","外星人","华硕","未来人类","神舟","狼人","平民","星空")
       //设置店铺数据
        val bean1 :SearchSelectBean= SearchSelectBean()
        bean1.typeName="店铺"
        val list1 :MutableList<SearchSelectBean.Children> = ArrayList<SearchSelectBean.Children>()
        for (i in windowshops.indices) {
         val cd : SearchSelectBean.Children =SearchSelectBean.Children()
            cd.value= windowshops[i]
            list1.add(cd)
        }
        bean1.children=list1

        //设置品牌数据
        val bean2 :SearchSelectBean= SearchSelectBean()
        bean2.typeName="品牌"
        val list2 :MutableList<SearchSelectBean.Children> = ArrayList<SearchSelectBean.Children>()
        for (i in windowbrands.indices) {
            val cd : SearchSelectBean.Children =SearchSelectBean.Children()
            cd.value= windowbrands[i]
            list2.add(cd)
        }
        bean2.children=list2

        searchList.add(bean1)
        searchList.add(bean2)
    }


    //初始化recycleview数据
    fun initData(){
        val shops=arrayOf("行星电脑店","北斗电脑店","游戏电脑店","灰色电脑店",
                "一背电脑店","风行电脑店","赛格电脑城","红星电脑城"
                ,"星星电脑店","雷神游戏店","超人电脑店","亿人电脑城","星际电脑城")
        val brands= arrayOf("联想","宏基","微星","苹果","惠普","雷神","外星人","华硕","未来人类","神舟","狼人","平民","星空")
        val prices=arrayOf("6000元","8500元","7200元","4500元","12000元","5800元","6200元","6300元","7000元","5500元","9000元","4800元","3600元")
        val intros= arrayOf("来自中国大陆， 但这两年联想的性价比渐渐也开始高了起来，不仅在质量上，" +
                "还是在配置上都令人很吃惊，大容量内存，大硬盘非常翘楚" ,
                "一直以性价比突出为主要卖点，作为拥有独显和无线的本本，性价比可谓非常超值。" +
                        "售后也不错，质保期在同类品牌中最长很方便全球连保",
                "一直以性价比突出为主要卖点，作为拥有独显和无线的本本，性价比可谓非常超值。售后也不错，" +
                "质保期在同类品牌中最长很方便全球连保",
                "苹果机因为原来只有白色，俗称“小白”。苹果机往往代表了潮流和时尚，代表了高端于精美的工业设计，" +
                        "其产品在图形图像处理领域",
                "商用笔记本较为时尚，性能不俗。惠普独有的8大智慧设计，超亮屏技术等，画面更加明亮、清晰，" +
                        "价格更有竞争力。目前HP惠普笔记本主流有三大系列"
        ,"高端品牌的低价化。十一的时候，陪朋友去买，商务本性价比超高",
                "中高端笔记本,性能很好,质量还不错。保证质量的同时配置又非常的好，" +
                        "唯一遗憾的一点就是外观时尚性还需进一步设计",
                "华硕是华人电脑的一个不大不小的骄傲，坚持“自己设计、自己制造、自有品牌销售”。" +
                        "除了自有芯片，主板，华硕还给其它品牌做代加工",
                "高端机型，优点很多，看配置就知道了，无需多说"
        ,"中国大陆除联想以外唯一在国际上创出名气的品牌","" +
                "来自美国，如果熟悉计算机发展史的话都应该记忆犹新的！许多外观都非常的漂亮",
                "世界独一无二的系列,硬盘保护系统,包括指纹,蓝牙系统，独立显卡",
                "F41,F31系列一直很受人的重视，销量都很好，经常断货")

        val pics:IntArray=intArrayOf(R.drawable.c1,R.drawable.c2,R.drawable.c3,R.drawable.c4,R.drawable.
              c5,R.drawable.c6,R.drawable.c7,R.drawable.c8,R.drawable.c9,R.drawable.c10,R.drawable.c11,
                R.drawable.c12,R.drawable.c13)

        var n:Int=0
        while(n <pics.size){
            val stu =SearchRecyBean()
            stu.shopname=shops[n]
            stu.shopintro=intros[n]
            stu.shopprice=prices[n]
            stu.brand=brands[n]
            stu.pic=pics[n]
            beanList.add(stu)
            //Log.i("====================",beanList.size.toString())
            n++
    }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }
}
