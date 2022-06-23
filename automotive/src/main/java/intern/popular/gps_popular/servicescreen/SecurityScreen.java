package intern.popular.gps_popular.servicescreen;

import static android.Manifest.permission.CALL_PHONE;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.car.app.CarContext;
import androidx.car.app.Screen;
import androidx.car.app.model.Action;
import androidx.car.app.model.CarIcon;
import androidx.car.app.model.GridItem;
import androidx.car.app.model.GridTemplate;
import androidx.car.app.model.ItemList;
import androidx.car.app.model.OnClickListener;
import androidx.car.app.model.Template;
import androidx.core.graphics.drawable.IconCompat;

import intern.popular.gps_popular.R;

public class SecurityScreen extends Screen {

    protected SecurityScreen(@NonNull CarContext carContext) {
        super(carContext);
    }

    @NonNull
    @Override
    public Template onGetTemplate() {

        //Create bitmap image
        Bitmap bit_identity = BitmapFactory.decodeResource(getCarContext().getResources(), R.mipmap.lock_indentity_foreground);
        Bitmap bit_card = BitmapFactory.decodeResource(getCarContext().getResources(), R.mipmap.popular_mortgage_foreground);
        Bitmap bit_visa = BitmapFactory.decodeResource(getCarContext().getResources(), R.mipmap.visa_internacional_foreground);

        //Scale Bitmap
        Bitmap logo1 = Bitmap.createScaledBitmap(bit_identity, 256, 256, true);
        Bitmap logo2 = Bitmap.createScaledBitmap(bit_card, 256, 256, true);
        Bitmap logo3 = Bitmap.createScaledBitmap(bit_visa, 256, 256, true);


        //Create Icon with Bitmap
        IconCompat icon_identity = IconCompat.createWithBitmap(logo1);
        IconCompat icon_card = IconCompat.createWithBitmap(logo2);
        IconCompat icon_visa = IconCompat.createWithBitmap(logo3);

        //Turn Icon into a CarIcon
        CarIcon car_identity = new CarIcon.Builder(icon_identity).build();
        CarIcon car_card = new CarIcon.Builder(icon_card).build();
        CarIcon car_visa = new CarIcon.Builder(icon_visa).build();

        ItemList.Builder list = new ItemList.Builder();

        list.addItem(new GridItem.Builder()
                        .setTitle("Robo de Identidad")
                        .setImage(car_identity)
                        .setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick() {
                                Intent call = new Intent(Intent.ACTION_DIAL);
                                call.setData(Uri.parse("tel:1-877-438-4338"));
                                PermissionCheckCall(call);

                            }
                        })
                        .build()
                )
                .addItem(new GridItem.Builder()
                        .setTitle("Tarjeta Perdida")
                        .setImage(car_card)
                        .setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick() {
                                Intent call = new Intent(Intent.ACTION_DIAL);
                                call.setData(Uri.parse("tel:787-758-0505"));
                                PermissionCheckCall(call);
                            }
                        })
                        .build()
                )
                .addItem(new GridItem.Builder()
                        .setTitle("Visa Internacional")
                        .setImage(car_visa)
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
                        .setTitle("Popular Security")
                        .setImage(car_identity)
                        .setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick() {
                                Intent call = new Intent(Intent.ACTION_DIAL);
                                call.setData(Uri.parse("tel:787-724-3657"));
                                PermissionCheckCall(call);
                            }
                        })
                        .build()
                )
                .build();

        return new GridTemplate.Builder().setSingleList(list.build())
                .setTitle("Contactos para Seguridad & Fraude")
                .setHeaderAction(Action.BACK)
                .build();
    }

    private void PermissionCheckCall(Intent intent){
        if (getCarContext().checkSelfPermission(CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            intent.setPackage("com.samsung.android.incallui");
            getCarContext().startCarApp(intent);
        }

    }
}
