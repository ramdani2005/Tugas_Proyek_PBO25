package model;

public abstract class PaketTour {
    protected String namaPaket;
    protected double harga;

    public PaketTour(String namaPaket, double harga) {
        this.namaPaket = namaPaket;
        this.harga = harga;
    }

    public String getNamaPaket() {
        return namaPaket;
    }

    public abstract double hitungBiaya();
}
