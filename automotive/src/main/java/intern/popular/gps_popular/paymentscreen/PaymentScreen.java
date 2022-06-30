package intern.popular.gps_popular.paymentscreen;

import androidx.annotation.NonNull;
import androidx.car.app.CarContext;
import androidx.car.app.Screen;
import androidx.car.app.ScreenManager;
import androidx.car.app.model.Action;
import androidx.car.app.model.CarIcon;
import androidx.car.app.model.ItemList;
import androidx.car.app.model.ListTemplate;
import androidx.car.app.model.OnClickListener;
import androidx.car.app.model.Row;
import androidx.car.app.model.Template;

import java.util.ArrayList;
import java.util.List;

import intern.popular.gps_popular.mapscreens.PlaceDetailScreen;

public class PaymentScreen extends Screen {

    public static double LumaPay = 115.50;
    public static double ClaroPay = 60.30;
    public static double LibertyPay = 94.55;

    public PaymentScreen(CarContext carContext) {
        super(carContext);
    }

    @NonNull
    @Override
    public Template onGetTemplate() {

        List<AccountsSample> builder = ItemBuilder();

        ItemList.Builder itemList = new ItemList.Builder();

        for(AccountsSample accounts: builder){
            itemList.addItem(new Row.Builder()
                    .setTitle(accounts.getAccount())
                    .addText("$" + String.format("%.2f",accounts.getAmount()))
                    .setImage(accounts.getCarIcon())
                    .setBrowsable(true)
                    .setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick() { OnClickPayment(accounts);}
                    })
                    .build());
        }
//        Row one = new Row.Builder()
//                .setTitle(luma.getAccount())
//                .addText("$" + String.format("%.2f",luma.getAmount()))
//                .setImage(luma.getCarIcon())
//                .setBrowsable(true)
//                .setOnClickListener(new OnClickListener() {
//                    @Override
//                    public void onClick() { OnClickPayment(luma);}
//                })
//                .build();
//
//        Row two = new Row.Builder()
//                .setTitle(claro.getAccount())
//                .addText("$" + String.format("%.2f",claro.getAmount()))
//                .setImage(claro.getCarIcon())
//                .setBrowsable(true)
//                .setOnClickListener(new OnClickListener() {
//                    @Override
//                    public void onClick() {
//                        OnClickPayment(claro);}
//                })
//                .build();
//
//        Row three = new Row.Builder()
//                .setTitle(liberty.getAccount())
//                .addText("$" + String.format("%.2f",liberty.getAmount()))
//                .setImage(liberty.getCarIcon())
//                .setBrowsable(true)
//                .setOnClickListener(new OnClickListener() {
//                    @Override
//                    public void onClick() {
//                        OnClickPayment(liberty);}
//                })
//                .build();
//
//        itemList.addItem(one).addItem(two).addItem(three);
        
        return new ListTemplate.Builder().setSingleList(itemList.build())
                .setHeaderAction(Action.BACK)
                .setTitle("PAGOS PENDIENTES")
                .build();

    }

    private void OnClickPayment(AccountsSample accounts) {
        getCarContext().getCarService(ScreenManager.class).push(new PaymentDetailScreen(getCarContext(), accounts));

    }

    private List<AccountsSample> ItemBuilder(){

        List<AccountsSample> result = new ArrayList<>();

        if(LumaPay!=0){
            AccountsSample luma = new AccountsSample("LUMA", LumaPay, CarIcon.APP_ICON);
            result.add(luma);
        }
        if(ClaroPay!=0){
            AccountsSample claro = new AccountsSample("Claro", ClaroPay, CarIcon.APP_ICON);
            result.add(claro);
        }
        if(LibertyPay!=0){
            AccountsSample liberty = new AccountsSample("Liberty", LibertyPay, CarIcon.APP_ICON);
            result.add(liberty);
        }

        return result;

    }

}