package intern.popular.gps_popular;

import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.car.app.CarContext;
import androidx.car.app.Screen;
import androidx.car.app.model.Action;
import androidx.car.app.model.CarIcon;
import androidx.car.app.model.GridItem;
import androidx.car.app.model.GridTemplate;
import androidx.car.app.model.Item;
import androidx.car.app.model.ItemList;
import androidx.car.app.model.Pane;
import androidx.car.app.model.PaneTemplate;
import androidx.car.app.model.Row;
import androidx.car.app.model.Template;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

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
            Row a = new Row.Builder().setTitle("Mai frend").build();
//            Pane pane = new Pane.Builder().addRow(row).build();
            Pane pane = new Pane.Builder().addRow(row).addRow(a).build();

            ItemList rows = new ItemList.Builder().addItem(row).addItem(a).build();

            return new GridTemplate.Builder().setSingleList(rows).setHeaderAction(Action.APP_ICON).build();

//            return new PaneTemplate.Builder(pane)
//                    .setHeaderAction(Action.APP_ICON)
//                    .build();
//            Item a = new
//            ItemList list = new ItemList.Builder().addItem("ATM & Sucursales").build();

//            Item item;
//
//
//            Button button1, button2, button3, button4;
//

//
//            GridTemplate grid = new GridTemplate.Builder().setSingleList(rows);
//
//            return new GridTemplate.Builder(grid)
//                    .setHeaderAction(Action.APP_ICON)
//                    .build();

//            setContentView(R.layout.activity_main);
        }

}
