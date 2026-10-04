package com.mycompany.wedplanmanager;

public class PaketOutdoor extends PaketPernikahan {

    private double luasAreaMeter;
    private String jenisTendaDarurat;

    public PaketOutdoor(
            String namaPaket,
            String kodePaket,
            double hargaDasar,
            double luasAreaMeter,
            String jenisTendaDarurat) {

        super(namaPaket, kodePaket, hargaDasar);

        this.luasAreaMeter = luasAreaMeter;
        this.jenisTendaDarurat = jenisTendaDarurat;
    }

    public double getLuasAreaMeter() {
        return this.luasAreaMeter;
    }

    public void setLuasAreaMeter(double luasAreaMeter) {
        if (luasAreaMeter > 0) {
            this.luasAreaMeter = luasAreaMeter;
        } else {
            System.out.println("Luas area harus lebih dari 0.");
        }
    }

    public String getJenisTendaDarurat() {
        return this.jenisTendaDarurat;
    }

    public void setJenisTendaDarurat(String jenisTendaDarurat) {
        if (jenisTendaDarurat != null &&
            !jenisTendaDarurat.trim().isEmpty()) {

            this.jenisTendaDarurat = jenisTendaDarurat;

        } else {
            System.out.println("Jenis tenda tidak boleh kosong.");
        }
    }

    @Override
    public void tampilkanInfo() {

        super.tampilkanInfo();

        System.out.println("Jenis Lokasi : Outdoor");
        System.out.println("Luas Area    : " + luasAreaMeter + " m2");
        System.out.println("Tenda Darurat: " + jenisTendaDarurat);
    }
}