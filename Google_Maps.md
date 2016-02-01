# Google Maps #

怎么使用Google Maps:
  1. 使用SDK Manager（以管理员身份运行）下载对应的带有Google APIs的SDK。
> > Third party Add-ons - Google Inc - Google APIs by Google Inc...
  1. 新建一个Virtual Device对应那个SDK。
  1. 取得一个Google Maps API key。
> > 访问https://code.google.com/android/add-ons/google-apis/index.html
> > 选Maps API Key Signup
> > 使用debug certification 产生一个Certificate fingerprint。
```
C:\Java\jre6\bin>keytool.exe -list -alias androiddebugkey -keystore C:\Users\qua
nli\.android\debug.keystore -storepass android -keypass android
```
  1. 在main.xml里加一个MapView，加入产生的Google Maps API key。
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    >
	<com.google.android.maps.MapView
	    android:layout_width="fill_parent"
	    android:layout_height="fill_parent"
	    android:id="@+id/themap"
	    android:clickable="true"
	    android:apiKey="00lwICTOKl4fzQTRsMMPVXNkFR82S5KDiPD7C5g"
    	/>
    	<!-- 
    		Labtop apiKey: 00lwICTOKl4fzQTRsMMPVXNkFR82S5KDiPD7C5g
    		Desktop apiKey: 00lwICTOKl4e0PhiDpOlTyy4d1a14ve2jksHeHw
    	 -->
</LinearLayout>
使用android:apiKey="00lwICTOKl4fzQTRsMMPVXNkFR82S5KDiPD7C5g"， 指定apiKey。
```
  1. 在AndroidManifest.xml里加入inernet权限。为了访问GPS加入GPS的权限
  1. 在AndroidManifest.xml里加入对google maps API的引用。
```
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
      package="com.quan.maps"
      android:versionCode="1"
      android:versionName="1.0">
    <uses-sdk android:minSdkVersion="10" />
    <uses-permission android:name="android.permission.INTERNET"></uses-permission>
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"></uses-permission>

    <application android:icon="@drawable/icon" android:label="@string/app_name">
        <uses-library android:name="com.google.android.maps"/>
        <activity android:name=".Main"
                  android:label="@string/app_name">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

    </application>
</manifest>
使用<uses-library android:name="com.google.android.maps"/> 加入对google maps api的引用。
使用<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"></uses-permission> 加入访问GPS的权限。
```
  1. 在activity里加入代码。
```
package com.quan.maps;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class Main extends MapActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        MapView mapView = (MapView) findViewById(R.id.themap);
        mapView.setBuiltInZoomControls(true);
        
        final MapController mapController = mapView.getController();
        
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		LocationListener locationListener = new LocationListener() {
			
			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onLocationChanged(Location location) {
				double geoLatitude = location.getLatitude()*1E6;
		        double geoLongitude = location.getLongitude()*1E6;
				mapController.setCenter(new GeoPoint((int) geoLatitude, (int) geoLongitude));
			}
		};
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}

使用public class Main extends MapActivity 继承MapActivity。
使用mapView.setBuiltInZoomControls(true); 加入zoom control。
使用LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE); 得到locationmanager
使用LocationListener locationListener = new LocationListener() new一个locationlistener
使用public void onLocationChanged(Location location) {
				double geoLatitude = location.getLatitude()*1E6;
		        double geoLongitude = location.getLongitude()*1E6;
				mapController.setCenter(new GeoPoint((int) geoLatitude, (int) geoLongitude));
			} 把设置地图位置注册在位置改变事件上。E6 是微度（microdegrees），就是度数再乘以1000000。GeoPoint(int latitudeE6, int longitudeE6) 需要的参数是微度为单位的。
			
使用locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener); 把位置改变的listener注册在  provider上。
```
  1. 源代码： https://ggggg.googlecode.com/svn/trunk/AndroidbyLynda/Maps/