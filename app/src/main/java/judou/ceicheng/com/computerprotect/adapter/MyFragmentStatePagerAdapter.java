package judou.ceicheng.com.computerprotect.adapter;

import android.app.Fragment;
import android.app.FragmentManager;

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