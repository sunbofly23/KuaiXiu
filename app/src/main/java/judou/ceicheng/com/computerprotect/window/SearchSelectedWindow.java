package judou.ceicheng.com.computerprotect.window;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.LineNumberInputStream;
import java.util.List;
import java.util.zip.Inflater;

import judou.ceicheng.com.computerprotect.R;
import judou.ceicheng.com.computerprotect.adapter.SearchSeleledAdapter;
import judou.ceicheng.com.computerprotect.bean.SearchSelectBean;
import judou.ceicheng.com.computerprotect.customView.CustomHeightListView;

/**
 * @Class SearchSelectedWindow
 * @Author sunbo
 * @DATE 2018/8/15 20:07
 * @Explanatory
 */
public class SearchSelectedWindow extends PopupWindow {
private   Activity activity;
private   List<SearchSelectBean> list;
private CustomHeightListView listView;
private TextView tv_reset,tv_confirm;
private View nullView;
private SearchSeleledAdapter adapter;
private  OnConfirmClickListener listener;


public SearchSelectedWindow(Activity activity ,List<SearchSelectBean> list){
    this.activity=activity;
    this.list=list;
    init();
}

    private void init() {
    //加载布局并且设置
    View view= View.inflate(activity, R.layout.window_search_selected,null);
    this.setContentView(view);
    this.setWidth(-1);
    this.setHeight(-2);
    this.setFocusable(true);
    this.setOutsideTouchable(true);
    this.setBackgroundDrawable(new ColorDrawable(0x33000000));

    //绑定布局控件
        listView=(CustomHeightListView) view.findViewById(R.id.listview);
        tv_reset=view.findViewById(R.id.tv_reset);
        tv_confirm=view.findViewById(R.id.tv_confirm);
        nullView=view.findViewById(R.id.view_null);
    //listview绑定 adapter
        adapter=new SearchSeleledAdapter(activity,list);
        listView.setAdapter(adapter);

        tv_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int x = 0; x < list.size(); x++) {
                    List<SearchSelectBean.Children> childrenBeen = list.get(x).getChildren();
                    for (int y=0;y<childrenBeen.size();y++){
                        if (childrenBeen.get(y).getIsSelected())
                            childrenBeen.get(y).setIsSelected(false);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });

        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onConfirmClick();
                dismiss();
            }
        });

        nullView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }

    //自定义监听接口第一步
    public interface OnConfirmClickListener{
        void onConfirmClick();
    }
    //自定义监听第二步
    public void setOnConfirmClickListener(OnConfirmClickListener onConfirmClickListener){
        this.listener=onConfirmClickListener;
    }

}
