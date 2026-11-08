
package com.mycompany.wedplanmanager;

public class TransaksiPernikahan {

    public void prosesTransaksi(
            Pelanggan pelanggan,
            PaketPernikahan paket) {

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
        System.out.println("==========================================");
    }
}
