
package com.mycompany.wedplanmanager;

import java.io.FileWriter;
import java.io.IOException;

public class TransaksiPernikahan {

    public void prosesTransaksi(Pelanggan pelanggan, PaketPernikahan paket) {
        System.out.println("\n========== TRANSAKSI PERNIKAHAN ==========");

        System.out.println("\nData Pelanggan:");
        pelanggan.tampilkanInfo();

        System.out.println("\nPaket yang Dipilih:");
        paket.tampilkanInfo();

        if (paket instanceof DapatDipesan) {
            DapatDipesan pemesanan = (DapatDipesan) paket;
            pemesanan.pesanPaket();
        }

        System.out.println("\nStatus Transaksi: BERHASIL");
        System.out.println("Transaksi berhasil diproses.");

        try {
            FileWriter penulis = new FileWriter("log_transaksi.txt", true);

            penulis.write("TRANSAKSI PERNIKAHAN\n");
            penulis.write("ID Pelanggan: " + pelanggan.getIdPelanggan() + "\n");
            penulis.write("Nama Pelanggan: " + pelanggan.getNamaPelanggan() + "\n");
            penulis.write("Kode Paket: " + paket.getKodePaket() + "\n");
            penulis.write("Nama Paket: " + paket.getNamaPaket() + "\n");
            penulis.write("Harga: Rp" + paket.getHargaDasar() + "\n");
            penulis.write("Status: BERHASIL\n");
            penulis.write("------------------------------------------\n");

            penulis.close();

            System.out.println("Riwayat transaksi berhasil dicatat.");

        } catch (IOException e) {
            System.out.println("Gagal mencatat transaksi: " + e.getMessage());
        }

        System.out.println("==========================================");
    }
}
