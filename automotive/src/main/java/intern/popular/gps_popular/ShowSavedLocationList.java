package intern.popular.gps_popular;

import android.location.Location;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;


public class ShowSavedLocationList extends AppCompatActivity {

    ListView lv_savedLocations;
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_saved_location_list);

        MyApplication myApplication= (MyApplication)getApplicationContext();
        List<Location> savedLocations= myApplication.getMylocations();

        lv_savedLocations= findViewById(R.id.lv_wayPoints);
        lv_savedLocations.setAdapter(new ArrayAdapter<Location>(this, android.R.layout.simple_list_item_1, savedLocations));

    }

}