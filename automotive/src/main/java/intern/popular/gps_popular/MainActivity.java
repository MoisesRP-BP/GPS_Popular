package intern.popular.gps_popular;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationRequest;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_FINE_LOCATION = 99;

    TextView tv_lat, tv_lon, tv_accuracy, tv_altitude, tv_speed, tv_sensor, tv_updates, tv_address, tv_countOfAtmSuc;

    Switch sw_locationsupdates, sw_gps;

    Button btn_atm_suc, btn_newlist, btn_showMap;

    //change location
    boolean updateOn = false;

    //current location
    Location currentLocation;

    //list of locations
    List<Location> savedLocations;

    //Config file for all related location for the Google Api
    LocationRequest locationRequest;

    LocationCallback locationCallBack;

    //Google API for location
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv_lat = findViewById(R.id.tv_lat);
        tv_lon = findViewById(R.id.tv_lon);
        tv_accuracy = findViewById(R.id.tv_accuracy);
        tv_altitude = findViewById(R.id.tv_altitude);
        tv_speed = findViewById(R.id.tv_speed);
        tv_sensor = findViewById(R.id.tv_sensor);
        tv_updates = findViewById(R.id.tv_updates);
        tv_address = findViewById(R.id.tv_address);
        tv_countOfAtmSuc = findViewById(R.id.tv_countOfAtmSuc);

        sw_gps = findViewById(R.id.sw_gps);
        sw_locationsupdates = findViewById(R.id.sw_locationsupdates);

        btn_atm_suc= findViewById(R.id.btn_atm_suc);
        btn_newlist= findViewById(R.id.btn_newlist);
        btn_showMap= findViewById(R.id.btn_showmap);

        locationRequest = new LocationRequest();

        //Default location check
        locationRequest.setInterval(3000);

        //Location check occurrences
        locationRequest.setFastestInterval(5000);

        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        //event that is triggered whenever the update interval is meet
        locationCallBack = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);

                //save the last location
                updateUIValues(locationResult.getLastLocation());

            }
        };

        btn_newlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the gps location



                //add elements to the list
                MyApplication myApplication= (MyApplication) getApplicationContext();
                savedLocations= myApplication.getMylocations();

                List<Location> data = readLocationData();

                for(Location location: data){
                    savedLocations.add(location);
                }

            }
        });

        btn_atm_suc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ShowSavedLocationList.class);
                startActivity(i);

            }
        });

        btn_showMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(i);
            }
        });

        sw_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sw_gps.isChecked()) {
                    //most accurate gps
                    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                    tv_sensor.setText("Using GPS sensor");
                } else {
                    locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
                    tv_sensor.setText("Using Tower + WIFI");
                }
            }
        });

        sw_locationsupdates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sw_locationsupdates.isChecked()) {
                    //turn on location tracking

                    startLocationUpdates();

                } else{
                    //turn of location tracking
                    stopLocationUpdates();

                }

            }
        });


        updateGPS();

    }   //end OnCreate methods

    private void startLocationUpdates() {
        tv_updates.setText("Location is being tracked");
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallBack, null);
        updateGPS();
    }

    private void stopLocationUpdates() {
        tv_updates.setText("Location is NOT being tracked");
        tv_lat.setText("Not tracking location");
        tv_lon.setText("Not tracking location");
        tv_speed.setText("Not tracking");
        tv_address.setText("Not tracking location");
        tv_accuracy.setText("Not tracking location");
        tv_altitude.setText("Not tracking location");
        tv_sensor.setText("Not tracking location");
        fusedLocationProviderClient.removeLocationUpdates(locationCallBack);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case PERMISSION_FINE_LOCATION:
                if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    updateGPS();
                } else{
                    Toast.makeText(this, "This app requires permissions to be granted in order to work properly", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }

    private void updateGPS(){
        //get permissions from the user to track GPS
        // get the current location
        // update the UI - i.e. set all properties

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MainActivity.this);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            // user provided the permission
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    //we got permissions; Put Values
                    updateUIValues(location);
                    currentLocation= location;
                }
            });
        } else{
            //permission not granted

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_FINE_LOCATION);
            }
        }
    }

    private void updateUIValues(Location location) {
        //update all fo the text view
        tv_lat.setText(String.valueOf(location.getLatitude()));
        tv_lon.setText(String.valueOf(location.getLongitude()));
        tv_accuracy.setText(String.valueOf(location.getAccuracy()));

        if(location.hasAltitude()){
            tv_altitude.setText(String.valueOf(location.getAltitude()));
        } else{
            tv_altitude.setText("Not available");
        }
        if(location.hasSpeed()){
            tv_altitude.setText(String.valueOf(location.getSpeed()));
        } else{
            tv_altitude.setText("Not available");
        }

        Geocoder geocoder= new Geocoder(MainActivity.this);

        try {
            List<Address> addresses= geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            tv_address.setText(addresses.get(0).getAddressLine(0));
        } catch (Exception e) {
            tv_address.setText("Unable to get street address");
        }

        MyApplication myApplication= (MyApplication) getApplicationContext();
        savedLocations= myApplication.getMylocations();

        //Show list of location
        tv_countOfAtmSuc.setText(Integer.toString(savedLocations.size()));

    }


    private List<LocationSample> locationSamples= new ArrayList<>();

    private List<Location> readLocationData() {
        InputStream is = getResources().openRawResource(R.raw.data);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );
        List<Location> location = new ArrayList<>();

        String line= "";
        try{
            //remove header
            reader.readLine();

            while((line =reader.readLine() )!= null){

                //Split by ',' except commas in qoutes e.g. "San Juan, Puerto Rico"
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                //Read data
                LocationSample sample = new LocationSample();

                sample.setId(Integer.parseInt(tokens[0]));
                sample.setType(Integer.parseInt(tokens[1]));
                sample.setName(tokens[2]);
                sample.setName_en(tokens[3]);
                sample.setArea(tokens[4]);
                if(tokens[5].length()>0){
                    sample.setPhone(tokens[5]);
                } else{
                    sample.setPhone("0");
                }
                if(tokens[5].length()>0){
                    sample.setFax(tokens[6]);
                } else{
                    sample.setFax("0");
                }
                sample.setHours_weekly_range(Boolean.getBoolean(tokens[7]));
                sample.setHours(tokens[8]);
                sample.setAddress(tokens[9]);
                sample.setCity(tokens[10]);
                sample.setLat(Double.parseDouble(tokens[11]));
                sample.setLon(Double.parseDouble(tokens[12]));
                locationSamples.add(sample);

                Log.d("My Activity", "Just created:" + sample);

                Location newLocation = new Location(sample.getName());
                newLocation.setLatitude(sample.getLat());
                newLocation.setLongitude(sample.getLon());

                location.add(newLocation);
            }

        } catch(IOException e){
            Log.wtf("My Activity", "Error reading data file on line" + line, e);
            e.printStackTrace();
        }

        return location;
    }

}