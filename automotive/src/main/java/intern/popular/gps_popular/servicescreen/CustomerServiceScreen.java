package intern.popular.gps_popular.servicescreen;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.car.app.CarContext;
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
import static android.Manifest.permission.CALL_PHONE;

import java.util.ArrayList;
import java.util.List;

import intern.popular.gps_popular.R;

public class CustomerServiceScreen extends Screen {
    public CustomerServiceScreen(CarContext carContext) {
        super(carContext);
    }

    @NonNull
    @Override
    public Template onGetTemplate() {

        String titleAuto = "Popular Auto";
        String titleMortgage = "Popular Mortgage";
        String titleTeleBanco = "Popular TeleBanco";
        String titleSeguros = "Seguros";
        String titleSecurity = "Popular Security";
        String titleComercio = "Banca Comercial";
        String titleOtros = "Otros";


        Bitmap bit1 = BitmapFactory.decodeResource(getCarContext().getResources(),R.mipmap.mibanco_logo);
        Bitmap bit2 = BitmapFactory.decodeResource(getCarContext().getResources(), R.mipmap.popular_mortgage_foreground);

        Bitmap logo1 = Bitmap.createScaledBitmap(bit1, 256, 256, true);
        Bitmap logo2 = Bitmap.createScaledBitmap(bit2, 256, 256, true);

        IconCompat iconCompat1 = IconCompat.createWithBitmap(logo1);
        IconCompat iconCompat2 = IconCompat.createWithBitmap(logo2);
        CarIcon car1 = new CarIcon.Builder(iconCompat1).build();
        CarIcon car2 = new CarIcon.Builder(iconCompat2).build();

        ItemList.Builder list = new ItemList.Builder();

        list.addItem(new GridItem.Builder()
                        .setTitle(titleTeleBanco)
                        .setImage(car1)
                        .setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick() {
                                Intent call = new Intent(Intent.ACTION_DIAL);
                                call.setData(Uri.parse("tel:787-724-3650"));
                                PermissionCheckCall(call);

                            }
                        })
                        .build()
                )
                .addItem(new GridItem.Builder()
                        .setTitle(titleMortgage)
                        .setImage(car2)
                        .setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick() {
                                OnMortgageClick();

                            }
                        })
                        .build()
                )
                .addItem(new GridItem.Builder()
                        .setTitle(titleAuto)
                        .setImage(car1)
                        .setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick() {
                                OnCustomClick(ItemBuilders(titleAuto), titleAuto);
//                                OnAutoClick();
                            }
                        })
                        .build()
                )
                .addItem(new GridItem.Builder()
                        .setTitle("Seguros")
                        .setImage(car1)
                        .setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick() {
                                Intent call = new Intent(Intent.ACTION_DIAL);
                                call.setData(Uri.parse("tel:787-522-4945"));
                                PermissionCheckCall(call);

                            }
                        })
                        .build()
                )
                .addItem(new GridItem.Builder()
                        .setTitle("Tarjetas de Credito")
                        .setImage(car1)
                        .setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick() {
                                Intent call = new Intent(Intent.ACTION_DIAL);
                                call.setData(Uri.parse("tel:787-775-1100"));
                                PermissionCheckCall(call);

                            }
                        })
                        .build()
                )
                .addItem(new GridItem.Builder()
                        .setTitle("Arrendamientos")
                        .setImage(car1)
                        .setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick() {
                                Intent call = new Intent(Intent.ACTION_DIAL);
                                call.setData(Uri.parse("tel:787-775-1100"));
                                PermissionCheckCall(call);

                            }
                        })
                        .build()
                )
                .addItem(new GridItem.Builder()
                        .setTitle("Popular Security")
                        .setImage(car1)
                        .setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick() {
                                onFraudeClick();
                            }
                        })
                        .build()
                );

        return new GridTemplate.Builder().setSingleList(list.build())
                .setTitle("MiBancoApp")
                .setHeaderAction(Action.BACK)
                .build();
    }

    private void PermissionCheckCall(Intent intent){
        if (getCarContext().checkSelfPermission(CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            intent.setPackage("com.samsung.android.incallui");
            getCarContext().startCarApp(intent);
        }
    }

    //Open Popular Auto Contact numbers
    private void OnAutoClick() {
        getCarContext().getCarService(ScreenManager.class).push(new PopularAutoScreen(getCarContext()));
    }
    //Open Fraud Contact numbers
    private void onFraudeClick() {
        getCarContext().getCarService(ScreenManager.class).push(new SecurityScreen(getCarContext()));
    }
    //Open Popular Mortgage Contact numbers
    private void OnMortgageClick() {
        getCarContext().getCarService(ScreenManager.class).push(new PopularMortgageScreen(getCarContext()));
    }

    //Create Each page Contact numbers
    private void OnCustomClick(List<PhoneList> contact, String title) {
        getCarContext().getCarService(ScreenManager.class).push(new PopularContactScreen(getCarContext(), contact, title));
    }

    private List<PhoneList> ItemBuilders(String string){

        Bitmap bit1 = BitmapFactory.decodeResource(getCarContext().getResources(),R.mipmap.mibanco_logo);
        Bitmap bit2 = BitmapFactory.decodeResource(getCarContext().getResources(), R.mipmap.popular_mortgage_foreground);

        Bitmap logo1 = Bitmap.createScaledBitmap(bit1, 256, 256, true);
        Bitmap logo2 = Bitmap.createScaledBitmap(bit2, 256, 256, true);

        IconCompat iconCompat1 = IconCompat.createWithBitmap(logo1);
        IconCompat iconCompat2 = IconCompat.createWithBitmap(logo2);
        CarIcon car1 = new CarIcon.Builder(iconCompat1).build();
        CarIcon car2 = new CarIcon.Builder(iconCompat2).build();

        List<PhoneList> phoneslists = new ArrayList<>();
        switch(string){
            case("Popular Auto"):

                PhoneList phone_service = new PhoneList();
                PhoneList phone_sales = new PhoneList();

                phone_service.setTitle("Servicios");
                phone_service.setCarIcon(car1);
                phone_service.setUri(Uri.parse("tel:787-792-9282"));

                phone_sales.setTitle("Ventas");
                phone_sales.setCarIcon(car2);
                phone_sales.setUri(Uri.parse("tel:tel:787-724-3656"));

                phoneslists.add(phone_service);
                phoneslists.add(phone_sales);

                break;
            case("Popular Mortgage"):
                break;
            case("Security"):
                break;
        }



        return phoneslists;
    }
}
