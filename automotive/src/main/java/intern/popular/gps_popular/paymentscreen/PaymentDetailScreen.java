package intern.popular.gps_popular.paymentscreen;

import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.car.app.CarContext;
import androidx.car.app.Screen;
import androidx.car.app.ScreenManager;
import androidx.car.app.model.Action;
import androidx.car.app.model.CarColor;
import androidx.car.app.model.OnClickListener;
import androidx.car.app.model.Pane;
import androidx.car.app.model.PaneTemplate;
import androidx.car.app.model.Row;
import androidx.car.app.model.Template;

public class PaymentDetailScreen extends Screen {

    private AccountsSample account;

    public PaymentDetailScreen(CarContext carContext, AccountsSample account) {
        super(carContext);
        this.account = account;
    }

    @NonNull
    @Override
    public Template onGetTemplate() {
        Pane pane = new Pane.Builder()
                .addAction(
                        new Action.Builder()
                                .setTitle("Pay Now")
                                .setBackgroundColor(CarColor.BLUE)
                                .setOnClickListener(new OnClickListener() {
                                    @Override
                                    public void onClick() {
                                        OnPayment(account.getAccount());
                                    }
                                })
                                .build()
                ).addAction(
                        new Action.Builder()
                                .setTitle("Back")
                                .setBackgroundColor(CarColor.BLUE)
                                .setOnClickListener(new OnClickListener() {
                                    @Override
                                    public void onClick() {
                                        OnClickBack();
                                    }
                                })
                                .build()
                )
                .addRow(
                        new Row.Builder()
                                .setTitle("Payment for an amount of: " + account.getAmount())
                                .addText("Card ending in x1234")
                                .build()
                )
                .build();
        return new PaneTemplate.Builder(pane)
                .setTitle(account.getAccount())
                .setHeaderAction(Action.BACK)
                .build();
    }

    private void OnPayment(String pay) {
        double sub = 0;

        switch(pay){
            case("LUMA"):
                sub = PaymentScreen.LumaPay;
                PaymentScreen.LumaPay = 0;
                break;
            case("Claro"):
                sub = PaymentScreen.ClaroPay;
                PaymentScreen.ClaroPay = 0;
                break;
            case("Liberty"):
                sub = PaymentScreen.LibertyPay;
                PaymentScreen.LibertyPay = 0;
                break;
        }

        getCarContext().getCarService(ScreenManager.class).pop();
    }

    private void OnClickBack() {
        getCarContext().getCarService(ScreenManager.class).pop();

    }
}
