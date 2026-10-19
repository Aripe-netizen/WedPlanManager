package com.mycompany.wedplanmanager;

public class PaketOutdoor extends PaketPernikahan
        implements DapatDipesan, DapatDibayar {

    private double luasArea;
    private String jenisTenda;

    public PaketOutdoor(
            String namaPaket,
            String kodePaket,
            double hargaDasar,
            double luasArea,
            String jenisTenda) {

        super(namaPaket, kodePaket, hargaDasar);
        this.luasArea = luasArea;
        this.jenisTenda = jenisTenda;
    }

    public double getLuasArea() {
        return luasArea;
    }

    public void setLuasArea(double luasArea) {
        this.luasArea = luasArea;
    }

    public String getJenisTenda() {
        return jenisTenda;
    }

    public void setJenisTenda(String jenisTenda) {
        this.jenisTenda = jenisTenda;
    }

    @Override
    public String getJenisLokasi() {
        return "Outdoor";
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Jenis Lokasi : " + getJenisLokasi());
        System.out.println("Luas Area    : " + luasArea + " m2");
        System.out.println("Jenis Tenda  : " + jenisTenda);
    }

    @Override
    public void pesanPaket() {
        System.out.println(
                "Paket Outdoor " + getNamaPaket()
                + " berhasil dipesan."
        );
    }

    @Override
    public void prosesPembayaran() {
        System.out.println("Pembayaran paket Outdoor berhasil diproses.");
        System.out.printf(
                "Total pembayaran: Rp%,.0f%n",
                getHargaDasar()
        );
    }
}