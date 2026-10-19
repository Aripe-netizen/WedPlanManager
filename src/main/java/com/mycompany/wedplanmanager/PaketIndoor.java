package com.mycompany.wedplanmanager;

public class PaketIndoor extends PaketPernikahan
        implements DapatDipesan, DapatDibayar {

    private int kapasitasGedung;
    private int jumlahAC;

    public PaketIndoor(
            String namaPaket,
            String kodePaket,
            double hargaDasar,
            int kapasitasGedung,
            int jumlahAC) {

        super(namaPaket, kodePaket, hargaDasar);
        this.kapasitasGedung = kapasitasGedung;
        this.jumlahAC = jumlahAC;
    }

    public int getKapasitasGedung() {
        return kapasitasGedung;
    }

    public void setKapasitasGedung(int kapasitasGedung) {
        this.kapasitasGedung = kapasitasGedung;
    }

    public int getJumlahAC() {
        return jumlahAC;
    }

    public void setJumlahAC(int jumlahAC) {
        this.jumlahAC = jumlahAC;
    }

    @Override
    public String getJenisLokasi() {
        return "Indoor";
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Jenis Lokasi     : " + getJenisLokasi());
        System.out.println("Kapasitas Gedung : " + kapasitasGedung + " orang");
        System.out.println("Jumlah AC        : " + jumlahAC + " unit");
    }

    @Override
    public void pesanPaket() {
        System.out.println(
                "Paket Indoor " + getNamaPaket()
                + " berhasil dipesan."
        );
    }

    @Override
    public void prosesPembayaran() {
        System.out.println("Pembayaran paket Indoor berhasil diproses.");
        System.out.printf(
                "Total pembayaran: Rp%,.0f%n",
                getHargaDasar()
        );
    }
}