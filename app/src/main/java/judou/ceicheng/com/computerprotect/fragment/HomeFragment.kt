package judou.ceicheng.com.computerprotect.fragment

import android.app.Fragment
import android.app.FragmentManager
import android.app.FragmentTransaction
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.*
import judou.ceicheng.com.computerprotect.FabuActivity

import judou.ceicheng.com.computerprotect.R
import judou.ceicheng.com.computerprotect.bean.HomeBean
import kotlinx.android.synthetic.main.fragment_home.*
import android.view.LayoutInflater
import judou.ceicheng.com.computerprotect.R.layout.fragment_search
import kotlinx.android.synthetic.main.fragment_home.view.*


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [HomeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 */
class HomeFragment : Fragment() {

    private var mListener: OnFragmentInteractionListener? = null
    var list :MutableList<HomeBean> = ArrayList<HomeBean>()
    var fragment: Fragment? = null
    var fragment1: Fragment? = null
    lateinit var fragmentTransaction: FragmentTransaction

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        iv_fabu.setOnClickListener { startActivity(Intent(activity,FabuActivity::class.java)) }
        showFragmentZuiXin(0)
        tv_zuixin.setOnClickListener {
            tv_zuixin.setTextColor(Color.parseColor("#ffffff"))
            tv_zuixin.setBackgroundResource(R.drawable.hometexttoptx)
            tv_zuire.setTextColor(Color.parseColor("#333333"))
            tv_zuire.setBackgroundResource(R.drawable.hometexttoptx2)
            showFragmentZuiXin(0)
        }

            tv_zuire.setOnClickListener {
                    tv_zuire.setTextColor(Color.parseColor("#ffffff"))
                    tv_zuire.setBackgroundResource(R.drawable.hometexttoptx)
                    tv_zuixin.setTextColor(Color.parseColor("#333333"))
                    tv_zuixin.setBackgroundResource(R.drawable.hometexttoptx2)
                showFragmentZuiXin(1)
                }
            }



    fun showFragmentZuiXin(i:Int){
        val  fragmentManager:FragmentManager = this.fragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        HideFragement()
        when(i){
            0->{
                if (fragment!=null)
                    fragmentTransaction.show(fragment)
                else {
                    fragment = Home2Fragment()
                    fragmentTransaction.add(R.id.content_frag, fragment)
                }
            }
           1->{
                if (fragment1!=null)
                    fragmentTransaction.show(fragment1)
                else {
                    fragment1 = Home3Fragment()
                    fragmentTransaction.add(R.id.content_frag, fragment1)
                }
            }
        }
        fragmentTransaction.commit()
    }



    fun HideFragement() {
        if (fragment1 != null) {
            fragmentTransaction.hide(fragment1)
        }
        if (fragment != null) {
            fragmentTransaction.hide(fragment)
        }

    }






    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
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
}// Required empty public constructor
