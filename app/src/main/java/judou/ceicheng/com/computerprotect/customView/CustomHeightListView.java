package judou.ceicheng.com.computerprotect.customView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.AbstractInputMethodService;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.ListView;

/**
 * @Class CustomHeightListView
 * @Author sunbo
 * @DATE 2018/8/15 19:51
 * @Explanatory
 */
public class CustomHeightListView extends ListView {

    private Context mContext;

    public CustomHeightListView(Context context) {
        this(context, null);
    }

    public CustomHeightListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomHeightListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomHeightListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context) {
        mContext = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //最大高度显示为屏幕内容高度的一半
        Display display=((Activity)mContext).getWindowManager().getDefaultDisplay();
        @SuppressLint("DrawAllocation")
        DisplayMetrics displayMetrics=new DisplayMetrics();
        display.getMetrics(displayMetrics);
        //设置控件高度不能超过屏幕高度一半（d.heightPixels / 2，
        // 下面有清空按钮所以再减200，也可随意换成自己想要的高度）
        heightMeasureSpec=MeasureSpec.makeMeasureSpec(displayMetrics.heightPixels/2-200,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
