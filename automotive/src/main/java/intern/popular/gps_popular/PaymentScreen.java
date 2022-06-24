package intern.popular.gps_popular;

import androidx.annotation.NonNull;
import androidx.car.app.CarContext;
import androidx.car.app.Screen;
import androidx.car.app.model.Action;
import androidx.car.app.model.CarIcon;
import androidx.car.app.model.ItemList;
import androidx.car.app.model.ListTemplate;
import androidx.car.app.model.OnClickListener;
import androidx.car.app.model.Row;
import androidx.car.app.model.Template;

public class PaymentScreen extends Screen {


    public PaymentScreen(CarContext carContext) {
        super(carContext);
    }

    @NonNull
    @Override
    public Template onGetTemplate() {

        ItemList.Builder itemList = new ItemList.Builder();

        Row one = new Row.Builder()
                .setTitle("LUMA")
                .addText("$110.50")
                .setImage(CarIcon.APP_ICON)
                .setBrowsable(true)
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick() { test();}
                })
                .build();

        Row two = new Row.Builder()
                .setTitle("CLARO")
                .addText("$60.30")
                .setImage(CarIcon.APP_ICON)
                .setBrowsable(true)
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick() {test();}
                })
                .build();

        Row three = new Row.Builder()
                .setTitle("LIBERTY")
                .addText("$82.40")
                .setImage(CarIcon.APP_ICON)
                .setBrowsable(true)
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick() {
                        test();}
                })
                .build();

        itemList.addItem(one).addItem(two).addItem(three);
        
        return new ListTemplate.Builder().setSingleList(itemList.build())
                .setHeaderAction(Action.BACK)
                .setTitle("PAGOS PENDIENTES")
                .build();

    }

    private void test() {}

}