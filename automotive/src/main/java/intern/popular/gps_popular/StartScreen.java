package intern.popular.gps_popular;

import static java.lang.Thread.sleep;

import androidx.annotation.NonNull;
import androidx.car.app.CarContext;
import androidx.car.app.Screen;
import androidx.car.app.ScreenManager;
import androidx.car.app.model.Action;
import androidx.car.app.model.ItemList;
import androidx.car.app.model.ListTemplate;
import androidx.car.app.model.OnClickListener;
import androidx.car.app.model.Row;
import androidx.car.app.model.Template;

import intern.popular.gps_popular.mapscreens.MapPOIScreen;
import intern.popular.gps_popular.servicescreen.CustomerServiceScreen;

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
                        OnMapClick();
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
}
