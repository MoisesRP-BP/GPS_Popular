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

import intern.popular.gps_popular.accountscreen.VerifyScreen;

public class PaymentDetailScreen extends Screen {

    private AccountsSample account;
    public static double LumaCost = 0;
    public static double ClaroCost = 0;
    public static double LibertyCost = 0;


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
                                .setTitle("Payment for an amount of: $" + String.format("%.2f", account.getAmount()))
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
                if(VerifyScreen.Check_Account>=sub){
                    LumaCost = PaymentScreen.LumaPay;
                    PaymentScreen.LumaPay = 0;
                }
                break;
            case("Claro"):
                sub = PaymentScreen.ClaroPay;
                if(VerifyScreen.Check_Account>=sub) {
                    ClaroCost = PaymentScreen.ClaroPay;
                    PaymentScreen.ClaroPay = 0;
                }
                break;
            case("Liberty"):
                sub = PaymentScreen.LibertyPay;
                if(VerifyScreen.Check_Account>=sub) {
                    LibertyCost = PaymentScreen.LibertyPay;
                    PaymentScreen.LibertyPay = 0;
                }
                break;
        }
        VerifyScreen.Check_Account= VerifyScreen.Check_Account-sub;
        getCarContext().getCarService(ScreenManager.class).pop();
    }

    private void OnClickBack() {
        getCarContext().getCarService(ScreenManager.class).pop();

    }
}
