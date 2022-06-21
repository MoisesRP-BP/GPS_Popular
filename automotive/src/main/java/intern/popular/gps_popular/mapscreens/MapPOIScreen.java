package intern.popular.gps_popular.mapscreens;

import android.content.res.AssetManager;
import android.location.Location;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.car.app.CarContext;
import androidx.car.app.Screen;
import androidx.car.app.ScreenManager;
import androidx.car.app.model.Action;
import androidx.car.app.model.CarColor;
import androidx.car.app.model.CarLocation;
import androidx.car.app.model.ItemList;
import androidx.car.app.model.Metadata;
import androidx.car.app.model.OnClickListener;
import androidx.car.app.model.Place;
import androidx.car.app.model.PlaceListMapTemplate;
import androidx.car.app.model.PlaceMarker;
import androidx.car.app.model.Row;
import androidx.car.app.model.Template;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MapPOIScreen extends Screen {
    private static final int SPAN_INCLUSIVE_INCLUSIVE = 6;

    public MapPOIScreen(CarContext carContext) {
        super(carContext);
    }

    private FusedLocationProviderClient mFusedLocationClient;

    @NonNull
    @Override
    public Template onGetTemplate() {

        //Create list of locations
        ItemList.Builder items = new ItemList.Builder();

        //Download list from data file
        List<LocationSample> list = new ArrayList<>();
        list = readData();

        for (LocationSample location : list) {
            Location loc = new Location(location.getName());
            loc.setLatitude(location.getLat());
            loc.setLongitude(location.getLon());

            String label = "";
            if (loc.getProvider().toUpperCase().contains("ATM")) {
                label = "ATM";
            } else {
                label = "Suc";
            }

            PlaceMarker mark = new PlaceMarker.Builder().setLabel(label).setColor(CarColor.RED).build();

            CarLocation car = CarLocation.create(loc);
            Place place = new Place.Builder(car).setMarker(mark).build();

            items.addItem(new Row.Builder()
                    .setTitle(loc.getProvider())
                    .setMetadata(
                            new Metadata.Builder()
                                    .setPlace(place)
                                    .build()
                    )
                    .setBrowsable(true)
                    .setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick() {
                            onPlaceClick(location.getName(), location.getAddress());
                        }
                    })
                    .build());

        }

        PlaceListMapTemplate.Builder builder = new PlaceListMapTemplate.Builder()
                .setTitle("Bancos & Atms")
                .setHeaderAction(Action.BACK)
                .setCurrentLocationEnabled(false);

        if (list.isEmpty()) {
            return builder.setLoading(true).build();
        } else {
            return builder.setItemList(items.build()).build();
        }
    }

    private void onPlaceClick(String name, String address) {
        getCarContext().getCarService(ScreenManager.class).push(new PlaceDetailScreen(getCarContext(), name, address));
    }

    private List<LocationSample> readData() {

        List<Location> location = new ArrayList<>();
        List<LocationSample> locsamp = new ArrayList<>();
        AssetManager assetManager = getCarContext().getAssets();
        InputStream is = null;

        try {
            is = assetManager.open("data.csv");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

        String line = "";

        try {
            reader.readLine();

            //temporary coordinates
            double userlat = 18.196343;
            double userlon = -67.141985;

            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                LatLng latLng = new LatLng(Double.parseDouble(tokens[11]), Double.parseDouble(tokens[12]));
                if(Math.sqrt(Math.pow((userlat -latLng.latitude),2) + Math.pow((userlon -latLng.longitude),2))>=0.08){
                    continue;
                }

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

                Log.d("My Activity", "Just created:" + sample);

                locsamp.add(sample);
//                Location newLocation = new Location(sample.getName());
//                newLocation.setLatitude(sample.getLat());
//                newLocation.setLongitude(sample.getLon());
//
//                location.add(newLocation);

            }
        } catch (IOException e) {

            e.printStackTrace();
        }


        return locsamp;
    }

}
