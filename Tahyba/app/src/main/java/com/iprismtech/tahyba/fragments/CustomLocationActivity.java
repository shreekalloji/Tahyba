package com.iprismtech.tahyba.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.iprismtech.tahyba.R;
import com.iprismtech.tahyba.factories.Constants.AppConstants;
import com.iprismtech.tahyba.factories.controllers.ApplicationController;
import com.iprismtech.tahyba.utilities.Common;

public class CustomLocationActivity extends AppCompatActivity implements PlaceSelectionListener {

    private EditText et_location_search;
    int AUTO_COMP_REQ_CODE = 1;
    private double lati, longi;
    private LinearLayout ll_get_current_loc;
    private ImageView iv_back_arrow;
    private RecyclerView locationRecycleView;
    private LinearLayoutManager llm;
    private LatLng latLng;


    private GoogleApiClient currentLocationmGoogleApiClient;
    private static final LatLngBounds BOUNDS_INDIA = new LatLngBounds(
            new LatLng(-0, 0), new LatLng(0, 0));

    private static final LatLngBounds BOUNDS_MOUNTAIN_VIEW = new LatLngBounds(
            new LatLng(37.398160, -122.180831), new LatLng(37.430610, -121.972090));
    private static final int REQUEST_SELECT_PLACE_DEST = 1000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_loaction_layout);
        et_location_search = findViewById(R.id.et_location_search);
        ll_get_current_loc = findViewById(R.id.ll_get_current_loc);
        iv_back_arrow = findViewById(R.id.iv_back_arrow);

        et_location_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    AutocompleteFilter autocompleteFilter = new AutocompleteFilter.Builder()
                            .setTypeFilter(Place.TYPE_COUNTRY)
                            .setCountry("IN")
                            .build();
                    Intent intent = new PlaceAutocomplete.IntentBuilder
                            (PlaceAutocomplete.MODE_OVERLAY)
                            .setBoundsBias(BOUNDS_MOUNTAIN_VIEW)
                            .setFilter(autocompleteFilter)
                            .build(CustomLocationActivity.this);
                    startActivityForResult(intent, REQUEST_SELECT_PLACE_DEST);

                } catch (GooglePlayServicesRepairableException |
                        GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

            }
        });
        ll_get_current_loc.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
                finish();
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SELECT_PLACE_DEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(CustomLocationActivity.this, data);
                this.onPlaceSelected(place);
                et_location_search.setText(place.getAddress());
                latLng = place.getLatLng();
                lati = latLng.latitude;
                longi = latLng.longitude;

                String ddress = Common.getAddressString(CustomLocationActivity.this, lati, longi);

                Intent returnIntent = new Intent();
                returnIntent.putExtra("lat", lati);
                returnIntent.putExtra("lng", longi);
                returnIntent.putExtra("addr", ddress);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();


            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(CustomLocationActivity
                        .this, data);
                this.onError(status);
            }

            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onPlaceSelected(Place place) {

    }

    @Override
    public void onError(Status status) {

    }
}
