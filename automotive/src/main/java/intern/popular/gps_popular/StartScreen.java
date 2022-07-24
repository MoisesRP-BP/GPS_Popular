package intern.popular.gps_popular;

import static java.lang.Thread.sleep;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
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
import androidx.car.app.model.Template;
import androidx.core.graphics.drawable.IconCompat;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import intern.popular.gps_popular.accountscreen.VerifyScreen;
import intern.popular.gps_popular.mapscreens.MapPOIScreen;
import intern.popular.gps_popular.paymentscreen.PaymentScreen;
import intern.popular.gps_popular.servicescreen.CustomerServiceScreen;

public class StartScreen extends Screen {

    public static Location currentLocation;
    boolean gps_enabled = false;
    boolean network_enabled = false;

    protected StartScreen(@NonNull CarContext carContext) {
        super(carContext);
    }

    @NonNull
    @Override
    public Template onGetTemplate() {
        PermissionGranted();
        List<CarIcon> icons = IconMaker();

        ItemList.Builder itemList = new ItemList.Builder();

        GridItem item1 = new GridItem.Builder()
                .setTitle("ATM & Sucursales")
                .setImage(icons.get(0))
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick() {
                        OnMapClick();
                    }
                })
                .build();

        GridItem item2 = new GridItem.Builder()
                .setTitle("Realizar Pago")
                .setImage(icons.get(1))
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick() {
                        OnPaymentClick();
                    }
                })
                .build();

        GridItem item3 = new GridItem.Builder()
                .setTitle("Verificar Cuentas")
                .setImage(icons.get(2))
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick() {
                        OnVerifyClick();
                    }
                })
                .build();

        GridItem item4 = new GridItem.Builder()
                .setTitle("Servicio al Cliente")
                .setImage(icons.get(3))
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick() {
                        OnCustomerClick();
                    }
                })
                .build();

        GridItem item5 = new GridItem.Builder()
                .setTitle("ATH Movil")
                .setText("Coming Soon")
                .setImage(icons.get(4))
                .build();

        itemList.addItem(item1);
        itemList.addItem(item2);
        itemList.addItem(item3);
        itemList.addItem(item4);
        itemList.addItem(item5);

        return new GridTemplate.Builder().setSingleList(itemList.build())
                .setHeaderAction(Action.APP_ICON)
                .setTitle("MiBanco")
                .build();

    }


    private void OnMapClick() {
        try {
            sleep(10);
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
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            setCurrentLocation(location);
        }
    };

    private void PermissionGranted() {

        if (getCarContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationManager location = (LocationManager) getCarContext().getSystemService(getCarContext().LOCATION_SERVICE);

            try {
                gps_enabled = location.isProviderEnabled(LocationManager.GPS_PROVIDER);
            } catch (Exception ex) {
            }
            try {
                network_enabled = location.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            } catch (Exception ex) {
            }

            if (gps_enabled) {
                location.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListerGPS);
            }
            if (network_enabled && currentLocation == null) {
                location.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 10, locationListerGPS);
            }

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

    private List<CarIcon> IconMaker() {
        List<CarIcon> list = new ArrayList<>();
        List<Bitmap> bitmaps = new ArrayList<>();

        //ATM+Sucursales
        bitmaps.add(BitmapFactory.decodeResource(getCarContext().getResources(), R.mipmap.atmsucursales_foreground));
        //Payments
        bitmaps.add(BitmapFactory.decodeResource(getCarContext().getResources(), R.mipmap.payment_foreground));
        //Cuentas
        bitmaps.add(BitmapFactory.decodeResource(getCarContext().getResources(), R.mipmap.cuentas_foreground));
        //Customer Service
        bitmaps.add(BitmapFactory.decodeResource(getCarContext().getResources(), R.mipmap.custserv_foreground));
        //ATH Movil
        bitmaps.add(BitmapFactory.decodeResource(getCarContext().getResources(), R.mipmap.athmovil_foreground));


        for (Bitmap bit : bitmaps) {
            IconCompat iconCompat = IconCompat.createWithBitmap(bit);
            CarIcon car = new CarIcon.Builder(iconCompat).build();
            list.add(car);
        }
        return list;
    }
}
