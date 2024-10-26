package by.losik.polynomial;

public class Monomial {
    private int power;
    private double value;

    public double getValue() {
        return value;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
