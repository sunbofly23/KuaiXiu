package judou.ceicheng.com.computerprotect.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import judou.ceicheng.com.computerprotect.R
import judou.ceicheng.com.computerprotect.SearchDetailActivity
import judou.ceicheng.com.computerprotect.bean.SearchRecyBean
import kotlinx.android.synthetic.main.item_search.view.*

/**
 * @Class SearchAdapter
 * @Author sunbo
 * @DATE 2018/8/10 17:59
 * @Explanatory
 */
class SearchAdapter(val items : List<SearchRecyBean> ,val context:Context) : RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return MyViewHolder(view)
     }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.tv_brand_item.text=items.get(position).brand
        holder.itemView.tv_shopintro_item.text=items.get(position).shopintro
        holder.itemView.tv_shopname_item.text=items.get(position).shopname
        holder.itemView.tv_shopprice_item.text=items.get(position).shopprice
        Glide.with(context).load(items.get(position).pic).placeholder(R.drawable.ic_launcher_background).into(holder.itemView.iv_pic_item)
        holder.itemView.setOnClickListener {
            val intent= Intent()
            intent.putExtra("img",items[position].pic)
            intent.putExtra("price",items[position].shopprice)
            intent.putExtra("intro",items[position].shopintro)
            context.startActivity(intent.setClass(context,SearchDetailActivity::class.java))
        }

    }

    override fun getItemCount(): Int {
        if(items.isEmpty())
            return 1
        else
       return items.size
    }
    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView){

    }

}