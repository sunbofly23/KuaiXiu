package judou.ceicheng.com.computerprotect.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import judou.ceicheng.com.computerprotect.R;
import judou.ceicheng.com.computerprotect.bean.SearchSelectBean;
import judou.ceicheng.com.computerprotect.customLayout.SearchSelectedLayout;

/**
 * @Class SearchSeleledAdapter
 * @Author sunbo
 * @DATE 2018/8/15 20:23
 * @Explanatory
 */
public class SearchSeleledAdapter extends BaseAdapter {
    private Context context;
    private List<SearchSelectBean>list;

    public SearchSeleledAdapter(Context context, List<SearchSelectBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder vh;
        if (view == null) {
            view = View.inflate(context, R.layout.item_listview_property, null);
            vh = new ViewHolder();
            vh.tvTypeName = (TextView) view.findViewById(R.id.tv_type_name);
            vh.layoutProperty = (SearchSelectedLayout) view.findViewById(R.id.layout_property);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }

        SearchSelectBean bean = list.get(i);
        vh.tvTypeName.setText(bean.getTypeName());
        setFlowLayoutData(bean.getChildren(), vh.layoutProperty);
        return view;
    }

    private void setFlowLayoutData(final List<SearchSelectBean.Children> children, final SearchSelectedLayout layoutProperty) {
       layoutProperty.removeAllViews();
        for (int x = 0; x < children.size(); x++) {
            CheckBox checkBox = (CheckBox) View.inflate(context, R.layout.item_flowlayout_bill, null);
            checkBox.setText(children.get(x).getValue());

            if (children.get(x).getIsSelected()) {
                checkBox.setChecked(true);
                children.get(x).setIsSelected(true);
            } else {
                checkBox.setChecked(false);
                children.get(x).setIsSelected(false);
            }

            final int finalX = x;
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    refreshCheckBox(layoutProperty, finalX, children);
//                    Toast.makeText(context,childrenList.get(finalX).getValue(),Toast.LENGTH_LONG).show();
                }
            });
            layoutProperty.addView(checkBox);
        }


    }
    private void refreshCheckBox(SearchSelectedLayout flowLayout, int finalX, List<SearchSelectBean.Children> propBeenList) {
        for (int y = 0; y < flowLayout.getChildCount(); y++) {
            CheckBox radio = (CheckBox) flowLayout.getChildAt(y);
            radio.setChecked(false);
            propBeenList.get(y).setIsSelected(false);
            if (finalX == y) {
                radio.setChecked(true);
                propBeenList.get(y).setIsSelected(true);
            }
        }
//        context.setPropertyPrice();
    }

    class ViewHolder {
        private TextView tvTypeName;
        private SearchSelectedLayout layoutProperty;
    }

}
