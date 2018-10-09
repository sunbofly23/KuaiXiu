package judou.ceicheng.com.computerprotect


import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import kotlinx.android.synthetic.main.activity_fabu.*
import android.widget.Toast
import org.greenrobot.eventbus.EventBus
import android.provider.MediaStore
import android.widget.Button
import android.support.v4.content.FileProvider
import android.os.Build
import android.os.Environment
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import judou.ceicheng.com.computerprotect.bean.HomeBean
import java.io.FileNotFoundException


class FabuActivity : AppCompatActivity() {


    var dialog: Dialog? = null
    var imgUri: Uri? = null
    var inflate: View? = null
    var btn1: Button? = null
    var btn2: Button? = null
    var btn3: Button? = null
    val file: File? = null
    var bq:Bitmap?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fabu)
        initEvent()
    }

    private fun initEvent() {
        iv_back1.setOnClickListener {
            finish()
        }
        tv_queding.setOnClickListener {
            var question: String = et_question.text.toString()
            if(bq==null){
                Toast.makeText(this, "大哥，来张图吧", Toast.LENGTH_SHORT).show()
            }
            if (TextUtils.isEmpty(question)) {
                Toast.makeText(this, "你有信息为空,检查后提交", Toast.LENGTH_SHORT).show()
            }
            else {
                var bean = HomeBean(tx_person.text.toString(), et_question.text.toString(), R.drawable.pic3,R.drawable.a1)
                EventBus.getDefault().post(bean)
                finish()
            }
        }

        iv_pic.setOnClickListener {
            if (Build.VERSION.SDK_INT > 23) {
                val str: Array<String> = arrayOf(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
//申请动态权限啊兄弟
                if (checkPermission(this))
                    ActivityCompat.requestPermissions(this, str, 1)
                else
//直接进行逻辑
                    show()
            }
            else{
                show()
            }
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> if (grantResults.size > 0 && grantResults[0] === PackageManager.PERMISSION_GRANTED) {
                //权限被用户同意了，执行后面逻辑
                show()
            } else
                Toast.makeText(this, "用户否定了这个权限", Toast.LENGTH_LONG).show()
        }
    }

    fun show() {
        dialog = Dialog(this, R.style.ActionSheetDialogStyle)
        //填充对话框的布局
        inflate = LayoutInflater.from(this).inflate(R.layout.layout_bottom_up, null)
        //初始化控件
        //将布局设置给Dialog
        btn1 = inflate!!.findViewById(R.id.btn_select_photo)
        btn2 = inflate!!.findViewById(R.id.btn_cancel_dialog)
        btn3 = inflate!!.findViewById(R.id.btn_take_photo)
        dialog!!.setContentView(inflate)
        //获取当前Activity所在的窗体
        var dialogWindow: Window = dialog!!.getWindow()
        if (false) {
            return
        }
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM)
        //获得窗体的属性
        var lp: WindowManager.LayoutParams = dialogWindow.getAttributes()
        val dm = getResources().getDisplayMetrics()
        lp.width = dm.widthPixels
        lp.y = 20//设置Dialog距离底部的距离
        //将属性设置给窗体
        dialogWindow.setAttributes(lp)
        dialog!!.show()//显示对话框
        btn3!!.setOnClickListener {
            savaPhoto()
            openCamera()
        }
        btn1!!.setOnClickListener {
            openPicture()
        }
        btn2!!.setOnClickListener {
            dialog!!.dismiss()
        }
    }


    fun savaPhoto() {
        val appDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
        if (!appDir.exists()) {
            appDir.mkdir()
        }
        val fileName = getPhotoFilename()

        val file = File(appDir, fileName)
        //图片名字用学生id保存，防止别的用户加载本地图片
        if (file.exists())
            file.delete()
        try {
            file.createNewFile()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        if (Build.VERSION.SDK_INT >= 24)
        //安卓7.0以上内容提供者
            imgUri = FileProvider.getUriForFile(this, "judou.ceicheng.com.computerprotect", file)
        else
            imgUri = Uri.fromFile(file)
    }


    protected fun getPhotoFilename(): String {
        val ts = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        return "Photo_$ts.jpg"
    }

    fun openPicture() {
        val intent = Intent(Intent.ACTION_PICK, null)
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
        startActivityForResult(intent, 2)
    }


    fun openCamera() {
        val intent = Intent("android.media.action.IMAGE_CAPTURE")
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri)
        startActivityForResult(intent, 1)
    }


    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 ->
                //相机
                if (resultCode == RESULT_OK) {
                    try {
                         var bp = BitmapFactory.decodeStream(contentResolver.openInputStream(imgUri))
                         bq=rotateBitmap(bp,90F)
                        iv_pic.setImageBitmap(bq)
                    } catch (e: FileNotFoundException) {
                        e.printStackTrace()
                    }

                }
            2 -> if (resultCode == RESULT_OK) {
                if (Build.VERSION.SDK_INT >= 19) {
                    if (null != data) {
                        var path: String = getRealImagePathByUri(this, data.data)
                        bq = BitmapFactory.decodeFile(path)
                        iv_pic.setImageBitmap(bq)
                    }
                } else {
                    var uri: Uri = data!!.getData()
                    var imagePath: String = getRealImagePathByUri(this, uri)
                    bq = BitmapFactory.decodeFile(imagePath)
                    iv_pic.setImageBitmap(bq)
                }
            }
        }
    }

        fun  rotateBitmap(origin: Bitmap, alpha:Float) :Bitmap{
        var  width:Int = origin.getWidth()
            var height:Int = origin.getHeight()
        var  matrix:Matrix =  Matrix()
        matrix.setRotate(alpha)
        // 围绕原地进行旋转
        var  newBM:Bitmap = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false)
        if (newBM.equals(origin)) {
            return newBM
        }
        origin.recycle()
        return newBM
    }



    fun getRealImagePathByUri(context: Context, uri: Uri): String {

        var scheme: String = uri.getScheme()
        var data: String? = null
        if (scheme == null) {
            data = uri.getPath();
        } else if ("file".equals(scheme)) {
            data = uri.getPath();
        } else if ("content".equals(scheme)) {
            var cursor: Cursor = context.getContentResolver().query(uri, arrayOf("_data"), null, null, null)
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    var index: Int = cursor.getColumnIndex("_data")
                    if (index > -1)
                        data = cursor.getString(index);
                }
                cursor.close();
            }
        }
        return data.toString()
    }

    fun checkPermission(activity: Activity): Boolean {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
            return true
        else
            return false
    }


}


