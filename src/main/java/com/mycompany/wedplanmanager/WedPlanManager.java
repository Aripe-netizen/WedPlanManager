
package com.mycompany.wedplanmanager;

import java.util.Scanner;
import java.util.InputMismatchException;

public class WedPlanManager {

    static Scanner input = new Scanner(System.in);
    static PengelolaWedPlan wedPlan = new PengelolaWedPlan();
    static TransaksiPernikahan transaksi = new TransaksiPernikahan();

    public static void main(String[] args) {
        wedPlan.muatDataDariFile();

        if (wedPlan.getJumlahPaket() == 0) {
            inisialisasiData();
        }

        boolean berjalan = true;

        while (berjalan) {
            tampilkanMenu();

            try {
                System.out.print("Pilih menu: ");
                int pilihan = input.nextInt();
                input.nextLine();

                switch (pilihan) {
                    case 1:
                        tambahPaket();
                        break;
                    case 2:
                        wedPlan.tampilkanSemuaPaket();
                        break;
                    case 3:
                        cariPaket();
                        break;
                    case 4:
                        prosesPaket();
                        break;
                    case 5:
                        System.out.println("Total paket: " + wedPlan.getJumlahPaket());
                        break;
                    case 6:
                        pesanPaket();
                        break;
                    case 7:
                        prosesPembayaran();
                        break;
                    case 8:
                        kelolaPelanggan();
                        break;
                    case 9:
                        wedPlan.tampilkanFasilitas();
                        break;
                    case 10:
                        transaksiPernikahan();
                        break;
                    case 11:
                        wedPlan.simpanDataKeFile();
                        break;
                    case 12:
                        wedPlan.muatDataDariFile();
                        break;
                    case 13:
                        wedPlan.bacaLogTransaksi();
                        break;
                    case 0:
                        wedPlan.simpanDataKeFile();
                        berjalan = false;
                        System.out.println("Terima kasih telah menggunakan WedPlanManager.");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka.");
                input.nextLine();
            }
        }

        input.close();
    }

    public static void tampilkanMenu() {
        System.out.println("\n========== WEDPLAN MANAGER ==========");
        System.out.println("1. Tambah Paket Pernikahan");
        System.out.println("2. Tampilkan Semua Paket");
        System.out.println("3. Cari Paket");
        System.out.println("4. Proses Paket");
        System.out.println("5. Hitung Total Paket");
        System.out.println("6. Pesan Paket");
        System.out.println("7. Proses Pembayaran");
        System.out.println("8. Kelola Pelanggan");
        System.out.println("9. Tampilkan Fasilitas Wedding");
        System.out.println("10. Transaksi Pernikahan");
        System.out.println("11. Simpan Data");
        System.out.println("12. Muat Data");
        System.out.println("13. Baca Riwayat Transaksi");
        System.out.println("0. Keluar");
        System.out.println("=====================================");
    }

    public static void inisialisasiData() {
        wedPlan.tambahPaket(
                new PaketIndoor("Royal Indoor", "IND001", 25000000, 500, 20)
        );

        wedPlan.tambahPaket(
                new PaketIndoor("Elegant Ballroom", "IND002", 35000000, 800, 30)
        );

        wedPlan.tambahPaket(
                new PaketOutdoor("Garden Romance", "OUT001", 20000000, 1000, "Tenda Dome")
        );

        wedPlan.tambahPaket(
                new PaketOutdoor("Beach Wedding", "OUT002", 30000000, 1500, "Tenda Transparan")
        );

        wedPlan.tambahPaket(
                new PaketSemiOutdoor("Sunset Garden", "SEM001", 28000000, 600, "Kanopi Transparan")
        );

        wedPlan.tambahPelanggan(
                new Pelanggan("PLG001", "Andi Saputra")
        );

        wedPlan.tambahPelanggan(
                new Pelanggan("PLG002", "Budi Santoso")
        );

        wedPlan.tambahPelanggan(
                new Pelanggan("PLG003", "Citra Amelia")
        );
    }

    public static void tambahPaket() {
        System.out.println("\n========== TAMBAH PAKET ==========");
        System.out.println("1. Indoor");
        System.out.println("2. Outdoor");
        System.out.println("3. Semi Outdoor");

        System.out.print("Pilih jenis paket: ");
        int jenis = input.nextInt();
        input.nextLine();

        System.out.print("Nama Paket: ");
        String nama = input.nextLine();

        System.out.print("Kode Paket: ");
        String kode = input.nextLine();

        System.out.print("Harga Dasar: ");
        double harga = input.nextDouble();

        if (jenis == 1) {
            System.out.print("Kapasitas Gedung: ");
            int kapasitas = input.nextInt();

            System.out.print("Jumlah AC: ");
            int ac = input.nextInt();

            wedPlan.tambahPaket(
                    new PaketIndoor(nama, kode, harga, kapasitas, ac)
            );

        } else if (jenis == 2) {
            System.out.print("Luas Area: ");
            double luas = input.nextDouble();
            input.nextLine();

            System.out.print("Jenis Tenda: ");
            String tenda = input.nextLine();

            wedPlan.tambahPaket(
                    new PaketOutdoor(nama, kode, harga, luas, tenda)
            );

        } else if (jenis == 3) {
            System.out.print("Kapasitas Area: ");
            int kapasitas = input.nextInt();
            input.nextLine();

            System.out.print("Jenis Kanopi: ");
            String kanopi = input.nextLine();

            wedPlan.tambahPaket(
                    new PaketSemiOutdoor(nama, kode, harga, kapasitas, kanopi)
            );

        } else {
            System.out.println("Jenis paket tidak valid.");
        }
    }

    public static void cariPaket() {
        System.out.println("\n========== CARI PAKET ==========");
        System.out.println("1. Cari berdasarkan nama atau kode");
        System.out.println("2. Cari berdasarkan harga maksimal");

        System.out.print("Pilih pencarian: ");
        int pilihan = input.nextInt();
        input.nextLine();

        if (pilihan == 1) {
            System.out.print("Masukkan nama atau kode paket: ");
            String keyword = input.nextLine();

            try {
                PaketPernikahan paket = wedPlan.cariPaket(keyword);
                paket.tampilkanInfo();
            } catch (PaketTidakDitemukanException e) {
                System.out.println(e.getMessage());
            }

        } else if (pilihan == 2) {
            System.out.print("Masukkan harga maksimal: ");
            double harga = input.nextDouble();

            wedPlan.cariPaket(harga);

        } else {
            System.out.println("Pilihan tidak valid.");
        }
    }

    public static void prosesPaket() {
        wedPlan.tampilkanSemuaPaket();

        System.out.print("\nMasukkan kode paket: ");
        input.nextLine();
        String kode = input.nextLine();

        try {
            PaketPernikahan paket = wedPlan.cariPaket(kode);
            System.out.println("\nPaket berhasil ditemukan.");
            paket.tampilkanInfo();
        } catch (PaketTidakDitemukanException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void pesanPaket() {
        wedPlan.tampilkanSemuaPaket();

        System.out.print("\nMasukkan kode paket: ");
        input.nextLine();
        String kode = input.nextLine();

        try {
            PaketPernikahan paket = wedPlan.cariPaket(kode);

            if (paket instanceof DapatDipesan) {
                DapatDipesan pemesanan = (DapatDipesan) paket;
                pemesanan.pesanPaket();
            }

        } catch (PaketTidakDitemukanException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void prosesPembayaran() {
        wedPlan.tampilkanSemuaPaket();

        System.out.print("\nMasukkan kode paket: ");
        input.nextLine();
        String kode = input.nextLine();

        try {
            PaketPernikahan paket = wedPlan.cariPaket(kode);

            if (paket instanceof DapatDibayar) {
                DapatDibayar pembayaran = (DapatDibayar) paket;
                pembayaran.prosesPembayaran();
            }

        } catch (PaketTidakDitemukanException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void kelolaPelanggan() {
        boolean kembali = false;

        while (!kembali) {
            System.out.println("\n========== KELOLA PELANGGAN ==========");
            System.out.println("1. Tambah Pelanggan");
            System.out.println("2. Cari Pelanggan");
            System.out.println("3. Tampilkan Semua Pelanggan");
            System.out.println("0. Kembali");

            System.out.print("Pilih menu: ");
            int pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1:
                    System.out.print("ID Pelanggan: ");
                    String id = input.nextLine();

                    System.out.print("Nama Pelanggan: ");
                    String nama = input.nextLine();

                    wedPlan.tambahPelanggan(
                            new Pelanggan(id, nama)
                    );
                    break;

                case 2:
                    System.out.print("Masukkan ID Pelanggan: ");
                    String cariId = input.nextLine();

                    Pelanggan pelanggan = wedPlan.cariPelanggan(cariId);

                    if (pelanggan != null) {
                        pelanggan.tampilkanInfo();
                    } else {
                        System.out.println("Pelanggan tidak ditemukan.");
                    }
                    break;

                case 3:
                    wedPlan.tampilkanSemuaPelanggan();
                    break;

                case 0:
                    kembali = true;
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    public static void transaksiPernikahan() {
        System.out.println("\n========== TRANSAKSI PERNIKAHAN ==========");

        System.out.print("Masukkan ID Pelanggan: ");
        input.nextLine();
        String id = input.nextLine();

        Pelanggan pelanggan = wedPlan.cariPelanggan(id);

        if (pelanggan == null) {
            System.out.println("Pelanggan tidak ditemukan.");
            return;
        }

        wedPlan.tampilkanSemuaPaket();

        System.out.print("\nMasukkan nomor paket: ");
        int nomor = input.nextInt();

        PaketPernikahan paket = wedPlan.getPaket(nomor - 1);

        if (paket == null) {
            System.out.println("Nomor paket tidak valid.");
            return;
        }

        transaksi.prosesTransaksi(pelanggan, paket);
    }
}
