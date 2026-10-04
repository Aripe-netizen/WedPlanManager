package com.mycompany.wedplanmanager;

public class PaketPernikahan {

    private String namaPaket;
    private String kodePaket;
    private double hargaDasar;

    private static int totalPaket = 0;

    public PaketPernikahan(String namaPaket, String kodePaket, double hargaDasar) {
        this.namaPaket = namaPaket;
        this.kodePaket = kodePaket;
        this.hargaDasar = hargaDasar;

        totalPaket++;
    }

    // Getter
    public String getNamaPaket() {
        return this.namaPaket;
    }

    public String getKodePaket() {
        return this.kodePaket;
    }

    public double getHargaDasar() {
        return this.hargaDasar;
    }

    // Setter
    public void setNamaPaket(String namaPaket) {
        if (namaPaket != null && !namaPaket.trim().isEmpty()) {
            this.namaPaket = namaPaket;
        } else {
            System.out.println("Nama paket tidak boleh kosong.");
        }
    }

    public void setKodePaket(String kodePaket) {
        if (kodePaket != null && !kodePaket.trim().isEmpty()) {
            this.kodePaket = kodePaket;
        } else {
            System.out.println("Kode paket tidak boleh kosong.");
        }
    }

    public void setHargaDasar(double hargaDasar) {
        if (hargaDasar > 0) {
            this.hargaDasar = hargaDasar;
        } else {
            System.out.println("Harga harus lebih dari 0.");
        }
    }

    public static int getTotalPaket() {
        return totalPaket;
    }

    // Method yang akan dioverride
    public void tampilkanInfo() {
        System.out.println("Nama Paket  : " + namaPaket);
        System.out.println("Kode Paket  : " + kodePaket);
        System.out.printf("Harga Dasar : Rp%,.0f%n", hargaDasar);
    }
}