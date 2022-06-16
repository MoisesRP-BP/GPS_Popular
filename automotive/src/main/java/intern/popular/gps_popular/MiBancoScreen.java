package intern.popular.gps_popular;

import androidx.annotation.NonNull;
import androidx.car.app.CarContext;
import androidx.car.app.Screen;
import androidx.car.app.model.Action;
import androidx.car.app.model.Pane;
import androidx.car.app.model.PaneTemplate;
import androidx.car.app.model.Row;
import androidx.car.app.model.Template;

public class MiBancoScreen extends Screen {
    public MiBancoScreen(CarContext carContext) {
        super(carContext);
    }

//    @NonNull
//    @Override
//    public Template onGetTemplate() {
//        Row row = new Row.Builder().setTitle("Hello AA World:").addText("Example Test").build();
//        return new PaneTemplate.Builder(new Pane.Builder().addRow(row).build()).setTitle("AA Hello World").build();
//    }
//
        @NonNull
        @Override
        public Template onGetTemplate() {
            Row row = new Row.Builder().setTitle("Hello world!").build();
            Pane pane = new Pane.Builder().addRow(row).build();
            return new PaneTemplate.Builder(pane)
                    .setHeaderAction(Action.APP_ICON)
                    .build();
        }

}
