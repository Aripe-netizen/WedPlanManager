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
                    cariPaket();
                    break;

                case 4:
                    System.out.println();
                    System.out.println("Total paket: "
                            + PaketPernikahan.getTotalPaket());
                    break;

                case 0:
                    System.out.println();
                    System.out.println("Terima kasih telah menggunakan");
                    System.out.println("WedPlan Manager!");
                    break;

                default:
                    System.out.println("Pilihan tidak tersedia.");
            }

        } while (pilihan != 0);

        input.close();
    }

    public static void tampilkanMenu() {

        System.out.println();
        System.out.println("======================================");
        System.out.println("          WEDPLAN MANAGER");
        System.out.println("     Wedding Event Organizer");
        System.out.println("======================================");
        System.out.println("1. Tambah Paket Baru");
        System.out.println("2. Tampilkan Semua Paket");
        System.out.println("3. Cari Paket");
        System.out.println("4. Lihat Total Paket");
        System.out.println("0. Keluar");
        System.out.println("======================================");
    }

    public static void tambahPaket() {

        System.out.println();
        System.out.println("======================================");
        System.out.println("          TAMBAH PAKET BARU");
        System.out.println("======================================");

        System.out.print("Nama Paket  : ");
        String nama = input.nextLine();

        System.out.print("Kode Paket  : ");
        String kode = input.nextLine();

        System.out.print("Harga Dasar : Rp");
        double harga = input.nextDouble();

        System.out.println();
        System.out.println("1. Indoor");
        System.out.println("2. Outdoor");

        System.out.print("Pilih lokasi: ");
        int jenis = input.nextInt();
        input.nextLine();

        if (jenis == 1) {

            System.out.print("Kapasitas Gedung : ");
            int kapasitas = input.nextInt();

            System.out.print("Jumlah Unit AC   : ");
            int ac = input.nextInt();
            input.nextLine();

            daftarPaket[jumlahPaket] =
                    new PaketIndoor(
                            nama,
                            kode,
                            harga,
                            kapasitas,
                            ac
                    );

            jumlahPaket++;

            System.out.println("Paket Indoor berhasil ditambahkan.");

        } else if (jenis == 2) {

            System.out.print("Luas Area (m2)      : ");
            double luas = input.nextDouble();
            input.nextLine();

            System.out.print("Jenis Tenda Darurat : ");
            String tenda = input.nextLine();

            daftarPaket[jumlahPaket] =
                    new PaketOutdoor(
                            nama,
                            kode,
                            harga,
                            luas,
                            tenda
                    );

            jumlahPaket++;

            System.out.println("Paket Outdoor berhasil ditambahkan.");

        } else {

            System.out.println("Jenis lokasi tidak valid.");
        }
    }

    public static void tampilkanSemuaPaket() {

        System.out.println();
        System.out.println("======================================");
        System.out.println("          DAFTAR PAKET WEDPLAN");
        System.out.println("======================================");

        if (jumlahPaket == 0) {
            System.out.println("Belum ada paket.");
            return;
        }

        for (int i = 0; i < jumlahPaket; i++) {

            System.out.println();
            System.out.println("--------------------------------------");
            System.out.println("Paket ke-" + (i + 1));
            System.out.println("--------------------------------------");

            daftarPaket[i].tampilkanInfo();
        }
    }

    // OVERLOADING 1
    public static void cariPaket(String keyword) {

        boolean ditemukan = false;

        for (int i = 0; i < jumlahPaket; i++) {

            if (daftarPaket[i].getNamaPaket()
                    .equalsIgnoreCase(keyword)
                    ||
                daftarPaket[i].getKodePaket()
                    .equalsIgnoreCase(keyword)) {

                daftarPaket[i].tampilkanInfo();

                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Paket tidak ditemukan.");
        }
    }

    // OVERLOADING 2
    public static void cariPaket(double hargaMaksimal) {

        boolean ditemukan = false;

        for (int i = 0; i < jumlahPaket; i++) {

            if (daftarPaket[i].getHargaDasar()
                    <= hargaMaksimal) {

                daftarPaket[i].tampilkanInfo();

                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println(
                    "Tidak ada paket dengan harga tersebut."
            );
        }
    }

    public static void cariPaket() {

        System.out.println();
        System.out.println("======================================");
        System.out.println("              CARI PAKET");
        System.out.println("======================================");

        System.out.println("1. Cari berdasarkan nama/kode");
        System.out.println("2. Cari berdasarkan harga maksimal");

        System.out.print("Pilihan: ");
        int pilihan = input.nextInt();
        input.nextLine();

        switch (pilihan) {

            case 1:

                System.out.print("Masukkan nama/kode: ");
                String keyword = input.nextLine();

                cariPaket(keyword);

                break;

            case 2:

                System.out.print("Masukkan harga maksimal: Rp");
                double harga = input.nextDouble();
                input.nextLine();

                cariPaket(harga);

                break;

            default:

                System.out.println("Pilihan tidak valid.");
        }
    }

    public static void tambahDataAwal() {

        daftarPaket[jumlahPaket++] =
                new PaketIndoor(
                        "Royal Indoor",
                        "IND001",
                        25000000,
                        500,
                        20
                );

        daftarPaket[jumlahPaket++] =
                new PaketIndoor(
                        "Elegant Ballroom",
                        "IND002",
                        35000000,
                        800,
                        30
                );

        daftarPaket[jumlahPaket++] =
                new PaketOutdoor(
                        "Garden Romance",
                        "OUT001",
                        20000000,
                        1000,
                        "Tenda Dome"
                );

        daftarPaket[jumlahPaket++] =
                new PaketOutdoor(
                        "Beach Wedding",
                        "OUT002",
                        30000000,
                        1500,
                        "Tenda Transparan"
                );
    }
}