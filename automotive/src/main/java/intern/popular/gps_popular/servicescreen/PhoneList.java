package intern.popular.gps_popular.servicescreen;

import android.net.Uri;

import androidx.car.app.model.CarIcon;

public class PhoneList {

    private String title;
    private CarIcon carIcon;
    private Uri uri;

    public PhoneList() {
    }

    public CarIcon getCarIcon() {
        return carIcon;
    }

    public void setCarIcon(CarIcon carIcon) {
        this.carIcon = carIcon;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "PhoneList{" +
                "title='" + title + '\'' +
                ", carIcon=" + carIcon +
                ", uri=" + uri +
                '}';
    }
}
