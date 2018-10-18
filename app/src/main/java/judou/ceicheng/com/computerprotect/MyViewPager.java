package judou.ceicheng.com.computerprotect;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by sunbo on 2018/10/18.
 */

public class MyViewPager extends ViewPager {
    //是否可以进行滑动
    private boolean isSlide = false;
    public void setSlide(boolean slide) {
        isSlide = slide;
    }
    public MyViewPager(@NonNull Context context) {
        super(context);
    }

    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isSlide;
    }
}
