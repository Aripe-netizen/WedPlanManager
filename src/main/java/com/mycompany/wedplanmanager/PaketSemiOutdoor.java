package com.mycompany.wedplanmanager;

public class PaketSemiOutdoor extends PaketPernikahan {

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
        return this.kapasitasArea;
    }

    public void setKapasitasArea(int kapasitasArea) {

        if (kapasitasArea > 0) {
            this.kapasitasArea = kapasitasArea;
        } else {
            System.out.println(
                    "Kapasitas area harus lebih dari 0."
            );
        }
    }

    public String getJenisKanopi() {
        return this.jenisKanopi;
    }

    public void setJenisKanopi(String jenisKanopi) {

        if (jenisKanopi != null &&
                !jenisKanopi.trim().isEmpty()) {

            this.jenisKanopi = jenisKanopi;
        } else {
            System.out.println(
                    "Jenis kanopi tidak boleh kosong."
            );
        }
    }

    @Override
    public void tampilkanInfo() {

        super.tampilkanInfo();

        System.out.println(
                "Jenis Lokasi : Semi Outdoor"
        );

        System.out.println(
                "Kapasitas Area : "
                + kapasitasArea + " orang"
        );

        System.out.println(
                "Jenis Kanopi : "
                + jenisKanopi
        );
    }
}