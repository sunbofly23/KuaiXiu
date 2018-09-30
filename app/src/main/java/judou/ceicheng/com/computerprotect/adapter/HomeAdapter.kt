package judou.ceicheng.com.computerprotect.adapter

import android.app.Dialog
import android.app.FragmentManager
import android.content.Context
import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.wx.goodview.GoodView
import judou.ceicheng.com.computerprotect.R
import judou.ceicheng.com.computerprotect.bean.HomeBean
import judou.ceicheng.com.computerprotect.fragment.HomeSharedialog
import kotlinx.android.synthetic.main.item_rv_home.view.*
import android.view.*
import android.widget.LinearLayout
import judou.ceicheng.com.computerprotect.bean.EventHomeBean
import java.io.ByteArrayOutputStream
import java.util.*


/**
 * Created by sunbo on 2018/9/9.
 */
class HomeAdapter(val items: MutableList<HomeBean>, var context: Context, var fragment: FragmentManager) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    var dialog:Dialog?=null
    var  btn1: LinearLayout?=null
    var  btn2: LinearLayout?=null
    var  btn3: LinearLayout?=null

    override fun getItemCount(): Int {
        if(items.isEmpty())
            return 1
        else
       return items.size
    }


     fun  addItem(pos:Int,date: HomeBean){
         items.add(pos,date)
         notifyItemInserted(pos)
         notifyItemRangeChanged(pos,items.size-pos)//通知数据与界面重新绑定
     }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        Glide.with(context).load(items[position].pic).into(holder.itemView.iv_picture)
        Glide.with(context).load(items[position].userpic).into(holder.itemView.iv_authorhead)
        holder.itemView.tv_authorname.text = items[position].name
        holder.itemView.tv_words.text=items[position].word

        var view :GoodView= GoodView(context)
        holder.itemView.iv_like.setOnClickListener {
            view.setImage(R.drawable.home0)
            holder.itemView.iv_like.setImageResource(R.drawable.home0)
            holder.itemView.tv_likecount.text= (holder.itemView.tv_likecount.text.toString().toInt()+1).toString()
            view.show(it)
        }

        holder.itemView.iv_comment.setOnClickListener {
            HomeSharedialog.getInstance(HomeSharedialog.InterfaceExample {
                if(holder.itemView.ll_comment.visibility==View.GONE){
                    Toast.makeText(context,"评论成功",Toast.LENGTH_SHORT).show()
                    holder.itemView.ll_comment.visibility=View.VISIBLE;
                    holder.itemView.tv_commentcount.text="1"
                    holder.itemView.tv_comment.text=it
                }
                else
                    Toast.makeText(context,"您已经评论过了啊，歇息歇息~",Toast.LENGTH_SHORT).show()
            }).show(fragment,"ffff")
        }

        holder.itemView.iv_share.setOnClickListener {
            showDialogShare()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_home, parent, false)
        return HomeAdapter.ViewHolder(view)
    }

    fun showDialogShare() {
        dialog =  Dialog(context, R.style.ActionSheetDialogStyle)
        //填充对话框的布局
        var inflate:View = LayoutInflater.from(context).inflate(R.layout.layout_share, null)
        //初始化控件
        //将布局设置给Dialog
        btn1=inflate!!.findViewById(R.id.ll_f1)
        btn2 =inflate!!.findViewById(R.id.ll_f2)
        btn3 =inflate!!.findViewById(R.id.ll_f3)

        dialog!!.setContentView(inflate)
        //获取当前Activity所在的窗体
        var  dialogWindow : Window = dialog!!.getWindow()
        if(false){
            return
        }
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM)
        //获得窗体的属性
        var lp: WindowManager.LayoutParams  = dialogWindow.getAttributes()
        val dm = context.getResources().getDisplayMetrics()
        lp.width=dm.widthPixels
        lp.y = 20//设置Dialog距离底部的距离
        //将属性设置给窗体
        dialogWindow.setAttributes(lp)
        dialog!!.show()//显示对话框

        btn1!!.setOnClickListener {
            Toast.makeText(context,"分享微博",Toast.LENGTH_SHORT).show()
        }
        btn2!!.setOnClickListener {
            Toast.makeText(context,"分享微信",Toast.LENGTH_SHORT).show()
        }
        btn3!!.setOnClickListener {
            Toast.makeText(context,"分享QQ",Toast.LENGTH_SHORT).show()
        }

    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

}