package judou.ceicheng.com.computerprotect.adapter

import android.app.FragmentManager
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import judou.ceicheng.com.computerprotect.R
import judou.ceicheng.com.computerprotect.bean.WorkBean
import kotlinx.android.synthetic.main.item_phone_connect.view.*

/**
 * Created by sunbo on 2018/10/14.
 */
class WorkPlaceAdapter(val items: MutableList<WorkBean>, var context: Context, var fragment: FragmentManager) : RecyclerView.Adapter<WorkPlaceAdapter.ViewHolder1>() {
    override fun onBindViewHolder(holder: ViewHolder1, position: Int) {

        holder.itemView.tv_workplace.text=items[position].names
        holder.itemView.tv_address.text=items[position].address
        holder.itemView.tv_phone.text=items[position].phones
    }

    override fun getItemCount(): Int {
        if (items.isEmpty())
            return 0
        else
            return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder1 {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_phone_connect, parent, false)
        return WorkPlaceAdapter.ViewHolder1(view)
    }


    class ViewHolder1(itemView: View?) : RecyclerView.ViewHolder(itemView){

    }


}