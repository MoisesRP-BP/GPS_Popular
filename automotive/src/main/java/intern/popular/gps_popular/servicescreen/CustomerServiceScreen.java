package intern.popular.gps_popular.servicescreen;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.car.app.CarContext;
import androidx.car.app.Screen;
import androidx.car.app.ScreenManager;
import androidx.car.app.model.Action;
import androidx.car.app.model.CarIcon;
import androidx.car.app.model.GridItem;
import androidx.car.app.model.GridTemplate;
import androidx.car.app.model.ItemList;
import androidx.car.app.model.OnClickListener;
import androidx.car.app.model.Template;
import androidx.core.graphics.drawable.IconCompat;
import static android.Manifest.permission.CALL_PHONE;

import java.util.ArrayList;
import java.util.List;

import intern.popular.gps_popular.R;

public class CustomerServiceScreen extends Screen {
    public CustomerServiceScreen(CarContext carContext) {
        super(carContext);
    }

    @NonNull
    @Override
    public Template onGetTemplate() {

        String titleAuto = "Popular Auto";
        String titleMortgage = "Popular Mortgage";
        String titleTeleBanco = "Popular TeleBanco";
        String titleSeguros = "Seguros";
        String titleSecurity = "Seguridad & Fraudes";
        String titleOtros = "Otros";


        Bitmap bit1 = BitmapFactory.decodeResource(getCarContext().getResources(),R.mipmap.mibanco_logo_foreground);
        Bitmap bit2 = BitmapFactory.decodeResource(getCarContext().getResources(), R.mipmap.popular_mortgage_foreground);

        Bitmap logo1 = Bitmap.createScaledBitmap(bit1, 128, 128, true);
        Bitmap logo2 = Bitmap.createScaledBitmap(bit2, 128, 128, true);

        IconCompat iconCompat1 = IconCompat.createWithBitmap(logo1);
        IconCompat iconCompat2 = IconCompat.createWithBitmap(logo2);
        CarIcon car1 = new CarIcon.Builder(iconCompat1).build();
        CarIcon car2 = new CarIcon.Builder(iconCompat2).build();

        ItemList.Builder list = new ItemList.Builder();

        list.addItem(new GridItem.Builder()
                        .setTitle(titleTeleBanco)
                        .setImage(car1)
                        .setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick() {
                                Intent call = new Intent(Intent.ACTION_DIAL);
                                call.setData(Uri.parse("tel:787-724-3650"));
                                PermissionCheckCall(call);

                            }
                        })
                        .build()
                )
                .addItem(new GridItem.Builder()
                        .setTitle(titleMortgage)
                        .setImage(car2)
                        .setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick() {
                                OnCustomClick(ItemBuilders(titleMortgage), titleMortgage);
                            }
                        })
                        .build()
                )
                .addItem(new GridItem.Builder()
                        .setTitle(titleAuto)
                        .setImage(car1)
                        .setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick() {
                                OnCustomClick(ItemBuilders(titleAuto), titleAuto);
                            }
                        })
                        .build()
                )
                .addItem(new GridItem.Builder()
                        .setTitle(titleSeguros)
                        .setImage(car1)
                        .setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick() {
                                OnCustomClick(ItemBuilders(titleSeguros), titleSeguros);
                            }
                        })
                        .build()
                )
                .addItem(new GridItem.Builder()
                        .setTitle(titleSecurity)
                        .setImage(car1)
                        .setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick() {
                                OnCustomClick(ItemBuilders(titleSecurity), titleSecurity);
                            }
                        })
                        .build()
                )
                .addItem(new GridItem.Builder()
                        .setTitle(titleOtros)
                        .setImage(car1)
                        .setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick() {
                                OnCustomClick(ItemBuilders(titleOtros), titleOtros);
                            }
                        })
                        .build()
                );

        return new GridTemplate.Builder().setSingleList(list.build())
                .setTitle("Contactos de Servicios al Cliente")
                .setHeaderAction(Action.BACK)
                .build();
    }

    private void PermissionCheckCall(Intent intent){
        if (getCarContext().checkSelfPermission(CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            intent.setPackage("com.samsung.android.incallui");
            getCarContext().startCarApp(intent);
        }
    }

    //Create Each page Contact numbers
    private void OnCustomClick(List<PhoneList> contact, String title) {
        getCarContext().getCarService(ScreenManager.class).push(new PopularContactScreen(getCarContext(), contact, title));
    }

    private List<PhoneList> ItemBuilders(String string){

        Bitmap bit1 = BitmapFactory.decodeResource(getCarContext().getResources(),R.mipmap.mibanco_logo_foreground);
        Bitmap bit2 = BitmapFactory.decodeResource(getCarContext().getResources(), R.mipmap.popular_mortgage_foreground);

        Bitmap logo1 = Bitmap.createScaledBitmap(bit1, 256, 256, true);
        Bitmap logo2 = Bitmap.createScaledBitmap(bit2, 256, 256, true);

        IconCompat iconCompat1 = IconCompat.createWithBitmap(logo1);
        IconCompat iconCompat2 = IconCompat.createWithBitmap(logo2);
        CarIcon car1 = new CarIcon.Builder(iconCompat1).build();
        CarIcon car2 = new CarIcon.Builder(iconCompat2).build();

        List<PhoneList> phoneslists = new ArrayList<>();
        switch(string){

            //Popular Auto contacts
            case("Popular Auto"):
                PhoneList auto_service = new PhoneList();
                PhoneList auto_sales = new PhoneList();

                auto_service.setTitle("Servicios");
                auto_service.setCarIcon(car1);
                auto_service.setUri(Uri.parse("tel:787-792-9282"));

                auto_sales.setTitle("Ventas");
                auto_sales.setCarIcon(car2);
                auto_sales.setUri(Uri.parse("tel:tel:787-724-3656"));

                phoneslists.add(auto_service);
                phoneslists.add(auto_sales);

                break;

            //Popular Mortgage contacts
            case("Popular Mortgage"):
                PhoneList mortgage_buy= new PhoneList();
                PhoneList mortgage_loan = new PhoneList();

                mortgage_buy.setTitle("Compra o Refinanciamiento");
                mortgage_buy.setCarIcon(car1);
                mortgage_buy.setUri(Uri.parse("tel:787-707-7070"));

                mortgage_loan.setTitle("Hipotecas");
                mortgage_loan.setCarIcon(car2);
                mortgage_loan.setUri(Uri.parse("tel:787-775-1100"));
                phoneslists.add(mortgage_buy);
                phoneslists.add(mortgage_loan);
                break;

            //Security & Frauds contacts
            case("Seguridad & Fraudes"):
                PhoneList security_identity = new PhoneList();
                PhoneList security_lost = new PhoneList();
                PhoneList security_visa = new PhoneList();
                PhoneList security_popular = new PhoneList();

                security_identity.setTitle("Robo de Identidad");
                security_identity.setCarIcon(car1);
                security_identity.setUri(Uri.parse("tel:1-877-438-4338"));

                security_lost.setTitle("Tarjeta Perdida o Robada");
                security_lost.setCarIcon(car2);
                security_lost.setUri(Uri.parse("tel:787-758-0505"));

                security_visa.setTitle("ATH Visa Internacional");
                security_visa.setCarIcon(car1);
                security_visa.setUri(Uri.parse("tel:787-724-3650"));

                security_popular.setTitle("Popular Security");
                security_popular.setCarIcon(car1);
                security_popular.setUri(Uri.parse("tel:787-724-3657"));

                phoneslists.add(security_identity);
                phoneslists.add(security_lost);
                phoneslists.add(security_visa);
                phoneslists.add(security_popular);
                break;

            //Insurance contacts
            case("Seguros"):
                PhoneList insurance_popular = new PhoneList();
                PhoneList insurance_risk = new PhoneList();

                insurance_popular.setTitle("Popular Insurance");
                insurance_popular.setCarIcon(car1);
                insurance_popular.setUri(Uri.parse("787-706-4111"));

                insurance_risk.setTitle("Popular Risk Services");
                insurance_risk.setCarIcon(car2);
                insurance_risk.setUri(Uri.parse("787-731-6900"));

                phoneslists.add(insurance_popular);
                phoneslists.add(insurance_risk);
                break;

            //Other contact numbers
            case("Otros"):
                PhoneList other_lease = new PhoneList();
                PhoneList other_credit = new PhoneList();
                PhoneList other_loan = new PhoneList();
                PhoneList other_bank = new PhoneList();
                PhoneList other_trust = new PhoneList();

                other_lease.setTitle("Arrendamiento");
                other_lease.setCarIcon(car1);
                other_lease.setUri(Uri.parse("787-522-4945"));

                other_loan.setTitle("Prestamos Personales");
                other_loan.setCarIcon(car1);
                other_loan.setUri(Uri.parse("787-522-4945"));

                other_credit.setTitle("Tarjeta de Credito");
                other_credit.setCarIcon(car1);
                other_credit.setUri(Uri.parse("787-522-4945"));

                other_bank.setTitle("Banca Comercial");
                other_bank.setCarIcon(car1);
                other_bank.setUri(Uri.parse("787-756-3939"));

                other_trust.setTitle("Fideicomiso");
                other_trust.setCarIcon(car1);
                other_trust.setUri(Uri.parse("787-724-3655"));

                phoneslists.add(other_lease);
                phoneslists.add(other_loan);
                phoneslists.add(other_credit);
                phoneslists.add(other_bank);
                phoneslists.add(other_trust);
                break;

        }

        return phoneslists;
    }
}
