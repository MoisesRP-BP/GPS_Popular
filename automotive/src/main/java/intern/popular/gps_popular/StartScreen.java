package intern.popular.gps_popular;

import static java.lang.Thread.sleep;

import android.location.Location;

import androidx.annotation.NonNull;
import androidx.car.app.CarContext;
import androidx.car.app.Screen;
import androidx.car.app.ScreenManager;
import androidx.car.app.model.Action;
import androidx.car.app.model.CarColor;
import androidx.car.app.model.CarLocation;
import androidx.car.app.model.GridItem;
import androidx.car.app.model.GridTemplate;
import androidx.car.app.model.ItemList;
import androidx.car.app.model.ListTemplate;
import androidx.car.app.model.OnClickListener;
import androidx.car.app.model.Pane;
import androidx.car.app.model.PaneTemplate;
import androidx.car.app.model.Place;
import androidx.car.app.model.Row;
import androidx.car.app.model.Template;

public class StartScreen extends Screen {

    protected StartScreen(@NonNull CarContext carContext) {
        super(carContext);
    }

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
                    public void onClick() {OnMapClick();}
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
                    public void onClick() {OnMapClick();}
                })
                .build();

        list.addItem(one).addItem(two).addItem(three).addItem(four);

        return new ListTemplate.Builder().setSingleList(list.build())
                .setHeaderAction(Action.APP_ICON)
                .build();
    }


    private void OnMapClick() {
//        Location one = new Location("Test");
//        one.setLatitude(18.445945);
//        one.setLongitude(-66.068358);
//        CarLocation car = CarLocation.create(one);
//        Place place = new Place.Builder(car).build();

        for (int i = 0; i < 2; i++) {
            if(i==0){
                getCarContext().getCarService(ScreenManager.class).push(new MiBancoScreen(getCarContext()));
                try {
                    sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                getCarContext().getCarService(ScreenManager.class).push(new MiBancoScreen(getCarContext()));
            }
        }



    }
}
