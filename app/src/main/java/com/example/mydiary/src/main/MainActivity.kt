package com.example.mydiary.src.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.mydiary.R
import com.example.mydiary.config.BaseActivity
import com.example.mydiary.databinding.ActivityMainBinding
import com.example.mydiary.src.main.list.ListFragment
import com.example.mydiary.src.main.statistics.StatisticsFragment
import com.example.mydiary.src.main.write.WriteFragment
import com.google.android.gms.location.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.IOException
import java.lang.IllegalArgumentException
import java.util.*

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding :: inflate) {

    private var mFusedLocationProviderClient: FusedLocationProviderClient? = null // 현재 위치를 가져오기 위한 변수
    lateinit var mLastLocation: Location // 위치 값을 가지고 있는 객체
    internal lateinit var mLocationRequest: LocationRequest // 위치 정보 요청의 매개변수를 저장하는
    private val REQUEST_PERMISSION_LOCATION = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (checkPermissionForLocation(this)) {
            startLocationUpdates()
        }

        mLocationRequest =  LocationRequest.create().apply {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }


        supportFragmentManager.beginTransaction().replace(R.id.main_fl_fragment, ListFragment()).commitAllowingStateLoss()

        binding.mainBnv.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.item_main_list -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_fl_fragment, ListFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.item_main_write -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_fl_fragment, WriteFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.item_main_statistics -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_fl_fragment, StatisticsFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                }
                false
            })
    }
    @SuppressLint("ResourceType")
    fun fragmentChange(fragmentNum : Int){
        if(fragmentNum == 0) {
            supportFragmentManager.beginTransaction().replace(R.id.main_fl_fragment, ListFragment()).commitAllowingStateLoss()
            binding.mainBnv.selectedItemId = R.id.item_main_list
        } else if(fragmentNum == 1) {
            supportFragmentManager.beginTransaction().replace(R.id.main_fl_fragment, WriteFragment()).commitAllowingStateLoss()
            binding.mainBnv.selectedItemId = R.id.item_main_write
        }

    }

    fun getCurrentAddress(latitude : Double, longitude : Double) : String{
        var geocoder = Geocoder(this, Locale.getDefault());

        val addresses: List<Address>

        addresses = try { geocoder.getFromLocation(latitude, longitude, 7)
        } catch (ioException: IOException) {
            //네트워크 문제
            Toast.makeText(this, "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show()
            return "지오코더 서비스 사용불가"
        } catch (illegalArgumentException: IllegalArgumentException) {
            Toast.makeText(this, "잘못된 GPS 좌표", Toast.LENGTH_LONG).show()
            return "잘못된 GPS 좌표"
        }

        if (addresses == null || addresses.size == 0) {
            Toast.makeText(this, "주소 미발견", Toast.LENGTH_LONG).show()
            return "주소 미발견"
        }

        val address = addresses[0]
        showCustomToast(address.getAddressLine(0).toString())
        return address.getAddressLine(0).toString()+"\n";
    }

    private fun startLocationUpdates() {

        //FusedLocationProviderClient의 인스턴스를 생성.
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        // 기기의 위치에 관한 정기 업데이트를 요청하는 메서드 실행
        // 지정한 루퍼 스레드(Looper.myLooper())에서 콜백(mLocationCallback)으로 위치 업데이트를 요청
        mFusedLocationProviderClient!!.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper()!!)
    }

    // 시스템으로 부터 위치 정보를 콜백으로 받음
    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            // 시스템에서 받은 location 정보를 onLocationChanged()에 전달
            locationResult.lastLocation
            onLocationChanged(locationResult.lastLocation)
        }
    }

    // 시스템으로 부터 받은 위치정보를 화면에 갱신해주는 메소드
    fun onLocationChanged(location: Location) {
        mLastLocation = location
        Log.d("Location","위도 : " + mLastLocation.latitude.toString())
        Log.d("Location","경도 : " + mLastLocation.longitude.toString())
        getCurrentAddress(mLastLocation.latitude,mLastLocation.longitude)
        //text2.text = "위도 : " + mLastLocation.latitude // 갱신 된 위도
        //text1.text = "경도 : " + mLastLocation.longitude // 갱신 된 경도

    }


    // 위치 권한이 있는지 확인하는 메서드
    private fun checkPermissionForLocation(context: Context): Boolean {
        // Android 6.0 Marshmallow 이상에서는 위치 권한에 추가 런타임 권한이 필요
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                true
            } else {
                // 권한이 없으므로 권한 요청 알림 보내기
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_PERMISSION_LOCATION)
                false
            }
        } else {
            true
        }
    }

    // 사용자에게 권한 요청 후 결과에 대한 처리 로직
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdates()

            } else {
                Log.d("ttt", "onRequestPermissionsResult() _ 권한 허용 거부")
                Toast.makeText(this, "권한이 없어 해당 기능을 실행할 수 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

}