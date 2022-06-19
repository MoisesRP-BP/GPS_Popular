package intern.popular.gps_popular;

import android.content.res.Resources;
import android.location.Location;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class LocationSample extends AppCompatActivity {
    private int id;
    private int type;
    private String name;
    private String name_en;
    private String area;
    private String phone;
    private String fax;
    private boolean hours_weekly_range;
    private String hours;
    private String address;
    private String city;

    private double lat;
    private double lon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public boolean isHours_weekly_range() {
        return hours_weekly_range;
    }

    public void setHours_weekly_range(boolean hours_weekly_range) {
        this.hours_weekly_range = hours_weekly_range;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "LocationSample{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", name_en='" + name_en + '\'' +
                ", area='" + area + '\'' +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                ", hours_weekly_range=" + hours_weekly_range +
                ", hours='" + hours + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }

    public List<Location> readLocationData() throws FileNotFoundException {

        String csvfileString = this.getApplicationInfo().dataDir + File.separatorChar + "data.csv";
        File csvfile = new File(csvfileString);

        CSVReader reader = new CSVReader( new FileReader("csvfile.getAbsolutePath()"));
        List<Location> location = new ArrayList<>();
        String[] lines = new String[0];
        int i = 0;
        try{
            //remove header
            reader.readNext();

            while((lines = reader.readNext())!= null){

                String line = lines[0];
                //Split by ',' except commas in qoutes e.g. "San Juan, Puerto Rico"
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                //Read data
                LocationSample sample = new LocationSample();

                sample.setId(Integer.parseInt(tokens[0]));
                sample.setType(Integer.parseInt(tokens[1]));
                sample.setName(tokens[2]);
                sample.setName_en(tokens[3]);
                sample.setArea(tokens[4]);
                if(tokens[5].length()>0){
                    sample.setPhone(tokens[5]);
                } else{
                    sample.setPhone("0");
                }
                if(tokens[5].length()>0){
                    sample.setFax(tokens[6]);
                } else{
                    sample.setFax("0");
                }
                sample.setHours_weekly_range(Boolean.getBoolean(tokens[7]));
                sample.setHours(tokens[8]);
                sample.setAddress(tokens[9]);
                sample.setCity(tokens[10]);
                sample.setLat(Double.parseDouble(tokens[11]));
                sample.setLon(Double.parseDouble(tokens[12]));

                Log.d("My Activity", "Just created:" + sample);

                Location newLocation = new Location(sample.getName());
                newLocation.setLatitude(sample.getLat());
                newLocation.setLongitude(sample.getLon());

                location.add(newLocation);
            }

        } catch(IOException e){
            Log.wtf("My Activity", "Error reading data file on line" + lines[i], e);
            e.printStackTrace();
        }

        return location;
    }

}
