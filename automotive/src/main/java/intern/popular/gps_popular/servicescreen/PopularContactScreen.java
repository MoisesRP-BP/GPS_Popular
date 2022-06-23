package intern.popular.gps_popular.servicescreen;

import static android.Manifest.permission.CALL_PHONE;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.car.app.CarContext;
import androidx.car.app.Screen;
import androidx.car.app.model.Action;
import androidx.car.app.model.GridItem;
import androidx.car.app.model.GridTemplate;
import androidx.car.app.model.ItemList;
import androidx.car.app.model.OnClickListener;
import androidx.car.app.model.Row;
import androidx.car.app.model.Template;

import java.util.List;

public class PopularContactScreen extends Screen {

    private List<PhoneList> lists;
    private String Ptitle;

    protected PopularContactScreen(@NonNull CarContext carContext, List<PhoneList> lists, String Ptitle) {
        super(carContext);
        this.lists = lists;
        this.Ptitle = Ptitle;
    }

    @NonNull
    @Override
    public Template onGetTemplate() {
        ItemList.Builder list = new ItemList.Builder();
        for(PhoneList element: lists){
            list.addItem(new GridItem.Builder()
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

        return new GridTemplate.Builder().setSingleList(list.build())
                .setTitle(Ptitle)
                .setHeaderAction(Action.BACK)
                .build();
    }

    private void PermissionCheckCall(Intent intent){
        if (getCarContext().checkSelfPermission(CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            intent.setPackage("com.samsung.android.incallui");
            getCarContext().startCarApp(intent);
        }
    }
}
