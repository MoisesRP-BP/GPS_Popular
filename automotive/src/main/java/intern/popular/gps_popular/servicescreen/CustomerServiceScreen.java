package intern.popular.gps_popular.servicescreen;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.widget.Button;

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
                        .setTitle("Hipotecas")
                        .setImage(car2)
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
                        .setImage(car1)
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
                        .setImage(car1)
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
                        .setTitle("Prestamos Personales")
                        .setImage(car1)
                        .setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick() {
                                Intent call = new Intent(Intent.ACTION_CALL);
                                call.setData(Uri.parse("tel:787-522-4945"));
                                call.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                                if (getCarContext().checkSelfPermission(CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                                    getCarContext().startActivity(call);
                                }
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
                        .setTitle("Arrendamientos")
                        .setImage(car1)
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
        ;

        return new GridTemplate.Builder().setSingleList(list.build())
                .setTitle("MiBancoApp")
                .setHeaderAction(Action.BACK)
                .build();
    }

}
