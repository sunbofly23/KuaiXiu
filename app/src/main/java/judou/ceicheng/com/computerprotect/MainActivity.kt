package judou.ceicheng.com.computerprotect

import android.app.Fragment
import android.app.FragmentManager
import android.app.FragmentTransaction
import android.net.Uri

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import judou.ceicheng.com.computerprotect.fragment.FixFragment
import judou.ceicheng.com.computerprotect.fragment.HomeFragment
import judou.ceicheng.com.computerprotect.fragment.SearchFragment
import kotlinx.android.synthetic.main.bottom_main.*

class MainActivity : BaseActivity(), View.OnClickListener, SearchFragment.OnFragmentInteractionListener, HomeFragment.OnFragmentInteractionListener, FixFragment.OnFragmentInteractionListener{
    override fun onFragmentInteraction(uri: Uri) {

    }

    internal var fragment_search: Fragment? = null
    internal var fragment_home: Fragment? = null
    internal var fragment_fix: Fragment? = null
    lateinit var fragmentTransaction: FragmentTransaction
    private val FRAGMENT_TAG = arrayOf("search", "home", "fix")
    private var selindex=0;
    private val PRV_SELINDEX = "PREV_SELINDEX"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)
        InitEvent()
        var fragmentManager:FragmentManager = fragmentManager
        if(savedInstanceState !=null){
            selindex=savedInstanceState.getInt(PRV_SELINDEX,selindex)
            fragment_search=fragmentManager.findFragmentByTag(FRAGMENT_TAG[0])
            fragment_home=fragmentManager.findFragmentByTag(FRAGMENT_TAG[1])
            fragment_fix=fragmentManager.findFragmentByTag(FRAGMENT_TAG[2])
        }

        SetSelect(selindex)
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        outState!!.putInt(PRV_SELINDEX,selindex)
        super.onSaveInstanceState(outState, outPersistentState)
    }

    private fun InitEvent() {
        ll_f1.setOnClickListener(this)
        ll_f2.setOnClickListener(this)
        ll_f3.setOnClickListener(this)
    }


    override fun onClick(v: View) {
        ResetImage()
        when (v.id) {
            R.id.ll_f1 -> SetSelect(0)
            R.id.ll_f2 -> SetSelect(1)
            R.id.ll_f3 -> SetSelect(2)
        }
    }


    private fun ResetImage() {
        iv_f1.setImageResource(R.drawable.f1)
        iv_f2.setImageResource(R.drawable.f2)
        iv_f3.setImageResource(R.drawable.f3)
    }


    private fun SetSelect(i: Int) {
       val  fragmentManager:FragmentManager = this.fragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        HideFragement()
        when (i) {
            0 -> {
                if (fragment_search == null) {
                    fragment_search = SearchFragment()
                    fragmentTransaction.add(R.id.fl_content, fragment_search)
                } else {
                    fragmentTransaction.show(
                            fragment_search
                    )
                }
                iv_f1.setImageResource(R.drawable.f1s)
            }
            1 -> {
                if (fragment_home == null) {
                    fragment_home = HomeFragment()
                    fragmentTransaction.add(R.id.fl_content, fragment_home)
                } else {
                    fragmentTransaction.show(fragment_home)
                }
                iv_f2.setImageResource(R.drawable.f2s)
            }
            2 -> {
                if (fragment_fix == null) {
                    fragment_fix = FixFragment()
                    fragmentTransaction.add(R.id.fl_content, fragment_fix)
                } else {
                    fragmentTransaction.show(fragment_fix)
                }
                iv_f3.setImageResource(R.drawable.f3s)
            }
        }
        fragmentTransaction.commit()
    }



    fun HideFragement() {
        if (fragment_search != null) {
            fragmentTransaction.hide(fragment_search)
        }
        if (fragment_home != null) {
            fragmentTransaction.hide(fragment_home)
        }
        if (fragment_fix != null) {
            fragmentTransaction.hide(fragment_fix)
        }
    }
}
