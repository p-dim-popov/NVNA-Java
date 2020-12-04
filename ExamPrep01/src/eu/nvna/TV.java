package eu.nvna;

public class TV implements Comparable<TV>{
    private String brand = "";
    private int inches = 0;

    public TV(){}

    public TV(String brand, int inches) {
        this.brand = brand;
        this.inches = inches;
    }

    public TV(int inches) {
        this("", inches);
    }

    public TV(TV tv) {
        this(tv.brand, tv.inches);
    }

    public int getInches() {
        return this.inches;
    }

    public String getBrand(){
        return this.brand;
    }


    public void setInches(int inches) {
        this.inches = inches;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return String.format("%s,%d", this.brand, this.inches);
    }

    @Override
    public int compareTo(TV o) {
        return Integer.compare(this.inches, o.inches);
    }
}
