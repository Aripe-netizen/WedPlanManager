
package com.mycompany.wedplanmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class WedPlanManager {

    static Scanner input = new Scanner(System.in);

    // ARRAYLIST UNTUK MENYIMPAN PAKET
    static ArrayList<PaketPernikahan> daftarPaket =
            new ArrayList<>();

    // HASHMAP UNTUK MENYIMPAN DATA PELANGGAN
    static HashMap<String, String> dataPelanggan =
            new HashMap<>();

    public static void main(String[] args) {

        tambahDataAwal();
        tambahPelangganAwal();

        boolean berjalan = true;

        try {
            while (berjalan) {

                tampilkanMenu();

                System.out.print("Pilih menu: ");
                int pilihan = input.nextInt();
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
                                "Total paket: " + daftarPaket.size()
                        );
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

                    case 0:
                        berjalan = false;
                        System.out.println(
                                "Terima kasih telah menggunakan WedPlan Manager!"
                        );
                        break;

                    default:
                        System.out.println("Pilihan tidak valid.");
                }
            }

        } catch (InputMismatchException e) {

            System.out.println(
                    "ERROR: Input tidak valid! Masukkan angka."
            );

        } catch (Exception e) {

            System.out.println(
                    "ERROR: " + e.getMessage()
            );

        } finally {

            System.out.println(
                    "Blok finally: Program selesai dijalankan."
            );

            input.close();
        }
    }

    // MENU UTAMA

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
        System.out.println("8. Kelola Data Pelanggan");
        System.out.println("0. Keluar");
        System.out.println("======================================");
    }

    // TAMBAH PAKET

    public static void tambahPaket() {

        System.out.println("\n========== TAMBAH PAKET ==========");

        System.out.print("Nama Paket  : ");
        String nama = input.nextLine();

        System.out.print("Kode Paket  : ");
        String kode = input.nextLine();

        System.out.print("Harga Dasar : Rp");
        double harga = input.nextDouble();

        if (harga <= 0) {
            System.out.println("Harga harus lebih dari 0.");
            return;
        }

        System.out.println("\n1. Indoor");
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

            daftarPaket.add(
                    new PaketIndoor(
                            nama, kode, harga, kapasitas, ac
                    )
            );

        } else if (jenis == 2) {

            System.out.print("Luas Area (m2) : ");
            double luas = input.nextDouble();
            input.nextLine();

            System.out.print("Jenis Tenda    : ");
            String tenda = input.nextLine();

            daftarPaket.add(
                    new PaketOutdoor(
                            nama, kode, harga, luas, tenda
                    )
            );

        } else if (jenis == 3) {

            System.out.print("Kapasitas Area : ");
            int kapasitas = input.nextInt();
            input.nextLine();

            System.out.print("Jenis Kanopi   : ");
            String kanopi = input.nextLine();

            daftarPaket.add(
                    new PaketSemiOutdoor(
                            nama, kode, harga, kapasitas, kanopi
                    )
            );

        } else {

            System.out.println("Jenis lokasi tidak valid.");
            return;
        }

        System.out.println("Paket berhasil ditambahkan.");
    }

    // TAMPILKAN SEMUA PAKET

    public static void tampilkanSemuaPaket() {

        System.out.println("\n========== DAFTAR PAKET ==========");

        if (daftarPaket.isEmpty()) {
            System.out.println("Belum ada paket.");
            return;
        }

        for (int i = 0; i < daftarPaket.size(); i++) {

            System.out.println("\n========== PAKET " + (i + 1) + " ==========");

            daftarPaket.get(i).tampilkanInfo();
        }

        System.out.println(
                "\nTotal paket: " + daftarPaket.size()
        );
    }

    // PENCARIAN PAKET DENGAN THROW DAN THROWS

    public static void cariPaket(String keyword)
            throws PaketTidakDitemukanException {

        boolean ditemukan = false;

        for (int i = 0; i < daftarPaket.size(); i++) {

            PaketPernikahan paket = daftarPaket.get(i);

            if (paket.getNamaPaket().equalsIgnoreCase(keyword)
                    || paket.getKodePaket().equalsIgnoreCase(keyword)) {

                paket.tampilkanInfo();
                ditemukan = true;
            }
        }

        if (!ditemukan) {

            throw new PaketTidakDitemukanException(
                    "Paket dengan nama atau kode tersebut tidak ditemukan!"
            );
        }
    }

    // PENCARIAN BERDASARKAN HARGA

    public static void cariPaket(double hargaMaksimal) {

        boolean ditemukan = false;

        for (int i = 0; i < daftarPaket.size(); i++) {

            PaketPernikahan paket = daftarPaket.get(i);

            if (paket.getHargaDasar() <= hargaMaksimal) {

                paket.tampilkanInfo();
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Tidak ada paket sesuai harga.");
        }
    }

    // MENU PENCARIAN

    public static void menuCariPaket() {

        System.out.println("\n1. Cari berdasarkan nama/kode");
        System.out.println("2. Cari berdasarkan harga maksimal");
        System.out.print("Pilihan: ");

        int pilihan = input.nextInt();
        input.nextLine();

        if (pilihan == 1) {

            System.out.print("Masukkan nama/kode: ");
            String keyword = input.nextLine();

            try {

                cariPaket(keyword);

            } catch (PaketTidakDitemukanException e) {

                System.out.println(
                        "ERROR: " + e.getMessage()
                );

            } finally {

                System.out.println(
                        "Pencarian paket selesai."
                );
            }

        } else if (pilihan == 2) {

            System.out.print("Harga maksimal: Rp");
            double harga = input.nextDouble();
            input.nextLine();

            cariPaket(harga);

        } else {

            System.out.println("Pilihan tidak valid.");
        }
    }

    // PROSES PAKET

    public static void prosesPaket(PaketPernikahan paket) {

        paket.tampilkanInfo();
        System.out.println("Paket berhasil diproses.");
    }

    public static void prosesPaket() {

        if (daftarPaket.isEmpty()) {
            System.out.println("Belum ada paket.");
            return;
        }

        tampilkanSemuaPaket();

        System.out.print("Pilih nomor paket: ");
        int nomor = input.nextInt();
        input.nextLine();

        if (nomor >= 1 && nomor <= daftarPaket.size()) {

            prosesPaket(daftarPaket.get(nomor - 1));

        } else {

            System.out.println("Nomor paket tidak valid.");
        }
    }

    // PEMESANAN MENGGUNAKAN INTERFACE

    public static void pesanPaket() {

        if (daftarPaket.isEmpty()) {
            System.out.println("Belum ada paket.");
            return;
        }

        tampilkanSemuaPaket();

        System.out.print("Pilih nomor paket yang dipesan: ");
        int nomor = input.nextInt();
        input.nextLine();

        if (nomor < 1 || nomor > daftarPaket.size()) {
            System.out.println("Nomor paket tidak valid.");
            return;
        }

        PaketPernikahan paket = daftarPaket.get(nomor - 1);

        if (paket instanceof DapatDipesan) {

            DapatDipesan pemesanan = (DapatDipesan) paket;
            pemesanan.pesanPaket();

        } else {

            System.out.println(
                    "Paket tidak mendukung pemesanan."
            );
        }
    }

    // PEMBAYARAN MENGGUNAKAN INTERFACE

    public static void prosesPembayaran() {

        if (daftarPaket.isEmpty()) {
            System.out.println("Belum ada paket.");
            return;
        }

        tampilkanSemuaPaket();

        System.out.print("Pilih nomor paket yang dibayar: ");
        int nomor = input.nextInt();
        input.nextLine();

        if (nomor < 1 || nomor > daftarPaket.size()) {
            System.out.println("Nomor paket tidak valid.");
            return;
        }

        PaketPernikahan paket = daftarPaket.get(nomor - 1);

        if (paket instanceof DapatDibayar) {

            DapatDibayar pembayaran = (DapatDibayar) paket;
            pembayaran.prosesPembayaran();

        } else {

            System.out.println(
                    "Paket tidak mendukung pembayaran."
            );
        }
    }

    // DATA AWAL PAKET: ARRAYLIST

    public static void tambahDataAwal() {

        daftarPaket.add(
                new PaketIndoor(
                        "Royal Indoor", "IND001",
                        25000000, 500, 20
                )
        );

        daftarPaket.add(
                new PaketIndoor(
                        "Elegant Ballroom", "IND002",
                        35000000, 800, 30
                )
        );

        daftarPaket.add(
                new PaketOutdoor(
                        "Garden Romance", "OUT001",
                        20000000, 1000, "Tenda Dome"
                )
        );

        daftarPaket.add(
                new PaketOutdoor(
                        "Beach Wedding", "OUT002",
                        30000000, 1500, "Tenda Transparan"
                )
        );

        daftarPaket.add(
                new PaketSemiOutdoor(
                        "Sunset Garden", "SEM001",
                        28000000, 600, "Kanopi Transparan"
                )
        );
    }

    // DATA AWAL PELANGGAN: HASHMAP

    public static void tambahPelangganAwal() {

        dataPelanggan.put("PLG001", "Andi Saputra");
        dataPelanggan.put("PLG002", "Budi Santoso");
        dataPelanggan.put("PLG003", "Citra Amelia");
    }

    // MENU KELOLA PELANGGAN

    public static void kelolaPelanggan() {

        System.out.println("\n========== KELOLA PELANGGAN ==========");
        System.out.println("1. Tambah Pelanggan");
        System.out.println("2. Cari Pelanggan");
        System.out.println("3. Tampilkan Semua Pelanggan");
        System.out.print("Pilihan: ");

        int pilihan = input.nextInt();
        input.nextLine();

        if (pilihan == 1) {

            System.out.print("Masukkan ID Pelanggan: ");
            String id = input.nextLine();

            if (id.trim().isEmpty()) {
                System.out.println("ID tidak boleh kosong.");
                return;
            }

            if (dataPelanggan.containsKey(id)) {
                System.out.println("ID pelanggan sudah terdaftar.");
                return;
            }

            System.out.print("Masukkan Nama Pelanggan: ");
            String nama = input.nextLine();

            if (nama.trim().isEmpty()) {
                System.out.println("Nama tidak boleh kosong.");
                return;
            }

            dataPelanggan.put(id, nama);

            System.out.println(
                    "Pelanggan berhasil ditambahkan."
            );

        } else if (pilihan == 2) {

            System.out.print("Masukkan ID yang dicari: ");
            String id = input.nextLine();

            if (dataPelanggan.containsKey(id)) {

                System.out.println(
                        "Nama Pelanggan: " + dataPelanggan.get(id)
                );

            } else {

                System.out.println(
                        "Pelanggan dengan ID tersebut tidak ditemukan."
                );
            }

        } else if (pilihan == 3) {

            System.out.println("\n========== DAFTAR PELANGGAN ==========");

            if (dataPelanggan.isEmpty()) {
                System.out.println("Belum ada pelanggan.");
                return;
            }

            System.out.println(
                    "Total Pelanggan: " + dataPelanggan.size()
            );

            for (String id : dataPelanggan.keySet()) {

                System.out.println(
                        "ID: " + id
                        + " | Nama: " + dataPelanggan.get(id)
                );
            }

        } else {

            System.out.println("Pilihan tidak valid.");
        }
    }
}
