package com.mycompany.wedplanmanager;

public class PaketIndoor extends PaketPernikahan {

    private int kapasitasGedung;
    private int jumlahUnitAC;

    public PaketIndoor(
            String namaPaket,
            String kodePaket,
            double hargaDasar,
            int kapasitasGedung,
            int jumlahUnitAC) {

        super(namaPaket, kodePaket, hargaDasar);

        this.kapasitasGedung = kapasitasGedung;
        this.jumlahUnitAC = jumlahUnitAC;
    }

    public int getKapasitasGedung() {
        return this.kapasitasGedung;
    }

    public void setKapasitasGedung(int kapasitasGedung) {
        if (kapasitasGedung > 0) {
            this.kapasitasGedung = kapasitasGedung;
        } else {
            System.out.println("Kapasitas harus lebih dari 0.");
        }
    }

    public int getJumlahUnitAC() {
        return this.jumlahUnitAC;
    }

    public void setJumlahUnitAC(int jumlahUnitAC) {
        if (jumlahUnitAC >= 0) {
            this.jumlahUnitAC = jumlahUnitAC;
        } else {
            System.out.println("Jumlah AC tidak boleh negatif.");
        }
    }

    @Override
    public void tampilkanInfo() {

        super.tampilkanInfo();

        System.out.println("Jenis Lokasi : Indoor");
        System.out.println("Kapasitas    : " + kapasitasGedung + " orang");
        System.out.println("Jumlah AC    : " + jumlahUnitAC + " unit");
    }
}