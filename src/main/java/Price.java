import java.io.Serializable;

/**
 * Created by Albin on 2017-11-16.
 */
public class Price implements Serializable {
    private double childPrice;
    private double adultPrice;
    private double seniorPrice;
    private double threeD;
    private double platinum;
    private double wkendHol;
    private double GST;
    public Price(double childPrice, double adultPrice, double seniorPrice, double threeD, double platinum, double wkendHol, double GST){
        this.childPrice = childPrice;
        this.adultPrice = adultPrice;
        this.platinum = platinum;
        this.threeD = threeD;
        this.seniorPrice = seniorPrice;
        this.wkendHol = wkendHol;
        this.GST = GST;
    }

    public double getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(double childPrice) {
        this.childPrice = childPrice;
    }

    public double getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(double adultPrice) {
        this.adultPrice = adultPrice;
    }

    public double getSeniorPrice() {
        return seniorPrice;
    }

    public void setSeniorPrice(double seniorPrice) {
        this.seniorPrice = seniorPrice;
    }

    public double getThreeD() {
        return threeD;
    }

    public void setThreeD(double threeD) {
        this.threeD = threeD;
    }

    public double getPlatinum() {
        return platinum;
    }

    public void setPlatinum(double platinum) {
        this.platinum = platinum;
    }

    public double getWkendHol() {
        return wkendHol;
    }

    public void setWkendHol(double wkendHol) {
        this.wkendHol = wkendHol;
    }

    public double getGST() {
        return GST;
    }

    public void setGST(double GST) {
        this.GST = GST;
    }
}
