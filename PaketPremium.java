package model;

public class PaketPremium extends PaketTour {

    public PaketPremium() {
        super("Paket Premium", 800000);
    }

    @Override
    public double hitungBiaya() {
        return harga;
    }
}
