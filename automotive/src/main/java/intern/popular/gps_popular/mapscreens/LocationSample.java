package intern.popular.gps_popular.mapscreens;

public class LocationSample {
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
    private double distance;

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


    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
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
                ", distance=" + distance +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }


}
