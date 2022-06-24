package intern.popular.gps_popular.mapscreens;

import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.car.app.CarContext;
import androidx.car.app.Screen;
import androidx.car.app.ScreenManager;
import androidx.car.app.model.Action;
import androidx.car.app.model.CarColor;
import androidx.car.app.model.OnClickListener;
import androidx.car.app.model.Pane;
import androidx.car.app.model.PaneTemplate;
import androidx.car.app.model.Place;
import androidx.car.app.model.Row;
import androidx.car.app.model.Template;

public class PlaceDetailScreen extends Screen {

    private String name;
    private String address;
    private Place place;

    public PlaceDetailScreen(CarContext carContext, String name, String address, Place place) {
        super(carContext);
        this.name = name;
        this.address = address;
        this.place = place;
    }

    @NonNull
    @Override
    public Template onGetTemplate() {
        Pane pane = new Pane.Builder()
                .addAction(
                        new Action.Builder()
                                .setTitle("Navigate")
                                .setBackgroundColor(CarColor.BLUE)
                                .setOnClickListener(new OnClickListener() {
                                    @Override
                                    public void onClick() {
                                        OnClickNavigate(place, name);
                                    }
                                })
                                .build()
                ).addAction(
                        new Action.Builder()
                                .setTitle("Back")
                                .setBackgroundColor(CarColor.BLUE)
                                .setOnClickListener(new OnClickListener() {
                                    @Override
                                    public void onClick() {
                                        OnClickBack();
                                    }
                                })
                                .build()
                )
                .addRow(
                        new Row.Builder()
                                .setTitle("Address")
                                .addText(address)
                                .build()
                )
                .build();
        return new PaneTemplate.Builder(pane)
                .setTitle(name)
                .setHeaderAction(Action.BACK)
                .build();
    }

    private void OnClickNavigate(Place place, String name) {
        String getLat = Double.toString(place.getLocation().getLatitude());
        String getLon =Double.toString(place.getLocation().getLongitude());
        Uri uri = Uri.parse("geo:0,0?q=" + getLat + "," + getLon + "(" + name + ")");
        Intent intent = new Intent(getCarContext().ACTION_NAVIGATE, uri);
        intent.setPackage("com.google.android.apps.maps");
        getCarContext().startCarApp(intent);
        getCarContext().getCarService(ScreenManager.class).popToRoot();
    }

    private void OnClickBack() {
        getCarContext().getCarService(ScreenManager.class).pop();

    }
}
