package intern.popular.gps_popular.servicescreen;

import static android.Manifest.permission.CALL_PHONE;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.car.app.CarContext;
import androidx.car.app.Screen;
import androidx.car.app.ScreenManager;
import androidx.car.app.model.Action;
import androidx.car.app.model.CarIcon;
import androidx.car.app.model.GridItem;
import androidx.car.app.model.GridTemplate;
import androidx.car.app.model.ItemList;
import androidx.car.app.model.ListTemplate;
import androidx.car.app.model.OnClickListener;
import androidx.car.app.model.Row;
import androidx.car.app.model.Template;
import androidx.core.graphics.drawable.IconCompat;

import java.util.ArrayList;
import java.util.List;

import intern.popular.gps_popular.R;

public class PopularContactScreen extends Screen {

    private String Ptitle;

    protected PopularContactScreen(@NonNull CarContext carContext, String Ptitle) {
        super(carContext);
        this.Ptitle = Ptitle;
    }

    @NonNull
    @Override
    public Template onGetTemplate() {
        ItemList.Builder list = new ItemList.Builder();
        List<PhoneList> lists = ItemBuilders(Ptitle);
        for(PhoneList element: lists){
//            list.addItem(new GridItem.Builder()
            list.addItem(new Row.Builder()
                    .setTitle(element.getTitle())
                    .setImage(element.getCarIcon())
                    .setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick() {
                            Intent call = new Intent(Intent.ACTION_DIAL);
                            call.setData(element.getUri());
                            PermissionCheckCall(call);

                        }
                    })
                    .build()
            )  .build();
        }

//        return new GridTemplate.Builder().setSingleList(list.build())
//                .setTitle("Contactos para " + Ptitle)
//                .setHeaderAction(Action.BACK)
//                .build();

        return new ListTemplate.Builder().setSingleList(list.build())
                .setTitle("Contactos para " + Ptitle)
                .setHeaderAction(Action.BACK)
                .build();
    }

    private void PermissionCheckCall(Intent intent){
        if (getCarContext().checkSelfPermission(CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            intent.setPackage("com.samsung.android.incallui");
            getCarContext().startCarApp(intent);
        }
    }

    private List<PhoneList> ItemBuilders(String string){

        String AutoTservicios = "Servicios";
        String AutoTventas = "Ventas";
        String MortTcompras = "Compras o Refinanciamiento";
        String MortThipotecas= "Hipotecas";
        String SecTRobo = "Robo de Identidad";
        String SecTtarjeta = "Tarjeta Perdida o Robada";
        String SecTvisa = "ATH Visa Internacional";
        String SecTpopular = "Popular Security";
        String SegurosTinsurance = "Popular Insurance";
        String SegurosTrisk = "Popular Risk Services";
        String OtherTarrendamiento = "Arrendamiento";
        String OtherTprestamos = "Prestamos Personales";
        String OtherTcredito = "Tarjetas de Creditos";
        String OtherTbanca = "Banca Comercial";
        String OtherTfideicomiso = "Fideicomiso";

        List<CarIcon> icons = IconMaker();
        List<PhoneList> phoneslists = new ArrayList<>();

        switch(string){

            //Popular Auto contacts
            case("Popular Auto"):
                PhoneList auto_service = new PhoneList();
                PhoneList auto_sales = new PhoneList();

                auto_service.setTitle(AutoTservicios);
                auto_service.setCarIcon(icons.get(0));
                auto_service.setUri(Uri.parse("tel:787-792-9282"));

                auto_sales.setTitle(AutoTventas);
                auto_sales.setCarIcon(icons.get(1));
                auto_sales.setUri(Uri.parse("tel:787-724-3656"));

                phoneslists.add(auto_service);
                phoneslists.add(auto_sales);

                break;

            //Popular Mortgage contacts
            case("Popular Mortgage"):
                PhoneList mortgage_buy= new PhoneList();
                PhoneList mortgage_loan = new PhoneList();

                mortgage_buy.setTitle(MortTcompras);
                mortgage_buy.setCarIcon(icons.get(2));
                mortgage_buy.setUri(Uri.parse("tel:787-707-7070"));

                mortgage_loan.setTitle(MortThipotecas);
                mortgage_loan.setCarIcon(icons.get(3));
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

                security_identity.setTitle(SecTRobo);
                security_identity.setCarIcon(icons.get(4));
                security_identity.setUri(Uri.parse("tel:1-877-438-4338"));

                security_lost.setTitle(SecTtarjeta);
                security_lost.setCarIcon(icons.get(5));
                security_lost.setUri(Uri.parse("tel:787-758-0505"));

                security_visa.setTitle(SecTvisa);
                security_visa.setCarIcon(icons.get(6));
                security_visa.setUri(Uri.parse("tel:787-724-3650"));

                security_popular.setTitle(SecTpopular);
                security_popular.setCarIcon(icons.get(7));
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

                insurance_popular.setTitle(SegurosTinsurance);
                insurance_popular.setCarIcon(icons.get(8));
                insurance_popular.setUri(Uri.parse("tel:787-706-4111"));

                insurance_risk.setTitle(SegurosTrisk);
                insurance_risk.setCarIcon(icons.get(9));
                insurance_risk.setUri(Uri.parse("tel:787-731-6900"));

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

                other_lease.setTitle(OtherTarrendamiento);
                other_lease.setCarIcon(icons.get(10));
                other_lease.setUri(Uri.parse("tel:787-522-4945"));

                other_loan.setTitle(OtherTprestamos);
                other_loan.setCarIcon(icons.get(11));
                other_loan.setUri(Uri.parse("tel:787-522-4945"));

                other_credit.setTitle(OtherTcredito);
                other_credit.setCarIcon(icons.get(12));
                other_credit.setUri(Uri.parse("tel:787-522-4945"));

                other_bank.setTitle(OtherTbanca);
                other_bank.setCarIcon(icons.get(13));
                other_bank.setUri(Uri.parse("tel:787-756-3939"));

                other_trust.setTitle(OtherTfideicomiso);
                other_trust.setCarIcon(icons.get(14));
                other_trust.setUri(Uri.parse("tel:787-724-3655"));

                phoneslists.add(other_lease);
                phoneslists.add(other_loan);
                phoneslists.add(other_credit);
                phoneslists.add(other_bank);
                phoneslists.add(other_trust);
                break;

        }

        return phoneslists;
    }

    private List<CarIcon> IconMaker(){
        List<CarIcon> list = new ArrayList<>();
        List<Bitmap> bitmaps = new ArrayList<>();

        //Logo Popular Auto: Servicios
        bitmaps.add(BitmapFactory.decodeResource(getCarContext().getResources(), R.mipmap.popular_auto_foreground));
        //Logo Popular Auto: Ventas
        bitmaps.add(BitmapFactory.decodeResource(getCarContext().getResources(), R.mipmap.popular_auto_foreground));
        //Logo Popular Mortgage: Compras
        bitmaps.add(BitmapFactory.decodeResource(getCarContext().getResources(), R.mipmap.popular_mortgage_foreground));
        //Logo Popular Mortgage: Hipotecas
        bitmaps.add(BitmapFactory.decodeResource(getCarContext().getResources(), R.mipmap.popular_mortgage_foreground));
        //Logo Seguridad & Fraude: Robo de identidad
        bitmaps.add(BitmapFactory.decodeResource(getCarContext().getResources(),R.mipmap.lock_indentity_foreground));
        //Logo Seguridad & Fraude: Tarjeta Perdida
        bitmaps.add(BitmapFactory.decodeResource(getCarContext().getResources(), R.mipmap.visa_logo_foreground));
        //Logo Seguridad & Fraude: ATH Visa Internacional
        bitmaps.add(BitmapFactory.decodeResource(getCarContext().getResources(),R.mipmap.visa_logo_foreground));
        //Logo Seguridad & Fraude: Security Popular
        bitmaps.add(BitmapFactory.decodeResource(getCarContext().getResources(), R.mipmap.lock_indentity_foreground));
        //Logo Seguros: Insurance
        bitmaps.add(BitmapFactory.decodeResource(getCarContext().getResources(),R.mipmap.popular_insurance_foreground));
        //Logo Seguros: Risks
        bitmaps.add(BitmapFactory.decodeResource(getCarContext().getResources(), R.mipmap.popular_insurance_foreground));
        //Logo Otros: Arrendamiento
        bitmaps.add(BitmapFactory.decodeResource(getCarContext().getResources(), R.mipmap.popular_mortgage_foreground));
        //Logo Otros: Prestamos Personales
        bitmaps.add(BitmapFactory.decodeResource(getCarContext().getResources(),R.mipmap.mibanco_logo_foreground));
        //Logo Otros: Tarjetas de Creditos
        bitmaps.add(BitmapFactory.decodeResource(getCarContext().getResources(),R.mipmap.visa_logo_foreground));
        //Logo Otros: Banca Comercial
        bitmaps.add(BitmapFactory.decodeResource(getCarContext().getResources(),R.mipmap.mibanco_logo_foreground));
        //Logo Otros: Fideicomiso
        bitmaps.add(BitmapFactory.decodeResource(getCarContext().getResources(),R.mipmap.popular_insurance_foreground));

        for(Bitmap bit: bitmaps){
            Bitmap logo = Bitmap.createScaledBitmap(bit, 256, 256, true);
            IconCompat iconCompat = IconCompat.createWithBitmap(logo);
            CarIcon car = new CarIcon.Builder(iconCompat).build();
            list.add(car);
        }

        return list;
    }


}
