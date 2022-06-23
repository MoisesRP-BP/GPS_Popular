package intern.popular.gps_popular;

import static java.lang.Thread.sleep;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.car.app.CarContext;
import androidx.car.app.OnRequestPermissionsListener;
import androidx.car.app.Screen;
import androidx.car.app.ScreenManager;
import androidx.car.app.model.Action;
import androidx.car.app.model.ItemList;
import androidx.car.app.model.ListTemplate;
import androidx.car.app.model.OnClickListener;
import androidx.car.app.model.Row;
import androidx.car.app.model.Template;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.apache.commons.collections4.Get;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import intern.popular.gps_popular.mapscreens.MapPOIScreen;
import intern.popular.gps_popular.servicescreen.CustomerServiceScreen;

public class StartScreen extends Screen {

    protected StartScreen(@NonNull CarContext carContext) {
        super(carContext);
    }

    FusedLocationProviderClient fusedLocationProviderClient;
    public static Location currentLocation;

    @NonNull
    @Override
    public Template onGetTemplate() {
//        ItemList.Builder list = new ItemList.Builder();
//        list.addItem(new GridItem.Builder()
//                        .setTitle("ATM & Sucursales")
//                        .setOnClickListener(new OnClickListener() {
//                            @Override
//                            public void onClick() {
//                                OnMapClick();
//                            }
//                        })
//                        .build()
//                )
//                .addItem(new GridItem.Builder()
//                        .setTitle("Verificar Cuenta")
//                        .build()
//                )
//                .addItem(new GridItem.Builder()
//                        .setTitle("Realizar Pagos")
//                        .build()
//                )
//                .addItem(new GridItem.Builder()
//                        .setTitle("Contactar Servicios al cliente")
//                        .build());
//
//        return new GridTemplate.Builder().setSingleList(list.build())
//                .setTitle("MiBancoApp")
//                .setHeaderAction(Action.APP_ICON)
//                .build();

        ItemList.Builder list = new ItemList.Builder();

        PermissionGranted();

        Row one = new Row.Builder()
                .setTitle("Atm & Sucursales")
                .setBrowsable(true)
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick() {
                        OnMapClick();
                    }
                })
                .build();
        Row two = new Row.Builder()
                .setTitle("Realizar Pago")
                .setBrowsable(true)
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick() {
                        OnMapClick();
                    }
                })
                .build();
        Row three = new Row.Builder()
                .setTitle("Verificar Cuentas")
                .setBrowsable(true)
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick() {
                        OnMapClick();
                    }
                })
                .build();

        Row four = new Row.Builder()
                .setTitle("Servicios al Cliente")
                .setBrowsable(true)
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick() {
                        OnCustomerClick();
                    }
                })
                .build();

        list.addItem(one).addItem(two).addItem(three).addItem(four);

        return new ListTemplate.Builder().setSingleList(list.build())
                .setHeaderAction(Action.APP_ICON)
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
    private void OnAccountClick() {
        try {
            sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getCarContext().getCarService(ScreenManager.class).push(new MapPOIScreen(getCarContext()));
    }
    private void OnPaymentClick() {
        try {
            sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getCarContext().getCarService(ScreenManager.class).push(new MapPOIScreen(getCarContext()));
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
//            double lat = location.getLatitude();
//            double lon = location.getLongitude();
            setCurrentLocation(location);
        }
    };

    private void PermissionGranted(){

        if(getCarContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            LocationManager location = (LocationManager) getCarContext().getSystemService(getCarContext().LOCATION_SERVICE);
            location.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000,10, locationListerGPS);

        } else{
            //permission not granted
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                List<String> permission = new ArrayList<String>();
                permission.add(Manifest.permission.ACCESS_FINE_LOCATION);
                permission.add(Manifest.permission.CALL_PHONE);

                getCarContext().requestPermissions(permission, new OnRequestPermissionsListener() {
                    @Override
                    public void onRequestPermissionsResult(@NonNull List<String> grantedPermissions, @NonNull List<String> rejectedPermissions) {
                        for(String requestCode: rejectedPermissions){
                            switch (requestCode){
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
