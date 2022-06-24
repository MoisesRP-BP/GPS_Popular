package intern.popular.gps_popular;

import static java.lang.Thread.sleep;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.car.app.CarContext;
import androidx.car.app.OnRequestPermissionsListener;
import androidx.car.app.Screen;
import androidx.car.app.ScreenManager;
import androidx.car.app.model.Action;
import androidx.car.app.model.CarIcon;
import androidx.car.app.model.GridItem;
import androidx.car.app.model.GridTemplate;
import androidx.car.app.model.ItemList;
import androidx.car.app.model.OnClickListener;
import androidx.car.app.model.Row;
import androidx.car.app.model.Template;

import com.google.android.gms.location.FusedLocationProviderClient;

import java.util.ArrayList;
import java.util.List;

import intern.popular.gps_popular.mapscreens.MapPOIScreen;
import intern.popular.gps_popular.servicescreen.CustomerServiceScreen;

public class StartScreen extends Screen {

    public static Location currentLocation;

    protected StartScreen(@NonNull CarContext carContext) {
        super(carContext);
    }

    @NonNull
    @Override
    public Template onGetTemplate() {

        PermissionGranted();

        ItemList.Builder itemList = new ItemList.Builder();

        GridItem item1 = new GridItem.Builder()
                .setTitle("ATM & Sucursales")
                .setImage(CarIcon.APP_ICON)
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick() {
                        OnMapClick();
                    }
                })
                .build();

        GridItem item2 = new GridItem.Builder()
                .setTitle("Realizar Pago")
                .setImage(CarIcon.APP_ICON)
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick() {
                        OnPaymentClick();
                    }
                })
                .build();

        GridItem item3 = new GridItem.Builder()
                .setTitle("Verificar Cuentas")
                .setImage(CarIcon.APP_ICON)
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick() {
                        OnVerifyClick();
                    }
                })
                .build();

        GridItem item4 = new GridItem.Builder()
                .setTitle("Servicio al Cliente")
                .setImage(CarIcon.APP_ICON)
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick() {
                        OnCustomerClick();
                    }
                })
                .build();

        itemList.addItem(item1);
        itemList.addItem(item2);
        itemList.addItem(item3);
        itemList.addItem(item4);

        return new GridTemplate.Builder().setSingleList(itemList.build())
                .setHeaderAction(Action.APP_ICON)
                .setTitle("MiBanco")
                .build();

    }


    private void OnMapClick() {
        try {
            sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getCarContext().getCarService(ScreenManager.class).push(new MapPOIScreen(getCarContext()));
    }

    private void OnVerifyClick() {
        try {
            sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getCarContext().getCarService(ScreenManager.class).push(new VerifyScreen(getCarContext()));
    }

    private void OnPaymentClick() {
        try {
            sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getCarContext().getCarService(ScreenManager.class).push(new PaymentScreen(getCarContext()));
    }

    private void OnCustomerClick() {
        try {
            sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getCarContext().getCarService(ScreenManager.class).push(new CustomerServiceScreen(getCarContext()));
    }

    //Confirm user location so we can send it to gps
    LocationListener locationListerGPS = new LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            setCurrentLocation(location);
        }
    };

    private void PermissionGranted() {

        if (getCarContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationManager location = (LocationManager) getCarContext().getSystemService(getCarContext().LOCATION_SERVICE);
            location.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListerGPS);

        } else {
            //permission not granted
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                List<String> permission = new ArrayList<String>();
                permission.add(Manifest.permission.ACCESS_FINE_LOCATION);
                permission.add(Manifest.permission.CALL_PHONE);

                getCarContext().requestPermissions(permission, new OnRequestPermissionsListener() {
                    @Override
                    public void onRequestPermissionsResult(@NonNull List<String> grantedPermissions, @NonNull List<String> rejectedPermissions) {
                        for (String requestCode : rejectedPermissions) {
                            switch (requestCode) {
                                case "ACCESS_FINE_LOCATION":
                                    Toast.makeText(getCarContext(), "This app requires location to be granted in order to work properly", Toast.LENGTH_SHORT).show();
                                    break;
                                case "CALL_PHONE":
                                    Toast.makeText(getCarContext(), "This app requires calls to be granted in order to work properly", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    }
                });
            }
        }
    }


    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }


}
