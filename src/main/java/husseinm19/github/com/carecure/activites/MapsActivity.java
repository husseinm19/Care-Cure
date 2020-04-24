package husseinm19.github.com.carecure.activites;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationListener;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import husseinm19.github.com.carecure.R;
import husseinm19.github.com.carecure.logic.GetNearbyPlacesData;
//import husseinm19.github.com.carecure.logic.GetNearbyPlaces;
//import husseinm19.github.com.carecure.logic.GetNearbyPlacesData;

/**
 * Created by Hussein on 18-04-2020
 */

public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {




    private static final int Request_User_location_Code = 99;
    private final float DEFAULT_ZOOM = 15;
    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Location lastLocation;
    private Marker currentUserLocationMarker;

    private double latitude, longtitude;
    int PROXIMITY_RADIUS = 10000;

    private int ProximityRadius = 15000;

    // public Intent i = getIntent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkUserLocationPermission();


        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    //    public void onClick() {
//        String hospital = "hospitals";
//        String pharmacy = "pharmacy";
//        String clinic = "clinic";
//        String Veterinary = "hospital";
//
//        Object transaferData[] = new Object[2];
//        GetNearbyPlaces getNearbyPlaces = new GetNearbyPlaces();
//
//
//         //Get intent data
//        Intent i = getIntent();
//        // Get Selected Card Id
//         int position = i.getExtras().getInt("id");
////
////
//        if (position == 1)//Hospital
//        {
//            mMap.clear();
//            String url = getURL( latitude,  longtitude,  hospital);
//            //Toast.makeText(this, "Hospitals", Toast.LENGTH_SHORT).show();
//            transaferData[0] = mMap;
//            transaferData[1] = url;
//
//            getNearbyPlaces.execute(transaferData);
//
//
//        }
//
//        if (position == 2)//pharmacy
//        {
//            String url = getURL( latitude,  longtitude,  pharmacy);
//            mMap.clear();
//            Toast.makeText(this, "pharmacy", Toast.LENGTH_SHORT).show();
//            transaferData[0] = mMap;
//            transaferData[1] = url;
//
//            getNearbyPlaces.execute(transaferData);
//
//        }
//
//        if (position == 3)//clinick
//        {
//            String url = getURL( latitude,  longtitude,  clinic);
//            mMap.clear();
//            Toast.makeText(this, "clinic", Toast.LENGTH_SHORT).show();
//            transaferData[0] = mMap;
//            transaferData[1] = url;
//
//            getNearbyPlaces.execute(transaferData);
//
//        }
//
//        if (position == 4)//Veterinary
//        {
//            String url = getURL( latitude,  longtitude,  Veterinary);
//            mMap.clear();
//            Toast.makeText(this, "Veterinary", Toast.LENGTH_SHORT).show();
//            transaferData[0] = mMap;
//            transaferData[1] = url;
//
//            getNearbyPlaces.execute(transaferData);
//
//        }
//
//    }
    public void onClick(View v) {
        Object dataTransfer[] = new Object[2];
        GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();

        switch(v.getId())
        {

            case R.id.B_hopistals:
                mMap.clear();
                String hospital = "hospital";
                String url = getUrl(latitude, longtitude, hospital);
                dataTransfer[0] = mMap;
                dataTransfer[1] = url;

                getNearbyPlacesData.execute(dataTransfer);
                Toast.makeText(MapsActivity.this, "Showing Nearby Hospitals", Toast.LENGTH_SHORT).show();
                break;

            case R.id.clinics:
                mMap.clear();
                String clinics = "clinics";
                url = getUrl(latitude, longtitude, clinics);
                dataTransfer[0] = mMap;
                dataTransfer[1] = url;

                getNearbyPlacesData.execute(dataTransfer);
                Toast.makeText(MapsActivity.this, "Showing Nearby clinics", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pharmacy:
                mMap.clear();
                String Pharmacy = "pharmacy";
                url = getUrl(latitude, longtitude, Pharmacy);
                dataTransfer[0] = mMap;
                dataTransfer[1] = url;

                getNearbyPlacesData.execute(dataTransfer);
                Toast.makeText(MapsActivity.this, "Showing Nearby Pharmacy", Toast.LENGTH_SHORT).show();
                break;

            case R.id.vet:
                mMap.clear();
                String vet = "Veterinary";
                url = getUrl(latitude, longtitude, vet);
                dataTransfer[0] = mMap;
                dataTransfer[1] = url;

                getNearbyPlacesData.execute(dataTransfer);
                Toast.makeText(MapsActivity.this, "Showing Nearby Veterinary", Toast.LENGTH_SHORT).show();
                break;
        }


    }
    private String getUrl(double latitude , double longitude , String nearbyPlace)
    {

        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlaceUrl.append("location="+latitude+","+longitude);
        googlePlaceUrl.append("&radius="+PROXIMITY_RADIUS);
        googlePlaceUrl.append("&type="+nearbyPlace);
        googlePlaceUrl.append("&sensor=true");
        googlePlaceUrl.append("&key="+"AIzaSyBLEPBRfw7sMb73Mr88L91Jqh3tuE4mKsE");

        Log.d("MapsActivity", "url = "+googlePlaceUrl.toString());

        return googlePlaceUrl.toString();
    }

//    private String getURL(double latitude, double longtitude, String nearbyPlace)
//    {
//        StringBuilder googleURl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
//
//        googleURl.append("location="+ latitude + "," + longtitude);
//        googleURl.append("&radius=" + ProximityRadius);
//        googleURl.append("&type=" + nearbyPlace);
//        googleURl.append("&sensor=true");
//        googleURl.append("&key=" + "AIzaSyCrrRjiiDLl5h_OfI8Sv-tszueGuHzLaeY");
//
//        Log.d("MapsActivity","url = " +googleURl.toString());
//
//        return googleURl.toString();
//    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


//         Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
        }

        //check if gps is enabled or not and then request user to enable it
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);

        SettingsClient settingsClient = LocationServices.getSettingsClient(MapsActivity.this);
        Task<LocationSettingsResponse> task = settingsClient.checkLocationSettings(builder.build());

        task.addOnSuccessListener(MapsActivity.this, new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                //hna method eli m3rfhash
                //tl3 msh lazm a call 3shan hwa by3mlha lwa7do :D
            }
        });

        task.addOnFailureListener(MapsActivity.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof ResolvableApiException) {
                    ResolvableApiException resolvable = (ResolvableApiException) e;
                    try {
                        resolvable.startResolutionForResult(MapsActivity.this, 51);
                    } catch (IntentSender.SendIntentException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                return false;
            }
        });

    }


    public boolean checkUserLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_location_Code);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_location_Code);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Request_User_location_Code:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        if (googleApiClient != null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);



                    }
                } else {
                    Toast.makeText(this, "Permission Denied....", Toast.LENGTH_SHORT).show();
                }
                return;

        }
    }

    protected synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();


    }

    @Override
    public void onLocationChanged(Location location) {

        latitude = location.getLatitude();
        longtitude = location.getLongitude();
        lastLocation = location;

        if (currentUserLocationMarker != null) {
            currentUserLocationMarker.remove();

        }

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        String title = String.valueOf(R.string.currentUserLocation);
        markerOptions.title("User Current Location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

        currentUserLocationMarker = mMap.addMarker(markerOptions);
        //  mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude()), DEFAULT_ZOOM));
        // mMap.moveCamera(CameraUpdateFactory.zoomTo(13));

        if (googleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
        }


//        Object dataTransfer[] = new Object[2];
//        GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
//
//            mMap.clear();
//            String hospital = "hospital";
//            String url = getUrl(latitude, longtitude, hospital);
//            dataTransfer[0] = mMap;
//            dataTransfer[1] = url;
//
//            getNearbyPlacesData.execute(dataTransfer);
//            Toast.makeText(MapsActivity.this, "Showing Nearby Hospitals", Toast.LENGTH_SHORT).show();



    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1100);
        locationRequest.setFastestInterval(1100);
        locationRequest.setPriority(locationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);



    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}

