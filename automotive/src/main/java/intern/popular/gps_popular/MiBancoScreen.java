package intern.popular.gps_popular;

import android.location.Location;
import android.text.SpannableString;

import androidx.annotation.NonNull;
import androidx.car.app.CarContext;
import androidx.car.app.Screen;
import androidx.car.app.ScreenManager;
import androidx.car.app.model.Action;
import androidx.car.app.model.CarLocation;
import androidx.car.app.model.Distance;
import androidx.car.app.model.DistanceSpan;
import androidx.car.app.model.ItemList;
import androidx.car.app.model.Metadata;
import androidx.car.app.model.OnClickListener;
import androidx.car.app.model.Place;
import androidx.car.app.model.PlaceListMapTemplate;
import androidx.car.app.model.Row;
import androidx.car.app.model.Template;

import java.util.ArrayList;
import java.util.List;

public class MiBancoScreen extends Screen {
    private static final int SPAN_INCLUSIVE_INCLUSIVE = 6;
    private static final Object UNIT_KILOMETERS = "km";

    public MiBancoScreen(CarContext carContext) {
        super(carContext);
    }
    @NonNull
    @Override
    public Template onGetTemplate() {

//        Row a = new Row.Builder().setTitle("Mai frend").build();
//        Pane pane = new Pane.Builder().addRow(a).build();

        //Create list of locations
        ItemList.Builder items = new ItemList.Builder();

        //Download list from data file
//        LocationSample location = new LocationSample();
//        List<Location> list = location.readLocationData();
        List<Location> list = new ArrayList<>();

        Location one = new Location("Test");
        one.setLatitude(18.445945);
        one.setLongitude(-66.068358);
        Location two = new Location("Test2");
        two.setLatitude(18.464325);
        two.setLongitude(-66.115256);
        Location three = new Location("Test3");
        three.setLatitude(18.38078);
        three.setLongitude(-65.962161);
        Location four = new Location("Test4");
        four.setLatitude(18.380254);
        four.setLongitude(-66.05493);
        Location five = new Location("Test5");
        five.setLatitude(18.451288);
        five.setLongitude(-66.058589);
        list.add(one);
        list.add(two);
        list.add(three);
        list.add(four);
        list.add(five);

        for (Location loc : list) {
            CarLocation car = CarLocation.create(loc);
            Place place = new Place.Builder(car).build();

            String temp = loc.getProvider();
            SpannableString string = new SpannableString("  " + temp + " Point-of-Interest 1");
            string.setSpan(
                    DistanceSpan.create(
                            Distance.create(1000, Distance.UNIT_METERS)), 0, 1, SPAN_INCLUSIVE_INCLUSIVE);

            items.addItem(new Row.Builder()
                    .setTitle(string)
                    .setMetadata(
                            new Metadata.Builder()
                                    .setPlace(place)
                                    .build()
                    )
//                    .setBrowsable(true)
//                            .setOnClickListener(OnPlaceClick(getCarContext()))
                    .build());

        }

        PlaceListMapTemplate.Builder builder = new PlaceListMapTemplate.Builder()
                .setTitle("Bancos & Atms")
                .setHeaderAction(Action.APP_ICON)
                .setCurrentLocationEnabled(false);

        if (list.isEmpty()) {
            return builder.setLoading(true).build();
        } else {
            return builder.setItemList(items.build()).build();
        }
    }

//    private OnClickListener OnPlaceClick(CarContext carContext) {
//        return carContext.getCarService(ScreenManager.class).push(new PlaceDetailScreen(carContext));
//    }


//        Pane pane = new Pane.Builder()
//                .addAction(
//                        new Action.Builder()
//                                .setTitle("Testing")
//                                .setBackgroundColor(CarColor.BLUE)
//                                .build()
//                )
//                .addAction(
//                        new Action.Builder()
//                                .setTitle("Back")
//                                .setBackgroundColor(CarColor.BLUE)
//                                .build()
//                )
//                .addAction(
//                        new Action.Builder()
//                                .setTitle("Left")
//                                .setBackgroundColor(CarColor.BLUE)
//                                .build()
//                ).build();

//        return new PaneTemplate.Builder(pane)
//                .setHeaderAction(Action.BACK)
//                .build();
//    }

}
