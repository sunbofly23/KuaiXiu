package judou.ceicheng.com.computerprotect.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import judou.ceicheng.com.computerprotect.R;
import judou.ceicheng.com.computerprotect.adapter.HomeAdapter;

/**
 * Created by sunbo on 2018/9/9.
 */

@SuppressLint("ValidFragment")
public class HomeSharedialog extends DialogFragment {
    private static volatile HomeSharedialog dialog = null;
    private Button btn_cancel,btn_commit;
    private EditText et_content;
    public InterfaceExample mc;

    //私有化构造函数：
    @SuppressLint("ValidFragment")
    private HomeSharedialog(InterfaceExample mc){
        this.mc=mc;
    }

    /**
     * 单例模式：创建  Fragment：
     * @return
     */
    public static HomeSharedialog getInstance(InterfaceExample mc){
        return new HomeSharedialog(mc);
    }

    /**
     * 使用   onCreateView 创建   对话框的样式  使用自定义视图
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_home_comment, container,false);
        initView(view);
        initEvent();
        return view;

    }

    @Override

    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout((int) (dm.widthPixels * 1),(int) (dm.heightPixels * 0.2));
        }
    }

    private void initEvent() {
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a =et_content.getText().toString();
                if(a!=null) {
                    mc.sendMessage(a);
                    dismiss();
                }
                else
                    Toast.makeText(getActivity(), "你没有输入评论", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initView(View view) {
        btn_cancel=(Button)view.findViewById(R.id.btn_dialog_cancel);
        btn_commit=(Button)view.findViewById(R.id.btn_dialog_commit);
        et_content=(EditText)view.findViewById(R.id.et_dialog_comment);
    }

    public  interface InterfaceExample {
        void sendMessage(String string);
    }
}
