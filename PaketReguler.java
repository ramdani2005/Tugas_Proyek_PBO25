package model;

public class PaketReguler extends PaketTour {

    public PaketReguler() {
        super("Paket Reguler", 500000);
    }

    @Override
    public double hitungBiaya() {
        return harga;
    }
}
