package judou.ceicheng.com.computerprotect.adapter;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;

import judou.ceicheng.com.computerprotect.fragment.BlankFragment;
import judou.ceicheng.com.computerprotect.fragment.BlankFragment2;
import judou.ceicheng.com.computerprotect.fragment.BlankFragment3;


public class MyFragmentStatePagerAdapter extends FragmentPagerAdapter {
    private String[] tabTilte;

    public MyFragmentStatePagerAdapter(FragmentManager fm, String[] tabTitle) {
        super(fm);
        this.tabTilte = tabTitle;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new BlankFragment();
            case 1:
                return new BlankFragment2();
            case 2:
                return new BlankFragment3();
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabTilte.length;
    }


}