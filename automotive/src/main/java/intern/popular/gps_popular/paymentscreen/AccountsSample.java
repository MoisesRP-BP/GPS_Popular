package intern.popular.gps_popular.paymentscreen;

import androidx.car.app.model.CarIcon;

public class AccountsSample {

    public AccountsSample(String account, double amount, CarIcon carIcon) {
        this.account = account;
        this.amount = amount;
        this.carIcon = carIcon;
    }

    private String account;
    private double amount;
    private CarIcon carIcon;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public CarIcon getCarIcon() {
        return carIcon;
    }

    public void setCarIcon(CarIcon carIcon) {
        this.carIcon = carIcon;
    }
}
