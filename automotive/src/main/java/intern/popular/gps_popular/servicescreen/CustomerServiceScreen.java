package intern.popular.gps_popular.servicescreen;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
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
import androidx.car.app.model.Row;
import androidx.car.app.model.Template;
import androidx.core.graphics.drawable.IconCompat;
import static android.Manifest.permission.CALL_PHONE;
import intern.popular.gps_popular.R;

public class CustomerServiceScreen extends Screen {
    public CustomerServiceScreen(CarContext carContext) {
        super(carContext);
    }

    @NonNull
    @Override
    public Template onGetTemplate() {

        Bitmap bit = BitmapFactory.decodeResource(getCarContext().getResources(),R.mipmap.mibanco_logo);
        IconCompat iconCompat = IconCompat.createWithBitmap(bit);
        CarIcon car = new CarIcon.Builder(iconCompat).build();
        ItemList.Builder list = new ItemList.Builder();
        list.addItem(new GridItem.Builder()
                        .setTitle("Hipotecas")
                        .setImage(car)
                        .setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick() {
                                Intent call = new Intent(Intent.ACTION_CALL);
                                call.setData(Uri.parse("tel:787-775-1100"));
                                call.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                                if (getCarContext().checkSelfPermission(CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                                    getCarContext().startActivity(call);
                                }
                            }
                        })
                        .build()
                )
                .addItem(new GridItem.Builder()
                        .setTitle("Prestamos de Auto")
                        .setImage(car)
                        .setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick() {
                                Intent call = new Intent(Intent.ACTION_CALL);
                                call.setData(Uri.parse("tel:787-792-9282"));
                                call.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                                if (getCarContext().checkSelfPermission(CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                                    getCarContext().startActivity(call);
                                }
                            }
                        })
                        .build()
                )
                .addItem(new GridItem.Builder()
                        .setTitle("Arrendamientos")
                        .setImage(car)
                        .setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick() {
                                Intent call = new Intent(Intent.ACTION_CALL);
                                call.setData(Uri.parse("tel:787-775-1100"));
                                call.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                                if (getCarContext().checkSelfPermission(CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                                    getCarContext().startActivity(call);
                                }
                            }
                        })
                        .build()
                )
                .addItem(new GridItem.Builder()
                        .setTitle("Next")
                        .setImage(car)
                        .build());

        return new GridTemplate.Builder().setSingleList(list.build())
                .setTitle("MiBancoApp")
                .setHeaderAction(Action.BACK)
                .build();
    }

}
