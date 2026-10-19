
package com.mycompany.wedplanmanager;

public class PaketSemiOutdoor extends PaketPernikahan
        implements DapatDipesan, DapatDibayar {

    private int kapasitasArea;
    private String jenisKanopi;

    public PaketSemiOutdoor(
            String namaPaket,
            String kodePaket,
            double hargaDasar,
            int kapasitasArea,
            String jenisKanopi) {

        super(namaPaket, kodePaket, hargaDasar);
        this.kapasitasArea = kapasitasArea;
        this.jenisKanopi = jenisKanopi;
    }

    public int getKapasitasArea() {
        return kapasitasArea;
    }

    public void setKapasitasArea(int kapasitasArea) {
        this.kapasitasArea = kapasitasArea;
    }

    public String getJenisKanopi() {
        return jenisKanopi;
    }

    public void setJenisKanopi(String jenisKanopi) {
        this.jenisKanopi = jenisKanopi;
    }

    @Override
    public String getJenisLokasi() {
        return "Semi Outdoor";
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Jenis Lokasi   : " + getJenisLokasi());
        System.out.println("Kapasitas Area : " + kapasitasArea + " orang");
        System.out.println("Jenis Kanopi   : " + jenisKanopi);
    }

    @Override
    public void pesanPaket() {
        System.out.println(
                "Paket Semi Outdoor " + getNamaPaket()
                + " berhasil dipesan."
        );
    }

    @Override
    public void prosesPembayaran() {
        System.out.println(
                "Pembayaran paket Semi Outdoor berhasil diproses."
        );
        System.out.printf(
                "Total pembayaran: Rp%,.0f%n",
                getHargaDasar()
        );
    }
}
