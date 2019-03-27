package com.iprismtech.tahyba;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;

import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.iprismtech.tahyba.base.BaseAbstractActivity;
import com.iprismtech.tahyba.fragments.HomeFragment;
import com.iprismtech.tahyba.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.tahyba.utilities.Common;
import com.iprismtech.tahyba.utilities.GPSTracker;
import com.iprismtech.tahyba.utilities.LiveLocationGet;
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
import com.iprismtech.tahyba.utilities.SharedPrefsUtils;
import com.iprismtech.tahyba.utilities.Variables;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends BaseAbstractActivity implements RetrofitResponseListener, LocationListener {

    //Location
    Location location;
    private LocationManager locationManager;
    boolean GpsStatus = false, networkStatus = false;
    Criteria criteria;
    //    String Holder;
    String mprovider;

    private LocationRequest locationRequest;
    private GoogleApiClient googleApiClient;
    private Double lat, lng;
    Geocoder gc = null;
    private static final int REQUEST_CHECK_SETTINGS = 11;


    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_main, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        // ApplicationController.getInstance().setContext(context);


//        locationRequest = LocationRequest.create();
//        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//        locationRequest.setInterval(10000);
//        locationRequest.setFastestInterval(10000 / 2);
//
//        googleApiClient = new GoogleApiClient.Builder(this)
//                .addApi(LocationServices.API).build();
//        googleApiClient.connect();
//
//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        // Creating an empty criteria object
//        Criteria criteria = new Criteria();
//        mprovider = locationManager.getBestProvider(criteria, false);
//        turnGPSOnRuntimePermissions();
        //displayLocationSettingsRequest(MainActivity.this);

        //Variables.location_status = 0;
        try {
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.fm_container, new HomeFragment(), "").commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   /* public void CheckGpsStatus() {

        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        try {
            GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            networkStatus = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {

    }

    @Override
    public void onLocationChanged(Location location) {
        try {
            lat = location.getLatitude();
            lng = location.getLongitude();

//        date.setText("location  " + lat + " " + lng);
            Log.d("MAinActivity", "" + lat + " " + lng);
            if (location != null) {
//                String s = Common.getAddressString(getApplicationContext(), location.getLatitude(), location.getLongitude());

                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(this, Locale.getDefault());

                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
//                if (city != null)
//                    edt_landmark.setText(city);
//                if (postalCode != null)
//                    edt_pin.setText(postalCode);
//                if (address != null)
//                    edt_house.setText(address);
//                if (state != null)
//                    edt_local.setText(state);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void turnGPSOnRuntimePermissions() {
        // run time permissions for gps
        int permissioncheck = ContextCompat.checkSelfPermission(getBaseContext(), android.Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissioncheck != PackageManager.PERMISSION_GRANTED) {
            requestLocationPermission();
            Log.i("  RUN TIME PERMISSIONS", "");
        } else {
            Log.i("  RUN TIME PERMISSIONS", "");
            displayLocationSettingsRequest(MainActivity.this);
        }

    }

    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
    }

    private void displayLocationSettingsRequest(final Context context) {
//        Log.i("  LOCATION SETTINGS", "" );
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        Log.i("AddAddressActivity", "All location settings are satisfied.");

                        if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions

                            return;
                        }
                        location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, MainActivity.this);
                        onLocationChanged(location);
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        Log.i("AddAddressActivity", "Location settings are not satisfied. Show the user a dialog to upgrade location settings ");

                        try {
                            // Show the dialog by calling startResolutionForResult(), and check the result
                            // in onActivityResult().
                            status.startResolutionForResult(MainActivity.this, REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            Log.i("AddAddressActivity", "PendingIntent unable to execute request.");
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Log.i("AddAddressActivity", "Location settings are inadequate, and cannot be fixed here. Dialog not created.");
                        break;
                }
            }
        });
    }

    @Override
    public void onActivityResult(final int requestCode, int resultCode, Intent data) {
        // final LocationSettingsStates states = LocationSettingsStates.fromIntent(data);
        Log.d("onActivityResult", "OnresultAxtivity1");

//        if (requestCode == 1) {
//            if (resultCode == Activity.RESULT_OK) {
//                lat = data.getDoubleExtra("lat", 0.0);
//                lng = data.getDoubleExtra("lng", 0.0);
//                String addr = data.getStringExtra("addr");
//                SharedPrefsUtils.getInstance(MainActivity.this).storelatlangcode(String.valueOf(lat), String.valueOf(lng));
//                //tv_loc_city.setText(addr);
//                Variables.location_status = 1;
//                Variables.cust_lat = String.valueOf(lat);
//                Variables.cust_lng = String.valueOf(lng);
//                Variables.cust_lat = addr;
//                // Toast.makeText(getActivity(), building_id + "and" + name, Toast.LENGTH_SHORT).show();
//            }


        switch (requestCode) {
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions

                            return;
                        }
                        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, MainActivity.this);
//                        onLocationChanged(location);
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
                    case 1:
                        if (resultCode == Activity.RESULT_OK) {
                            lat = data.getDoubleExtra("lat", 0.0);
                            lng = data.getDoubleExtra("lng", 0.0);
                            String addr = data.getStringExtra("addr");
                            SharedPrefsUtils.getInstance(MainActivity.this).storelatlangcode(String.valueOf(lat), String.valueOf(lng));
                            //tv_loc_city.setText(addr);
                            Variables.location_status = 1;
                            Variables.cust_lat = String.valueOf(lat);
                            Variables.cust_lng = String.valueOf(lng);
                            Variables.cust_lat = addr;
                            // Toast.makeText(getActivity(), building_id + "and" + name, Toast.LENGTH_SHORT).show();
                        }

                        break;

                    default:
                        break;
                }
                break;


        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 1) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Camera permission has been granted, preview can be displayed
                /*Toast.makeText(this, "permision_available_location",
                        LENGTH_SHORT).show();*/
            } else {
                Toast.makeText(this, "permissions_not_granted",
                        LENGTH_SHORT).show();
            }
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        // set dialog message
        alertDialogBuilder
                .setTitle("Confirm Exit?")
                .setMessage("Are you sure want to Exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finishAffinity();
                        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                        homeIntent.addCategory(Intent.CATEGORY_HOME);
                        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(homeIntent);
                        System.exit(0);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.dismiss();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
