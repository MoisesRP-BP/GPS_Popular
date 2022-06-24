package intern.popular.gps_popular;

import androidx.annotation.NonNull;
import androidx.car.app.CarContext;
import androidx.car.app.Screen;
import androidx.car.app.model.Action;
import androidx.car.app.model.CarIcon;
import androidx.car.app.model.GridItem;
import androidx.car.app.model.GridTemplate;
import androidx.car.app.model.ItemList;
import androidx.car.app.model.Template;

public class VerifyScreen extends Screen {


    public VerifyScreen(CarContext carContext) {
        super(carContext);
    }

    @NonNull
    @Override
    public Template onGetTemplate() {

        ItemList.Builder itemList = new ItemList.Builder();

        GridItem one = new GridItem.Builder()
                .setTitle("CHEQUE")
                .setText("x0987")
                .setText("$700.00")
                .setImage(CarIcon.APP_ICON)
                .build();

        GridItem two = new GridItem.Builder()
                .setTitle("AHORRO")
                .setText("x1234")
                .setText("$1,234.56")
                .setImage(CarIcon.APP_ICON)
                .build();

        GridItem three = new GridItem.Builder()
                .setTitle("Black Dual Mastercard")
                .setText("x4322")
                .setText("$2,000.00")
                .setImage(CarIcon.APP_ICON)
                .build();

        itemList.addItem(one).addItem(two).addItem(three);

        return new GridTemplate.Builder().setSingleList(itemList.build())
                .setHeaderAction(Action.BACK)
                .setTitle("CUENTAS DISPONIBLES")
                .build();
    }
}
