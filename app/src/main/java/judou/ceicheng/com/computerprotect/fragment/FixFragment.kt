package judou.ceicheng.com.computerprotect.fragment

import android.app.Fragment
import android.app.FragmentManager
import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import judou.ceicheng.com.computerprotect.R
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import judou.ceicheng.com.computerprotect.adapter.MyFragmentStatePagerAdapter
import judou.ceicheng.com.computerprotect.R.id.mTabLayout
import judou.ceicheng.com.computerprotect.R.id.mViewPager1




/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FixFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 */
class FixFragment : Fragment(), TabLayout.OnTabSelectedListener {
    override fun onTabReselected(tab: TabLayout.Tab?) {
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        mViewPager1!!.setCurrentItem(tab!!.getPosition())
    }

    private var mListener: OnFragmentInteractionListener? = null
    private var mViewPager1: ViewPager? = null
    private var mTabLayout: TabLayout? = null
    private val tabTitle = arrayOf("附近服务", "悬赏接单", "厂商维修")

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_fix, container, false)
        initViews(rootView)
        initDate()
        return rootView
    }

    private fun initViews(rootView: View) {
        mTabLayout=rootView.findViewById(R.id.mTabLayout)
        mViewPager1=rootView.findViewById(R.id.mViewPager1)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }




 @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
 fun initDate(){
     for (i in 0 until tabTitle.size) {
         mTabLayout!!.addTab(mTabLayout!!.newTab().setText(tabTitle[i]))
     }

     mTabLayout!!.tabGravity=TabLayout.GRAVITY_FILL

     mTabLayout!!.setSelectedTabIndicatorColor(Color.parseColor("#7CCD7C"))
     mTabLayout!!.setTabTextColors(Color.GRAY,Color.parseColor("#FF4061"))

     mViewPager1!!.adapter= MyFragmentStatePagerAdapter(getChildFragmentManager(),tabTitle)

     mViewPager1!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(mTabLayout))

     mTabLayout!!.addOnTabSelectedListener(this)

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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }
}// Required empty public constructor
