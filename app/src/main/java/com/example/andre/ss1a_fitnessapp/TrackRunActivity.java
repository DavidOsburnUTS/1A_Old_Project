package com.example.andre.ss1a_fitnessapp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Criteria;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;
import 	android.location.LocationManager;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class TrackRunActivity extends FragmentActivity
        implements OnMapReadyCallback, LocationListener, View.OnClickListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnPolylineClickListener,
        GoogleMap.OnPolygonClickListener, SensorEventListener, StepListener {

    private GoogleMap mMap;
    private TextView distanceTv, avgSpeed;
    private ArrayList<LatLng> routePoints;
    private Polyline line;
    private Chronometer runTimerCm;

    private boolean isDraw;
    private boolean mLocationPermission;
    private LocationRequest mLocationRequest;
    private LocationCallback mLocationCallback;

    private CameraPosition mCameraPosition;
    private Location mLastKnownLocation;
    private Location mCurrentLocation;
    private LatLng currentLatLng;
    private LatLng lastLatLng;
    private FusedLocationProviderClient mFusedLocationProviderClient;

    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";
    private static final int DEFAULT_ZOOM = 18;
    private static final String TAG = TrackRunActivity.class.getSimpleName();
    private final LatLng mDefaultLocation = new LatLng(-33.8523341, 151.2106085);
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private long pauseOffset;



    /*
    THESE FIELDS FOR STEP COUNTER
     */

    private TextView TvSteps;
    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private static final String TEXT_NUM_STEPS = "Number of Steps: ";
    private int numSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_run);

        routePoints = new ArrayList<LatLng>();
        distanceTv = findViewById(R.id.distanceTv);
        findViewById(R.id.trackRunPauseBtn).setOnClickListener(this);
        findViewById(R.id.trackRunStartBtn).setOnClickListener(this);
        findViewById(R.id.trackRunStopBtn).setOnClickListener(this);
        findViewById(R.id.trackRunPauseBtn).setEnabled(false);
        findViewById(R.id.trackRunStopBtn).setEnabled(false);
        runTimerCm = (Chronometer)findViewById(R.id.run_timer);
        findViewById(R.id.runBackBtn).setOnClickListener(this);
        avgSpeed = findViewById(R.id.avgSpeedTv);


        TvSteps = (TextView) findViewById(R.id.stepsTv);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);

        numSteps = 0;


        if (savedInstanceState != null) {
            mLastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            mCameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(
                            new LatLng(location.getLatitude(),
                                    location.getLongitude())));
                        onLocationChanged(location);
                }
            }
        };
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.trackRunPauseBtn:
                isDraw = false;
                runTimerCm.stop();
                pauseOffset = SystemClock.elapsedRealtime() - runTimerCm.getBase();
                line.setColor(Color.GRAY);
                stopLocationUpdates();

                sensorManager.unregisterListener(TrackRunActivity.this);
                break;

            case R.id.trackRunStartBtn:
                isDraw = true;
                startLocationUpdates();
                runTimerCm.setBase(SystemClock.elapsedRealtime() - pauseOffset);
                runTimerCm.start();
                findViewById(R.id.trackRunPauseBtn).setEnabled(true);
                findViewById(R.id.trackRunStopBtn).setEnabled(true);
                //Step code
                sensorManager.registerListener(TrackRunActivity.this, accel, SensorManager.SENSOR_DELAY_FASTEST);
                break;

            case R.id.trackRunStopBtn:
                isDraw = false;
                mMap.addMarker(new MarkerOptions().position(routePoints.get(routePoints.size() - 1)).
                    icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))); //add Marker in finished position
                stopLocationUpdates();
                runTimerCm.stop();
                pauseOffset = SystemClock.elapsedRealtime() - runTimerCm.getBase();

                sensorManager.unregisterListener(TrackRunActivity.this);
                break;

            case R.id.runBackBtn:
                finish();
        }
    }

    /**
     * Saves the state of the map when the activity is paused.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mMap != null) {
            outState.putParcelable(KEY_CAMERA_POSITION, mMap.getCameraPosition());
            outState.putParcelable(KEY_LOCATION, mLastKnownLocation);
            super.onSaveInstanceState(outState);
        }
    }
    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Prompt the user for permission.
        getLocationPermission();
        // Turn on the My Location layer and the related control on the map.
        updateLocationUI();
        // Get the current location of the device and set the position of the map.
        getDeviceLocation();
    }

    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (mLocationPermission) {
                Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            mLastKnownLocation = task.getResult();
                            currentLatLng = new LatLng(mLastKnownLocation.getLatitude(),
                                    mLastKnownLocation.getLongitude());
                            mCurrentLocation = mLastKnownLocation;
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(mLastKnownLocation.getLatitude(),
                                            mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                        } else {
                            Log.d(TAG, "Current location is null. Using defaults.");
                            Log.e(TAG, "Exception: %s", task.getException());
                            mMap.moveCamera(CameraUpdateFactory
                                    .newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (mLocationPermission) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

//    private void getLocationPermission() {
//        /*
//         * Request location permission, so that we can get the location of th
//         * device. The result of the permission request is handled by a callback,
//         * onRequestPermissionsResult.
//         */
//        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
//                android.Manifest.permission.ACCESS_FINE_LOCATION)
//                == PackageManager.PERMISSION_GRANTED) {
//            mLocationPermission = true;
//        } else {
//            ActivityCompat.requestPermissions(this,
//                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
//                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
//        }
//    }

    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of th
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermission = true;
        } else {
            //permission rationale triggered
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle(R.string.title_location_permission)
                        .setMessage(R.string.permission_rationale)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            //requests permission again
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(TrackRunActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                                TrackRunActivity.this.onBackPressed();

                            }
                        })
                        //permission denied. exits page
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                TrackRunActivity.this.onBackPressed();
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            }
        }
    }

    /**
     * Handles the result of the request for location permissions.
     */
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           @NonNull String permissions[],
//                                           @NonNull int[] grantResults) {
//        mLocationPermission = false;
//        switch (requestCode) {
//            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    mLocationPermission = true;
//                }
//            }
//        }
//        updateLocationUI();
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                        mFusedLocationProviderClient.requestLocationUpdates(mLocationRequest,
                                mLocationCallback,
                                null /* Looper */);
                        mLocationPermission = true;

                    } else {
                        // permission denied, boo! Disable the
                        // functionality that depends on this permission.
                        TrackRunActivity.this.onBackPressed();
                    }
                }
                updateLocationUI();
            }
        }
    }

    private void startLocationUpdates() {
        createLocationRequest();
        //drop marker with coordinate
        //current location = coordinate;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocationProviderClient.requestLocationUpdates(mLocationRequest,
                mLocationCallback,
                null /* Looper */);
    }

    private void stopLocationUpdates() {
        mFusedLocationProviderClient.removeLocationUpdates(mLocationCallback);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @Override
    public void onLocationChanged(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        if(isDraw) {
            routePoints.add(latLng); //added
        }
        drawLine();
    }

    private void drawLine(){
        if(isDraw) {
            float distance = 0;
            PolylineOptions options = new PolylineOptions().width(15).color(Color.GREEN).geodesic(true);
            for (int i = 0; i < routePoints.size(); i++) {
                if(i > 0) {
                    LatLng prev = routePoints.get(i-1);
                    LatLng curr = routePoints.get(i);

                    Location prevLocation = new Location("prevLocation");
                    prevLocation.setLatitude(prev.latitude);
                    prevLocation.setLongitude(prev.longitude);

                    Location currLocation = new Location("currLocation");
                    currLocation.setLatitude(curr.latitude);
                    currLocation.setLongitude(curr.longitude);

                    float dis = prevLocation.distanceTo(currLocation);

                    distance += dis;
                    float dist = distance/1000;
                    String s = String.format("%.02f", dist);
                    long timeElapsed = SystemClock.elapsedRealtime() - pauseOffset;
                    float hours = (float) (timeElapsed / 3600000);
                    float speed = dist/hours;
                    String spd = String.format("%.02f", speed);

                    distanceTv.setText("Distance: " + s + " km");
                    avgSpeed.setText("Avg Speed: " + spd + "/hr");
                }
                LatLng point = routePoints.get(i);
                options.add(point);
            }
            line = mMap.addPolyline(options); //add Polyline
            mMap.addMarker(new MarkerOptions().position(routePoints.get(0))
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))); //add Marker in starting position
        }
    }

    private void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(7000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @Override
    public void onPolygonClick(Polygon polygon) {
    }

    @Override
    public void onPolylineClick(Polyline polyline) {
    }


    /*

    BELOW IS FOR STEP COUNTER

     */


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void step(long timeNs) {
        numSteps++;
        TvSteps.setText(TEXT_NUM_STEPS + numSteps);
    }
















}
