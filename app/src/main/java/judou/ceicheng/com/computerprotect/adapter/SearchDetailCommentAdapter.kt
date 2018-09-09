package judou.ceicheng.com.computerprotect.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import judou.ceicheng.com.computerprotect.R
import kotlinx.android.synthetic.main.item_searchdetailcomment.view.*

/**
 * @Class SearchDetailCommentAdapter
 * @Author sunbo
 * @DATE 2018/8/11 19:06
 * @Explanatory
 */
class SearchDetailCommentAdapter(val items : List<String>) : RecyclerView.Adapter<SearchDetailCommentAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_searchdetailcomment, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.tv_comment.text = items[position]
    }

    override fun getItemCount(): Int {
      if (items.isEmpty())
          return 0
        else
          return items.size
    }
    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView){

    }

}