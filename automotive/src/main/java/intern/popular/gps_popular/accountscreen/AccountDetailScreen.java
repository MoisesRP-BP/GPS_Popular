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

import intern.popular.gps_popular.paymentscreen.PaymentDetailScreen;
import intern.popular.gps_popular.paymentscreen.PaymentScreen;
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

            //Transacciones Cheque
            case("Cheque"):
                List<String> claropay = new ArrayList<>();
                List<String> lumapay = new ArrayList<>();
                List<String> libertypay = new ArrayList<>();

                List<String> cheque1 = new ArrayList<>();
                List<String> cheque2 = new ArrayList<>();
                List<String> cheque3 = new ArrayList<>();

                if(PaymentScreen.LibertyPay==0){
                    libertypay.add("LIBERTY CABLEVISION");
                    libertypay.add("-$" + String.format("%.2f", PaymentDetailScreen.LibertyCost));
                    results.add(libertypay);
                }

                if(PaymentScreen.ClaroPay==0){
                    claropay.add("MI CLARO PR");
                    claropay.add("-$" + String.format("%.2f",PaymentDetailScreen.ClaroCost));
                    results.add(claropay);
                }

                if(PaymentScreen.LumaPay==0){
                    lumapay.add("AEE-PREPA");
                    lumapay.add("-$" + String.format("%.2f", PaymentDetailScreen.LumaCost));
                    results.add(lumapay);
                }

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

            //Transacciones Ahorro
            case("Ahorro"):
                List<String> save1 = new ArrayList<>();
                List<String> save2 = new ArrayList<>();

                save1.add("INTERES PAGADOS");
                save1.add("$0.52");

                save2.add("ATH WITHDRAWAL");
                save2.add("-$450.00");

                results.add(save1);
                results.add(save2);

                break;
            //Transacciones tarjeta
            case("Black Dual Mastercard"):
                List<String> card1 = new ArrayList<>();
                List<String> card2 = new ArrayList<>();

                card1.add("Spotify USA");
                card1.add("-$5.56");

                card2.add("SUPERMERCADO");
                card2.add("-$210.45");


                results.add(card1);
                results.add(card2);

                break;
        }

        return results;
    }

}
