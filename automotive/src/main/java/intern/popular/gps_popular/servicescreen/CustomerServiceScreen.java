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
        String titleTeleBanco = "TeleBanco";
        String titleSeguros = "Seguros";
        String titleSecurity = "Seguridad & Fraudes";
        String titleOtros = "Otros";


        List<CarIcon> icons = IconMaker();
        ItemList.Builder list = new ItemList.Builder();

        list.addItem(new GridItem.Builder()
                        .setTitle(titleTeleBanco)
                        .setImage(icons.get(0))
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
                        .setImage(icons.get(1))
                        .setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick() {
                                OnCustomClick(titleMortgage);
                            }
                        })
                        .build()
                )
                .addItem(new GridItem.Builder()
                        .setTitle(titleAuto)
                        .setImage(icons.get(2))
                        .setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick() {
                                OnCustomClick(titleAuto);
                            }
                        })
                        .build()
                )
                .addItem(new GridItem.Builder()
                        .setTitle(titleSeguros)
                        .setImage(icons.get(3))
                        .setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick() {
                                OnCustomClick(titleSeguros);
                            }
                        })
                        .build()
                )
                .addItem(new GridItem.Builder()
                        .setTitle(titleSecurity)
                        .setImage(icons.get(4))
                        .setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick() {
                                OnCustomClick(titleSecurity);
                            }
                        })
                        .build()
                )
                .addItem(new GridItem.Builder()
                        .setTitle(titleOtros)
                        .setImage(icons.get(5))
                        .setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick() {
                                OnCustomClick(titleOtros);
                            }
                        })
                        .build()
                );

        return new GridTemplate.Builder().setSingleList(list.build())
                .setTitle("Contactos de Servicios al Cliente")
                .setHeaderAction(Action.BACK)
                .build();
    }

    private void PermissionCheckCall(Intent intent){
        if (getCarContext().checkSelfPermission(CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            intent.setPackage("com.samsung.android.incallui");
            getCarContext().startCarApp(intent);
        }
    }

    //Create Each page Contact numbers
    private void OnCustomClick(String title) {
        getCarContext().getCarService(ScreenManager.class).push(new PopularContactScreen(getCarContext(), title));
    }

    private List<CarIcon> IconMaker(){
        List<CarIcon> list = new ArrayList<>();
        List<Bitmap> bitmaps = new ArrayList<>();

        //Logo TeleBanco
        bitmaps.add(BitmapFactory.decodeResource(getCarContext().getResources(),R.mipmap.telebanco_foreground));
        //Logo Popular Mortgage
        bitmaps.add(BitmapFactory.decodeResource(getCarContext().getResources(), R.mipmap.popularmortgage_foreground));
        //Logo Popular Auto
        bitmaps.add(BitmapFactory.decodeResource(getCarContext().getResources(),R.mipmap.popularauto_foreground));
        //Logo Popular Seguros
        bitmaps.add(BitmapFactory.decodeResource(getCarContext().getResources(),R.mipmap.popularseguros_foreground));
        //Logo Popular Security
        bitmaps.add(BitmapFactory.decodeResource(getCarContext().getResources(), R.mipmap.popularsecurity_foreground));
        //Logo Others
        bitmaps.add(BitmapFactory.decodeResource(getCarContext().getResources(),R.mipmap.otherslogos_foreground));

        for(Bitmap bit: bitmaps){
            Bitmap logo = Bitmap.createScaledBitmap(bit, 256, 256, true);
            IconCompat iconCompat = IconCompat.createWithBitmap(logo);
            CarIcon car = new CarIcon.Builder(iconCompat).build();
            list.add(car);
        }

        return list;
    }
}
