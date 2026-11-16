
package com.mycompany.wedplanmanager;

import java.io.Serializable;

public class Pelanggan implements Serializable {

    private static final long serialVersionUID = 1L;

    private String idPelanggan;
    private String namaPelanggan;

    public Pelanggan(String idPelanggan, String namaPelanggan) {
        this.idPelanggan = idPelanggan;
        this.namaPelanggan = namaPelanggan;
    }

    public String getIdPelanggan() {
        return idPelanggan;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public void tampilkanInfo() {
        System.out.println("ID Pelanggan   : " + idPelanggan);
        System.out.println("Nama Pelanggan : " + namaPelanggan);
    }
}
