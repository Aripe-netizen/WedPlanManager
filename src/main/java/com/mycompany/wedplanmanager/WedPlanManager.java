package com.mycompany.wedplanmanager;

import java.util.Scanner;

public class WedPlanManager {

    static Scanner input = new Scanner(System.in);

    // ARRAY SUPERSCLASS
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
                    prosesPaket();
                    break;

                case 5:
                    System.out.println();
                    System.out.println(
                            "Total paket: "
                            + PaketPernikahan.getTotalPaket()
                    );
                    break;

                case 0:
                    System.out.println();
                    System.out.println(
                            "Terima kasih telah menggunakan"
                    );
                    System.out.println(
                            "WedPlan Manager!"
                    );
                    break;

                default:
                    System.out.println(
                            "Pilihan tidak tersedia."
                    );
            }

        } while (pilihan != 0);

        input.close();
    }

    // ==========================================
    // MENU
    // ==========================================

    public static void tampilkanMenu() {

        System.out.println();
        System.out.println(
                "======================================"
        );

        System.out.println(
                "          WEDPLAN MANAGER"
        );

        System.out.println(
                "     Wedding Event Organizer"
        );

        System.out.println(
                "======================================"
        );

        System.out.println(
                "1. Tambah Paket Baru"
        );

        System.out.println(
                "2. Tampilkan Semua Paket"
        );

        System.out.println(
                "3. Cari Paket"
        );

        System.out.println(
                "4. Proses Paket"
        );

        System.out.println(
                "5. Lihat Total Paket"
        );

        System.out.println(
                "0. Keluar"
        );

        System.out.println(
                "======================================"
        );
    }

    // ==========================================
    // TAMBAH PAKET
    // ==========================================

    public static void tambahPaket() {

        System.out.println();
        System.out.println(
                "======================================"
        );

        System.out.println(
                "          TAMBAH PAKET BARU"
        );

        System.out.println(
                "======================================"
        );

        System.out.print("Nama Paket  : ");
        String nama = input.nextLine();

        System.out.print("Kode Paket  : ");
        String kode = input.nextLine();

        System.out.print("Harga Dasar : Rp");
        double harga = input.nextDouble();

        System.out.println();

        System.out.println("1. Indoor");
        System.out.println("2. Outdoor");
        System.out.println("3. Semi Outdoor");

        System.out.print("Pilih lokasi: ");
        int jenis = input.nextInt();
        input.nextLine();

        // ======================================
        // INDOOR
        // ======================================

        if (jenis == 1) {

            System.out.print(
                    "Kapasitas Gedung : "
            );

            int kapasitas =
                    input.nextInt();

            System.out.print(
                    "Jumlah Unit AC   : "
            );

            int ac =
                    input.nextInt();

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

            System.out.println(
                    "Paket Indoor berhasil ditambahkan."
            );

        }

        // ======================================
        // OUTDOOR
        // ======================================

        else if (jenis == 2) {

            System.out.print(
                    "Luas Area (m2)      : "
            );

            double luas =
                    input.nextDouble();

            input.nextLine();

            System.out.print(
                    "Jenis Tenda Darurat : "
            );

            String tenda =
                    input.nextLine();

            daftarPaket[jumlahPaket] =
                    new PaketOutdoor(
                            nama,
                            kode,
                            harga,
                            luas,
                            tenda
                    );

            jumlahPaket++;

            System.out.println(
                    "Paket Outdoor berhasil ditambahkan."
            );

        }

        // ======================================
        // SEMI OUTDOOR
        // ======================================

        else if (jenis == 3) {

            System.out.print(
                    "Kapasitas Area : "
            );

            int kapasitas =
                    input.nextInt();

            input.nextLine();

            System.out.print(
                    "Jenis Kanopi   : "
            );

            String kanopi =
                    input.nextLine();

            daftarPaket[jumlahPaket] =
                    new PaketSemiOutdoor(
                            nama,
                            kode,
                            harga,
                            kapasitas,
                            kanopi
                    );

            jumlahPaket++;

            System.out.println(
                    "Paket Semi Outdoor berhasil ditambahkan."
            );

        }

        else {

            System.out.println(
                    "Jenis lokasi tidak valid."
            );
        }
    }

    // ==========================================
    // TAMPILKAN SEMUA PAKET
    // ==========================================

    public static void tampilkanSemuaPaket() {

        System.out.println();
        System.out.println(
                "======================================"
        );

        System.out.println(
                "          DAFTAR PAKET WEDPLAN"
        );

        System.out.println(
                "======================================"
        );

        if (jumlahPaket == 0) {

            System.out.println(
                    "Belum ada paket."
            );

            return;
        }

        for (int i = 0;
                i < jumlahPaket;
                i++) {

            System.out.println();

            System.out.println(
                    "--------------------------------------"
            );

            System.out.println(
                    "Paket ke-" + (i + 1)
            );

            System.out.println(
                    "--------------------------------------"
            );

            // RUNTIME POLYMORPHISM
            daftarPaket[i].tampilkanInfo();
        }
    }

    // ==========================================
    // OVERLOADING 1
    // ==========================================

    public static void cariPaket(
            String keyword) {

        boolean ditemukan = false;

        for (int i = 0;
                i < jumlahPaket;
                i++) {

            if (daftarPaket[i]
                    .getNamaPaket()
                    .equalsIgnoreCase(keyword)
                    ||
                daftarPaket[i]
                    .getKodePaket()
                    .equalsIgnoreCase(keyword)) {

                daftarPaket[i]
                        .tampilkanInfo();

                ditemukan = true;
            }
        }

        if (!ditemukan) {

            System.out.println(
                    "Paket tidak ditemukan."
            );
        }
    }

    // ==========================================
    // OVERLOADING 2
    // ==========================================

    public static void cariPaket(
            double hargaMaksimal) {

        boolean ditemukan = false;

        for (int i = 0;
                i < jumlahPaket;
                i++) {

            if (daftarPaket[i]
                    .getHargaDasar()
                    <= hargaMaksimal) {

                daftarPaket[i]
                        .tampilkanInfo();

                ditemukan = true;
            }
        }

        if (!ditemukan) {

            System.out.println(
                    "Tidak ada paket dengan harga tersebut."
            );
        }
    }

    // ==========================================
    // MENU CARI
    // ==========================================

    public static void cariPaket() {

        System.out.println();

        System.out.println(
                "======================================"
        );

        System.out.println(
                "              CARI PAKET"
        );

        System.out.println(
                "======================================"
        );

        System.out.println(
                "1. Cari berdasarkan nama/kode"
        );

        System.out.println(
                "2. Cari berdasarkan harga maksimal"
        );

        System.out.print("Pilihan: ");

        int pilihan =
                input.nextInt();

        input.nextLine();

        switch (pilihan) {

            case 1:

                System.out.print(
                        "Masukkan nama/kode: "
                );

                String keyword =
                        input.nextLine();

                cariPaket(keyword);

                break;

            case 2:

                System.out.print(
                        "Masukkan harga maksimal: Rp"
                );

                double harga =
                        input.nextDouble();

                input.nextLine();

                cariPaket(harga);

                break;

            default:

                System.out.println(
                        "Pilihan tidak valid."
                );
        }
    }

    // ==========================================
    // METHOD SUPERCLASS PARAMETER
    // ==========================================

    public static void prosesPaket(
            PaketPernikahan paket) {

        System.out.println();

        System.out.println(
                "======================================"
        );

        System.out.println(
                "          PROSES PAKET WEDDING"
        );

        System.out.println(
                "======================================"
        );

        // DYNAMIC BINDING
        paket.tampilkanInfo();

        System.out.println();

        System.out.println(
                "Paket berhasil diproses."
        );
    }

    // ==========================================
    // MENU PROSES PAKET
    // ==========================================

    public static void prosesPaket() {

        if (jumlahPaket == 0) {

            System.out.println(
                    "Belum ada paket."
            );

            return;
        }

        tampilkanSemuaPaket();

        System.out.println();

        System.out.print(
                "Pilih nomor paket yang ingin diproses: "
        );

        int nomor =
                input.nextInt();

        input.nextLine();

        if (nomor >= 1 &&
                nomor <= jumlahPaket) {

            /*
             * Parameter method adalah
             * PaketPernikahan (Superclass).
             *
             * Object sebenarnya dapat berupa:
             * PaketIndoor
             * PaketOutdoor
             * PaketSemiOutdoor
             */

            prosesPaket(
                    daftarPaket[nomor - 1]
            );

        } else {

            System.out.println(
                    "Nomor paket tidak valid."
            );
        }
    }

    // ==========================================
    // DATA AWAL
    // ==========================================

    public static void tambahDataAwal() {

        // OBJECT 1
        daftarPaket[jumlahPaket++] =
                new PaketIndoor(
                        "Royal Indoor",
                        "IND001",
                        25000000,
                        500,
                        20
                );

        // OBJECT 2
        daftarPaket[jumlahPaket++] =
                new PaketIndoor(
                        "Elegant Ballroom",
                        "IND002",
                        35000000,
                        800,
                        30
                );

        // OBJECT 3
        daftarPaket[jumlahPaket++] =
                new PaketOutdoor(
                        "Garden Romance",
                        "OUT001",
                        20000000,
                        1000,
                        "Tenda Dome"
                );

        // OBJECT 4
        daftarPaket[jumlahPaket++] =
                new PaketOutdoor(
                        "Beach Wedding",
                        "OUT002",
                        30000000,
                        1500,
                        "Tenda Transparan"
                );

        // OBJECT 5 - SUBCLASS BARU
        daftarPaket[jumlahPaket++] =
                new PaketSemiOutdoor(
                        "Sunset Garden",
                        "SEM001",
                        28000000,
                        600,
                        "Kanopi Transparan"
                );
    }
}