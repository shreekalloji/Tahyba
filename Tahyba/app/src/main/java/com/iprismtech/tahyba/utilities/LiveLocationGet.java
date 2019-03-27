package com.iprismtech.tahyba.utilities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.firebase.database.ServerValue;

public class LiveLocationGet extends AppCompatActivity implements LocationListener {
    private LocationRequest locationRequest;
    private GoogleApiClient googleApiClient;
    public Double lat, lng;
    public String address;
    double lat_ofc = 17.449123;
    double lon_ofc = 78.363443;
    LocationManager locationManager;
    String provider;
    Geocoder gc = null;
    private Context mContext;
    private static final int REQUEST_CHECK_SETTINGS = 11;

    public LiveLocationGet(Activity context) {
        this.mContext = context;
        getLocation();
    }

    public void getLocation() {
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(10000 / 2);

        googleApiClient = new GoogleApiClient.Builder(mContext)
                .addApi(LocationServices.API).build();
        googleApiClient.connect();

        locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        // Creating an empty criteria object
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, true);

        //  turnGPSOnRuntimePermissions();
        displayLocationSettingsRequest(mContext);


    }


//    private void turnGPSOnRuntimePermissions() {
//        // run time permissions for gps
//        int permissioncheck = ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION);
//        if (permissioncheck != PackageManager.PERMISSION_GRANTED) {
//            requestLocationPermission();
//            Log.i("  RUN TIME PERMISSIONS", "" + ServerValue.TIMESTAMP);
//        } else {
//            Log.i("  RUN TIME PERMISSIONS", "" + ServerValue.TIMESTAMP);
//            displayLocationSettingsRequest(mContext);
//        }
//
//    }

//    private void requestLocationPermission() {
//        if (ActivityCompat.shouldShowRequestPermissionRationale(, Manifest.permission.ACCESS_FINE_LOCATION)) {
//            ActivityCompat.requestPermissions(mContext,
//                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
//        } else {
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//        if (requestCode == 1) {
//            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Camera permission has been granted, preview can be displayed
//                Toast.makeText(mContext, "Permissions granted",LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(mContext, "Permissions not granted",
//                        LENGTH_SHORT).show();
//            }
//            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        }
//    }


    public void displayLocationSettingsRequest(final Context context) {
        Log.i("  LOCATION SETTINGS", "" + ServerValue.TIMESTAMP);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        Log.i("MainActivity", "All location settings are satisfied.");

                        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions

                            return;
                        }
                        Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, LiveLocationGet.this);
                        lat = location.getLatitude();
                        lng = location.getLongitude();
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        Log.i("MainActivity", "Location settings are not satisfied. Show the user a dialog to upgrade location settings ");

                        try {
                            // Show the dialog by calling startResolutionForResult(), and check the result
                            // in onActivityResult().
                            status.startResolutionForResult(LiveLocationGet.this, REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            Log.i("MainActivity", "PendingIntent unable to execute request.");
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Log.i("MainActivity", "Location settings are inadequate, and cannot be fixed here. Dialog not created.");
                        break;
                }
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i("  ONLOCATIONCHANGED", "" + ServerValue.TIMESTAMP);
        lat = location.getLatitude();
        lng = location.getLongitude();

//        date.setText("location  " + lat + " " + lng);
        Log.d("MAinActivity", "" + lat + " " + lng);
        if (location != null) {
            //  String s = Common.getAddressString(mContext, location.getLatitude(), location.getLongitude());

            /*double earthRadius = 3958.75;
            double dLat = Math.toRadians(lat-lat_ofc);
            double dLng = Math.toRadians(lng-lon_ofc);
            double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                    Math.cos(Math.toRadians(lat)) * Math.cos(Math.toRadians(lat_ofc)) *
                            Math.sin(dLng/2) * Math.sin(dLng/2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
            double dist = earthRadius * c;*/

            Location startPoint = new Location("locationA");
            startPoint.setLatitude(lat_ofc);
            startPoint.setLongitude(lon_ofc);

            Location endPoint = new Location("locationA");
            endPoint.setLatitude(location.getLatitude());
            endPoint.setLongitude(location.getLongitude());

//            distance = startPoint.distanceTo(endPoint);
//            date.setText("" + s + "" + distance + " in Meters");
//            Log.d("Distance ", "" + distance);


        }
    }

    public void getDistance(double distance) {

        Log.i("", "" + distance);

        return;
    }

    @Override
    public void onActivityResult(final int requestCode, int resultCode, Intent data) {
        // final LocationSettingsStates states = LocationSettingsStates.fromIntent(data);
        Log.d("onActivityResult", "OnresultAxtivity1");
        switch (requestCode) {
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions

                            return;
                        }
                        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, LiveLocationGet.this);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        Log.d("onActivityResult", "OnresultAxtivity4");
                        break;
                    case Activity.RESULT_CANCELED:
                        // The user was asked to change settings, but chose not to
                        // finish();

                        break;

                    default:
                        break;
                }
                break;


        }
    }

}
