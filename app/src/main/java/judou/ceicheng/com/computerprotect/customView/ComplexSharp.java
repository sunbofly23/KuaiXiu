package judou.ceicheng.com.computerprotect.customView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sunbo on 2018/10/17.
 */

public class ComplexSharp extends View
{
    private static int dline = 56;
    private static int radius = 6;
    private Context context;

    public ComplexSharp(Context paramContext)
    {
        super(paramContext);
    }

    public ComplexSharp(Context paramContext, @Nullable AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
    }

    public int dip2px(float paramFloat)
    {
        return (int)(paramFloat * this.context.getResources().getDisplayMetrics().density + 0.5F);
    }

    @SuppressLint({"ResourceType"})
    @RequiresApi(api=21)
    protected void onDraw(Canvas paramCanvas)
    {
        super.onDraw(paramCanvas);
        this.context = getContext();
        @SuppressLint("DrawAllocation")
        Paint localPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        //画笔填充
        localPaint.setStyle(Paint.Style.FILL);
        localPaint.setAntiAlias(true);
        localPaint.setColor(Color.parseColor("#99999999"));
        Path localPath = new Path();
        int i = getWidth();
        int j = getHeight();
        localPath.moveTo(dip2px(radius), 0.0F);
        localPath.lineTo(i - dip2px(dline + radius), 0.0F);
        localPath.arcTo(i - dip2px(dline + radius), -dip2px(radius), i - dip2px(dline - radius), dip2px(radius), -180.0F, -180.0F, false);
        localPath.lineTo(i - dip2px(radius), 0.0F);
        localPath.arcTo(i - dip2px(radius), 0.0F, i, dip2px(radius + radius), -90.0F, 90.0F, false);
        localPath.lineTo(i, j - dip2px(radius));
        localPath.arcTo(i - dip2px(radius + radius), j - dip2px(radius + radius), i, j, 0.0F, 90.0F, false);
        localPath.lineTo(i - dip2px(dline - radius), j);
        localPath.arcTo(i - dip2px(dline + radius), j - dip2px(radius), i - dip2px(dline - radius), dip2px(radius) + j, 0.0F, -180.0F, false);
        localPath.lineTo(dip2px(radius), j);
        localPath.arcTo(0.0F, j - dip2px(radius), dip2px(radius + radius), j, 90.0F, 90.0F, false);
        localPath.lineTo(0.0F, dip2px(radius + radius));
        localPath.arcTo(0.0F, 0.0F, dip2px(radius + radius), dip2px(radius + radius), 180.0F, 90.0F, false);
        paramCanvas.drawPath(localPath, localPaint);
    }
}
