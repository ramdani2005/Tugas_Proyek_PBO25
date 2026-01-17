package model;

public class Peserta {
    private String nama;
    private String nomorHp;
    private String alamat;

    public Peserta(String nama, String nomorHp, String alamat) {
        this.nama = nama;
        this.nomorHp = nomorHp;
        this.alamat = alamat;
    }

    public boolean nomorHpValid() {
        return nomorHp != null && nomorHp.matches("\\d{10,13}");
    }

    public String getData() {
        return "Nama: " + nama + " | HP: " + nomorHp + " | Alamat: " + alamat;
    }
}
