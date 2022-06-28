package intern.popular.gps_popular.accountscreen;

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
import androidx.core.graphics.drawable.IconCompat;

import java.util.ArrayList;
import java.util.List;
import intern.popular.gps_popular.servicescreen.PhoneList;

public class AccountDetailScreen extends Screen {

    private String card;

    protected AccountDetailScreen(@NonNull CarContext carContext, String card) {
        super(carContext);

        this.card = card;
    }

    @NonNull
    @Override
    public Template onGetTemplate() {
        ItemList.Builder list = new ItemList.Builder();
        List<List<String>> lists = ItemBuilders(card);

        for(List<String> element: lists){
            list.addItem(new Row.Builder()
                    .setTitle(element.get(0))
                            .addText(element.get(1))
                    .build()
            )  .build();
        }


        return new ListTemplate.Builder().setSingleList(list.build())
                .setTitle("Transferencias de las ultimas 24 horas de: " + card)
                .setHeaderAction(Action.BACK)
                .build();
    }

    private List<List<String>> ItemBuilders(String string){
        List<List<String>> results = new ArrayList<>();

        switch(string){

            //Popular Auto contacts
            case("Cheque"):
                List<String> cheque1 = new ArrayList<>();
                List<String> cheque2 = new ArrayList<>();
                List<String> cheque3 = new ArrayList<>();

                cheque1.add("Spotify USA");
                cheque1.add("-$5.56");

                cheque2.add("SUPERMERCADO");
                cheque2.add("-$210.45");

                cheque3.add("BANCO POPULAR DE PAYROLL");
                cheque3.add("$997.50");

                results.add(cheque1);
                results.add(cheque2);
                results.add(cheque3);

                break;

            //Popular Mortgage contacts
            case("Ahorro"):
                List<String> cheque4 = new ArrayList<>();
                List<String> cheque5 = new ArrayList<>();
                List<String> cheque6 = new ArrayList<>();

                cheque4.add("Spotify USA");
                cheque4.add("-$5.56");

                cheque5.add("SUPERMERCADO");
                cheque5.add("-$210.45");

                cheque6.add("BANCO POPULAR DE PAYROLL");
                cheque6.add("$997.50");

                results.add(cheque4);
                results.add(cheque5);
                results.add(cheque6);

                break;
        }

        return results;
    }

}
