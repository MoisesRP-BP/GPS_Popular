package intern.popular.gps_popular;

import androidx.annotation.NonNull;
import androidx.car.app.CarContext;
import androidx.car.app.Screen;
import androidx.car.app.model.Action;
import androidx.car.app.model.CarColor;
import androidx.car.app.model.Pane;
import androidx.car.app.model.PaneTemplate;
import androidx.car.app.model.Row;
import androidx.car.app.model.Template;

public class PlaceDetailScreen extends Screen {

    public PlaceDetailScreen(CarContext carContext) {
        super(carContext);
    }

    @NonNull
    @Override
    public Template onGetTemplate() {
        Pane pane = new Pane.Builder()
                .addAction(
                        new Action.Builder()
                                .setTitle("Navigate")
                                .setBackgroundColor(CarColor.BLUE)
                                .build()
                ).addAction(
                        new Action.Builder()
                                .setTitle("Back")
                                .setBackgroundColor(CarColor.BLUE)
                                .build()
                )
                .addRow(
                        new Row.Builder()
                                .setTitle("Address")
//                                .addText(place.address)
                                .build()
                )
                .build();
        return new PaneTemplate.Builder(pane)
                .setTitle("Morovis")
                .setHeaderAction(Action.BACK)
                .build();
    }
}
