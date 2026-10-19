package com.mycompany.wedplanmanager;

public abstract class PaketPernikahan {

    private String namaPaket;
    private String kodePaket;
    private double hargaDasar;

    private static int totalPaket = 0;

    public PaketPernikahan(
            String namaPaket,
            String kodePaket,
            double hargaDasar) {

        this.namaPaket = namaPaket;
        this.kodePaket = kodePaket;
        this.hargaDasar = hargaDasar;
        totalPaket++;
    }

    public String getNamaPaket() {
        return namaPaket;
    }

    public void setNamaPaket(String namaPaket) {
        this.namaPaket = namaPaket;
    }

    public String getKodePaket() {
        return kodePaket;
    }

    public void setKodePaket(String kodePaket) {
        this.kodePaket = kodePaket;
    }

    public double getHargaDasar() {
        return hargaDasar;
    }

    public void setHargaDasar(double hargaDasar) {
        this.hargaDasar = hargaDasar;
    }

    public static int getTotalPaket() {
        return totalPaket;
    }

    public void tampilkanInfo() {
        System.out.println("Nama Paket  : " + namaPaket);
        System.out.println("Kode Paket  : " + kodePaket);
        System.out.printf(
                "Harga Dasar : Rp%,.0f%n",
                hargaDasar
        );
    }

    public abstract String getJenisLokasi();
}