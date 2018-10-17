package judou.ceicheng.com.computerprotect.fragment


import android.app.Fragment
import android.content.Context
import android.graphics.Color
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amap.api.maps.AMap
import com.amap.api.maps.MapView

import judou.ceicheng.com.computerprotect.R
import com.amap.api.maps.UiSettings
import com.amap.api.maps.CameraUpdateFactory
import com.amap.api.maps.model.MyLocationStyle
import android.content.Intent
import android.widget.Toast
import android.location.LocationManager
import android.content.Context.LOCATION_SERVICE



/**
 * A simple [Fragment] subclass.
 */
class BlankFragment : Fragment() {

    var mMapView: MapView? = null
    private var mapLayout: View? = null
    private var aMap: AMap? = null
    private var lm: LocationManager? = null//【位置管理】
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
            if (mapLayout == null) {
                mapLayout = inflater.inflate(R.layout.fragment_blank, null)
                mMapView = mapLayout!!.findViewById(R.id.map)
                mMapView!!.onCreate(savedInstanceState)
                initMap()
//                if (aMap == null) {
//                    aMap = mMapView!!.getMap()
//                }
            }
            return mapLayout
    }

    fun initMap() {
        if (aMap == null) {
            aMap = mMapView!!.getMap()
        }

        lm = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
        val ok = lm!!.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (ok) {
            val myLocationStyle: MyLocationStyle
            myLocationStyle = MyLocationStyle()//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
            myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0))// 设置圆形的边框颜色
            myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0))// 设置圆形的填充颜色
            myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE)//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
            myLocationStyle.interval(2000) //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
            aMap!!.setMyLocationStyle(myLocationStyle)//设置定位蓝点的Style
            aMap!!.moveCamera(CameraUpdateFactory.zoomTo(18F))
            val mUiSettings: UiSettings//定义一个UiSettings对象
            mUiSettings = aMap!!.getUiSettings()//实例化UiSettings类对象
            mUiSettings.isCompassEnabled = true//开启指南针
            mUiSettings.isScaleControlsEnabled = true//开启比例尺
        } else {
            Toast.makeText(activity, "系统检测到未开启GPS定位服务", Toast.LENGTH_LONG).show()
            val intent = Intent()
            intent.action = android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS
            startActivity(intent)
        }

    }



    override fun onResume() {
        super.onResume()
        mMapView!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMapView!!.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMapView!!.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        mMapView!!.onSaveInstanceState(outState)
    }

}
