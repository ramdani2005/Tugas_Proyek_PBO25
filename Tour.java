package model;

import java.util.ArrayList;

public class Tour {
    private PaketTour paket;
    private ArrayList<Peserta> pesertaList;

    public int getJumlahPeserta() {
        return pesertaList.size();
    }

    public void setPaket(PaketTour paket) {
        this.paket = paket;
    }
    public Tour(PaketTour paket) {
        this.paket = paket;
        this.pesertaList = new ArrayList<>();
    }

    public boolean tambahPeserta(Peserta p) {
        if (!p.nomorHpValid()) {
            return false;
        }
        pesertaList.add(p);
        return true;
    }
    public boolean hapusPeserta(int nomor) {
        if (nomor < 1 || nomor > pesertaList.size()) {
            return false;
        }
        pesertaList.remove(nomor - 1);
        return true;
    }


    public String tampilkanPeserta() {
        if (pesertaList.isEmpty()) {
            return "Belum ada peserta.";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pesertaList.size(); i++) {
            sb.append((i + 1)).append(". ")
                    .append(pesertaList.get(i).getData())
                    .append("\n");
        }
        return sb.toString();
    }

    public double hitungTotalBiaya() {
        return paket.hitungBiaya() * pesertaList.size();
    }

    public String infoTour() {
        return "Paket: " + paket.getNamaPaket() +
                "\nBiaya per peserta: Rp " + paket.hitungBiaya() +
                "\nJumlah peserta: " + pesertaList.size() +
                "\nTotal biaya: Rp " + hitungTotalBiaya();
    }

}
