package judou.ceicheng.com.computerprotect.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import judou.ceicheng.com.computerprotect.R;

/**
 * Created by sunbo on 2018/9/24.
 */

@SuppressLint("ValidFragment")
public class AlipayDialogFragment extends DialogFragment {
    private static volatile AlipayDialogFragment dialog = null;
    private Button btn_in;
    private EditText et_text;
    private TextView tvCode1,tvCode2,tvCode3,tvCode4,tvCode5;
    private Context context;

    @SuppressLint("ValidFragment")
    private AlipayDialogFragment(Context context) {
        this.context= context;
    }


    public static AlipayDialogFragment getInstance(Context context){
        return new AlipayDialogFragment(context);
    }

    /**
     * 使用   onCreateView 创建   对话框的样式  使用自定义视图
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_alipay, container,false);
        initView(view);
        initEvent();
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            WindowManager.LayoutParams lp=dialog.getWindow().getAttributes();
            DisplayMetrics dm=getActivity().getResources().getDisplayMetrics();
            lp.width=dm.widthPixels;
            //lp.height=
            dialog.getWindow().setAttributes(lp);
            dialog.getWindow().setGravity(Gravity.CENTER);
        }
    }

    private void initEvent() {

       btn_in.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                    if(et_text.getText().toString().equals("12345")){
                        Toast.makeText(context,"支付成功", Toast.LENGTH_SHORT).show();
                        dismiss();
                        ((Activity)context).finish();
                    }
                    else{
                        Toast.makeText(context,"支付密码错误", Toast.LENGTH_SHORT).show();
                    }
           }
       });

        TextWatcher edtCodeChange=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tvCode1.setText("");
                tvCode2.setText("");
                tvCode3.setText("");
                tvCode4.setText("");
                tvCode5.setText("");

                switch (s.length()) {
                    case 5:
                        tvCode5.setText(s.subSequence(4, 5));
                    case 4:
                        tvCode4.setText(s.subSequence(3, 4));
                    case 3:
                        tvCode3.setText(s.subSequence(2, 3));
                    case 2:
                        tvCode2.setText(s.subSequence(1, 2));
                    case 1:
                        tvCode1.setText(s.subSequence(0, 1));
                    default:
                        break;
                }
            }
        };

        et_text.addTextChangedListener(edtCodeChange);
    }

    private void initView(View view) {
        btn_in=(Button)view.findViewById(R.id.btn_in);
        et_text=(EditText) view.findViewById(R.id.et_text);
        tvCode1=(TextView) view.findViewById(R.id.tvCode1);
        tvCode2=(TextView) view.findViewById(R.id.tvCode2);
        tvCode3=(TextView) view.findViewById(R.id.tvCode3);
        tvCode4=(TextView) view.findViewById(R.id.tvCode4);
        tvCode5=(TextView) view.findViewById(R.id.tvCode5);
    }

}
