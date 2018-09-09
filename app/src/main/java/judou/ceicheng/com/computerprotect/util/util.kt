package judou.ceicheng.com.computerprotect.util

import android.content.Context
import android.widget.Toast

/**
 * @Class util
 * @Author sunbo
 * @DATE 2018/8/4 19:28
 * @Explanatory
 */
class util {

companion object {
   fun Toastshow(context:Context,msg:String){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
    }
}
}