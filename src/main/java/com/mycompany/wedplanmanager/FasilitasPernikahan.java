
package com.mycompany.wedplanmanager;

public class FasilitasPernikahan {

    private String namaFasilitas;
    private String keterangan;

    public FasilitasPernikahan(String namaFasilitas, String keterangan) {
        this.namaFasilitas = namaFasilitas;
        this.keterangan = keterangan;
    }

    public void tampilkanFasilitas() {
        System.out.println("- " + namaFasilitas + " : " + keterangan);
    }
}
