package intern.popular.gps_popular.accountscreen;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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

import java.util.ArrayList;
import java.util.List;

import intern.popular.gps_popular.R;
import intern.popular.gps_popular.paymentscreen.AccountsSample;
import intern.popular.gps_popular.paymentscreen.PaymentDetailScreen;

public class VerifyScreen extends Screen {

    public static double Check_Account= 700.00;
    public static double Save_Account= 1234.56;
    public static double Card_Account= 3500.80;


    public VerifyScreen(CarContext carContext) {
        super(carContext);
    }

    @NonNull
    @Override
    public Template onGetTemplate() {
        String checkTitle= "Cheque: x0987";
        String saveTitle = "Ahorro: x1234";
        String cardTitle = "Black Dual Mastercard: x4322";

        List<CarIcon> icons = IconMaker();

        ItemList.Builder itemList = new ItemList.Builder();

        GridItem one = new GridItem.Builder()
                .setTitle(checkTitle)
                .setText("$" + String.format("%.2f", Check_Account))
                .setImage(icons.get(0))
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick() { OnClickPayment(checkTitle);}
                })
                .build();

        GridItem two = new GridItem.Builder()
                .setTitle(saveTitle)
                .setText("$" + String.format("%.2f", Save_Account))
                .setImage(icons.get(1))
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick() { OnClickPayment(saveTitle);}
                })
                .build();

        GridItem three = new GridItem.Builder()
                .setTitle(cardTitle)
                .setText("$" + String.format("%.2f", Card_Account))
                .setImage(icons.get(2))
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick() { OnClickPayment(cardTitle);}
                })
                .build();

        itemList.addItem(one).addItem(two).addItem(three);

        return new GridTemplate.Builder().setSingleList(itemList.build())
                .setHeaderAction(Action.BACK)
                .setTitle("CUENTAS DISPONIBLES")
                .build();
    }

    private void OnClickPayment(String name) {
        String[] accounts = name.split(":");

        getCarContext().getCarService(ScreenManager.class).push(new AccountDetailScreen(getCarContext(), accounts[0]));

    }

    private List<CarIcon> IconMaker() {
        List<CarIcon> list = new ArrayList<>();
        List<Bitmap> bitmaps = new ArrayList<>();

        //cheque
        bitmaps.add(BitmapFactory.decodeResource(getCarContext().getResources(), R.mipmap.cuenta_foreground));
        //ahorro
        bitmaps.add(BitmapFactory.decodeResource(getCarContext().getResources(), R.mipmap.cuenta_foreground));
        //BlackDual
        bitmaps.add(BitmapFactory.decodeResource(getCarContext().getResources(), R.mipmap.blackdual_foreground));

        for (Bitmap bit : bitmaps) {
            IconCompat iconCompat = IconCompat.createWithBitmap(bit);
            CarIcon car = new CarIcon.Builder(iconCompat).build();
            list.add(car);
        }
        return list;
    }
}
