
package com.mycompany.wedplanmanager;

import java.util.Scanner;

public class WedPlanManager {

    static Scanner input = new Scanner(System.in);
    static PaketPernikahan[] daftarPaket =
            new PaketPernikahan[100];
    static int jumlahPaket = 0;

    public static void main(String[] args) {

        tambahDataAwal();

        int pilihan;

        do {
            tampilkanMenu();

            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1:
                    tambahPaket();
                    break;
                case 2:
                    tampilkanSemuaPaket();
                    break;
                case 3:
                    menuCariPaket();
                    break;
                case 4:
                    prosesPaket();
                    break;
                case 5:
                    System.out.println(
                            "Total paket: "
                            + PaketPernikahan.getTotalPaket()
                    );
                    break;
                case 6:
                    pesanPaket();
                    break;
                case 7:
                    prosesPembayaran();
                    break;
                case 0:
                    System.out.println(
                            "Terima kasih telah menggunakan WedPlan Manager!"
                    );
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }

        } while (pilihan != 0);

        input.close();
    }

    public static void tampilkanMenu() {
        System.out.println("\n======================================");
        System.out.println("          WEDPLAN MANAGER");
        System.out.println("     Wedding Event Organizer");
        System.out.println("======================================");
        System.out.println("1. Tambah Paket Baru");
        System.out.println("2. Tampilkan Semua Paket");
        System.out.println("3. Cari Paket");
        System.out.println("4. Proses Paket");
        System.out.println("5. Lihat Total Paket");
        System.out.println("6. Pesan Paket");
        System.out.println("7. Proses Pembayaran");
        System.out.println("0. Keluar");
        System.out.println("======================================");
    }

    public static void tambahPaket() {

        if (jumlahPaket >= daftarPaket.length) {
            System.out.println("Penyimpanan paket penuh.");
            return;
        }

        System.out.print("Nama Paket  : ");
        String nama = input.nextLine();

        System.out.print("Kode Paket  : ");
        String kode = input.nextLine();

        System.out.print("Harga Dasar : Rp");
        double harga = input.nextDouble();

        System.out.println("1. Indoor");
        System.out.println("2. Outdoor");
        System.out.println("3. Semi Outdoor");
        System.out.print("Pilih lokasi: ");

        int jenis = input.nextInt();
        input.nextLine();

        if (jenis == 1) {
            System.out.print("Kapasitas Gedung : ");
            int kapasitas = input.nextInt();

            System.out.print("Jumlah AC        : ");
            int ac = input.nextInt();
            input.nextLine();

            daftarPaket[jumlahPaket++] =
                    new PaketIndoor(
                            nama, kode, harga, kapasitas, ac
                    );

        } else if (jenis == 2) {
            System.out.print("Luas Area (m2) : ");
            double luas = input.nextDouble();
            input.nextLine();

            System.out.print("Jenis Tenda    : ");
            String tenda = input.nextLine();

            daftarPaket[jumlahPaket++] =
                    new PaketOutdoor(
                            nama, kode, harga, luas, tenda
                    );

        } else if (jenis == 3) {
            System.out.print("Kapasitas Area : ");
            int kapasitas = input.nextInt();
            input.nextLine();

            System.out.print("Jenis Kanopi   : ");
            String kanopi = input.nextLine();

            daftarPaket[jumlahPaket++] =
                    new PaketSemiOutdoor(
                            nama, kode, harga, kapasitas, kanopi
                    );

        } else {
            System.out.println("Jenis lokasi tidak valid.");
            return;
        }

        System.out.println("Paket berhasil ditambahkan.");
    }

    public static void tampilkanSemuaPaket() {

        if (jumlahPaket == 0) {
            System.out.println("Belum ada paket.");
            return;
        }

        for (int i = 0; i < jumlahPaket; i++) {
            System.out.println("\n========== PAKET " + (i + 1) + " ==========");
            daftarPaket[i].tampilkanInfo();
        }
    }

    // PENCARIAN BERDASARKAN NAMA ATAU KODE

    public static void cariPaket(String keyword) {

        boolean ditemukan = false;

        for (int i = 0; i < jumlahPaket; i++) {
            if (daftarPaket[i].getNamaPaket()
                    .equalsIgnoreCase(keyword)
                    || daftarPaket[i].getKodePaket()
                    .equalsIgnoreCase(keyword)) {

                daftarPaket[i].tampilkanInfo();
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Paket tidak ditemukan.");
        }
    }

    // PENCARIAN BERDASARKAN HARGA MAKSIMAL

    public static void cariPaket(double hargaMaksimal) {

        boolean ditemukan = false;

        for (int i = 0; i < jumlahPaket; i++) {
            if (daftarPaket[i].getHargaDasar() <= hargaMaksimal) {
                daftarPaket[i].tampilkanInfo();
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Tidak ada paket sesuai harga.");
        }
    }

    public static void menuCariPaket() {

        System.out.println("1. Cari nama/kode");
        System.out.println("2. Cari harga maksimal");
        System.out.print("Pilihan: ");

        int pilihan = input.nextInt();
        input.nextLine();

        if (pilihan == 1) {
            System.out.print("Masukkan nama/kode: ");
            cariPaket(input.nextLine());

        } else if (pilihan == 2) {
            System.out.print("Harga maksimal: Rp");
            double harga = input.nextDouble();
            input.nextLine();
            cariPaket(harga);

        } else {
            System.out.println("Pilihan tidak valid.");
        }
    }

    // POLYMORPHISM

    public static void prosesPaket(PaketPernikahan paket) {
        paket.tampilkanInfo();
        System.out.println("Paket berhasil diproses.");
    }

    public static void prosesPaket() {

        if (jumlahPaket == 0) {
            System.out.println("Belum ada paket.");
            return;
        }

        tampilkanSemuaPaket();

        System.out.print("Pilih nomor paket: ");
        int nomor = input.nextInt();
        input.nextLine();

        if (nomor >= 1 && nomor <= jumlahPaket) {
            prosesPaket(daftarPaket[nomor - 1]);
        } else {
            System.out.println("Nomor paket tidak valid.");
        }
    }

    // INTERFACE: PEMESANAN

    public static void pesanPaket() {

        if (jumlahPaket == 0) {
            System.out.println("Belum ada paket.");
            return;
        }

        tampilkanSemuaPaket();

        System.out.print("Pilih nomor paket yang dipesan: ");
        int nomor = input.nextInt();
        input.nextLine();

        if (nomor < 1 || nomor > jumlahPaket) {
            System.out.println("Nomor paket tidak valid.");
            return;
        }

        PaketPernikahan paket = daftarPaket[nomor - 1];

        System.out.println(
                "Lokasi paket: " + paket.getJenisLokasi()
        );

        if (paket instanceof DapatDipesan) {
            DapatDipesan pemesanan = (DapatDipesan) paket;
            pemesanan.pesanPaket();
        } else {
            System.out.println("Paket tidak mendukung pemesanan.");
        }
    }

    // INTERFACE: PEMBAYARAN

    public static void prosesPembayaran() {

        if (jumlahPaket == 0) {
            System.out.println("Belum ada paket.");
            return;
        }

        tampilkanSemuaPaket();

        System.out.print("Pilih nomor paket yang dibayar: ");
        int nomor = input.nextInt();
        input.nextLine();

        if (nomor < 1 || nomor > jumlahPaket) {
            System.out.println("Nomor paket tidak valid.");
            return;
        }

        PaketPernikahan paket = daftarPaket[nomor - 1];

        if (paket instanceof DapatDibayar) {
            DapatDibayar pembayaran = (DapatDibayar) paket;
            pembayaran.prosesPembayaran();
        } else {
            System.out.println("Paket tidak mendukung pembayaran.");
        }
    }

    // LIMA DATA PAKET AWAL

    public static void tambahDataAwal() {

        daftarPaket[jumlahPaket++] =
                new PaketIndoor(
                        "Royal Indoor", "IND001",
                        25000000, 500, 20
                );

        daftarPaket[jumlahPaket++] =
                new PaketIndoor(
                        "Elegant Ballroom", "IND002",
                        35000000, 800, 30
                );

        daftarPaket[jumlahPaket++] =
                new PaketOutdoor(
                        "Garden Romance", "OUT001",
                        20000000, 1000, "Tenda Dome"
                );

        daftarPaket[jumlahPaket++] =
                new PaketOutdoor(
                        "Beach Wedding", "OUT002",
                        30000000, 1500, "Tenda Transparan"
                );

        daftarPaket[jumlahPaket++] =
                new PaketSemiOutdoor(
                        "Sunset Garden", "SEM001",
                        28000000, 600, "Kanopi Transparan"
                );
    }
}
