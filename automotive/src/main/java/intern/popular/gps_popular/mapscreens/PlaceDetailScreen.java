package intern.popular.gps_popular.mapscreens;

import static android.Manifest.permission.CALL_PHONE;

import android.content.Intent;
import android.content.pm.PackageManager;
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

import java.util.zip.ZipInputStream;

public class PlaceDetailScreen extends Screen {

    private String name;
    private String address;
    private Place place;
    private String phone;

    public PlaceDetailScreen(CarContext carContext, String name, String address, Place place, String phone) {
        super(carContext);
        this.name = name;
        this.address = address;
        this.place = place;
        this.phone = phone;
    }

    @NonNull
    @Override
    public Template onGetTemplate() {

        String title1 = "Navigate";
        String title2 = "Back";
        if(!phone.equals("0")){
            title2= "Llamar";
        }

        Pane pane = new Pane.Builder()
                .addAction(
                        new Action.Builder()
                                .setTitle(title1)
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
                                .setTitle(title2)
                                .setBackgroundColor(CarColor.BLUE)
                                .setOnClickListener(new OnClickListener() {
                                    @Override
                                    public void onClick() {
                                        if(!phone.equals("0")){
                                            OnClickCall();
                                        } else{
                                            OnClickBack();
                                        }
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

    private void OnClickCall() {
        Intent call = new Intent(Intent.ACTION_DIAL);
        call.setData(Uri.parse("tel:" + phone));
        PermissionCheckCall(call);
        getCarContext().getCarService(ScreenManager.class).popToRoot();

    }

    private void PermissionCheckCall(Intent intent){
        if (getCarContext().checkSelfPermission(CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            intent.setPackage("com.samsung.android.incallui");
            getCarContext().startCarApp(intent);
        }
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
