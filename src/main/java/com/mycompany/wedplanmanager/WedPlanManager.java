
package com.mycompany.wedplanmanager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class WedPlanManager {

    static Scanner input = new Scanner(System.in);

    static PengelolaWedPlan wedPlan = new PengelolaWedPlan();

    static TransaksiPernikahan transaksi = new TransaksiPernikahan();

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
                        wedPlan.tampilkanSemuaPaket();
                        break;

                    case 3:
                        menuCariPaket();
                        break;

                    case 4:
                        prosesPaket();
                        break;

                    case 5:
                        System.out.println(
                                "Total paket: " + wedPlan.getJumlahPaket()
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

                    case 9:
                        wedPlan.tampilkanFasilitas();
                        break;

                    case 10:
                        prosesTransaksi();
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
        System.out.println("9. Lihat Fasilitas Wedding");
        System.out.println("10. Transaksi Pernikahan");
        System.out.println("0. Keluar");
        System.out.println("======================================");
    }

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

            wedPlan.tambahPaket(
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

            wedPlan.tambahPaket(
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

            wedPlan.tambahPaket(
                    new PaketSemiOutdoor(
                            nama, kode, harga, kapasitas, kanopi
                    )
            );

        } else {

            System.out.println("Jenis lokasi tidak valid.");
        }
    }

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

                PaketPernikahan hasil = wedPlan.cariPaket(keyword);
                hasil.tampilkanInfo();

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

            wedPlan.cariPaket(harga);

        } else {

            System.out.println("Pilihan tidak valid.");
        }
    }

    public static void prosesPaket(PaketPernikahan paket) {

        paket.tampilkanInfo();
        System.out.println("Paket berhasil diproses.");
    }

    public static void prosesPaket() {

        if (wedPlan.getJumlahPaket() == 0) {
            System.out.println("Belum ada paket.");
            return;
        }

        wedPlan.tampilkanSemuaPaket();

        System.out.print("Pilih nomor paket: ");
        int nomor = input.nextInt();
        input.nextLine();

        if (nomor >= 1 && nomor <= wedPlan.getJumlahPaket()) {

            prosesPaket(wedPlan.getPaket(nomor - 1));

        } else {

            System.out.println("Nomor paket tidak valid.");
        }
    }

    public static void pesanPaket() {

        if (wedPlan.getJumlahPaket() == 0) {
            System.out.println("Belum ada paket.");
            return;
        }

        wedPlan.tampilkanSemuaPaket();

        System.out.print("Pilih nomor paket yang dipesan: ");
        int nomor = input.nextInt();
        input.nextLine();

        if (nomor < 1 || nomor > wedPlan.getJumlahPaket()) {
            System.out.println("Nomor paket tidak valid.");
            return;
        }

        PaketPernikahan paket = wedPlan.getPaket(nomor - 1);

        if (paket instanceof DapatDipesan) {

            DapatDipesan pemesanan = (DapatDipesan) paket;
            pemesanan.pesanPaket();

        } else {

            System.out.println("Paket tidak mendukung pemesanan.");
        }
    }

    public static void prosesPembayaran() {

        if (wedPlan.getJumlahPaket() == 0) {
            System.out.println("Belum ada paket.");
            return;
        }

        wedPlan.tampilkanSemuaPaket();

        System.out.print("Pilih nomor paket yang dibayar: ");
        int nomor = input.nextInt();
        input.nextLine();

        if (nomor < 1 || nomor > wedPlan.getJumlahPaket()) {
            System.out.println("Nomor paket tidak valid.");
            return;
        }

        PaketPernikahan paket = wedPlan.getPaket(nomor - 1);

        if (paket instanceof DapatDibayar) {

            DapatDibayar pembayaran = (DapatDibayar) paket;
            pembayaran.prosesPembayaran();

        } else {

            System.out.println("Paket tidak mendukung pembayaran.");
        }
    }

    public static void tambahDataAwal() {

        wedPlan.tambahPaket(
                new PaketIndoor(
                        "Royal Indoor", "IND001",
                        25000000, 500, 20
                )
        );

        wedPlan.tambahPaket(
                new PaketIndoor(
                        "Elegant Ballroom", "IND002",
                        35000000, 800, 30
                )
        );

        wedPlan.tambahPaket(
                new PaketOutdoor(
                        "Garden Romance", "OUT001",
                        20000000, 1000, "Tenda Dome"
                )
        );

        wedPlan.tambahPaket(
                new PaketOutdoor(
                        "Beach Wedding", "OUT002",
                        30000000, 1500, "Tenda Transparan"
                )
        );

        wedPlan.tambahPaket(
                new PaketSemiOutdoor(
                        "Sunset Garden", "SEM001",
                        28000000, 600, "Kanopi Transparan"
                )
        );
    }

    public static void tambahPelangganAwal() {

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

            System.out.print("Masukkan Nama Pelanggan: ");
            String nama = input.nextLine();

            if (id.trim().isEmpty() || nama.trim().isEmpty()) {
                System.out.println("Data tidak boleh kosong.");
                return;
            }

            Pelanggan pelangganBaru = new Pelanggan(id, nama);

            wedPlan.tambahPelanggan(pelangganBaru);

        } else if (pilihan == 2) {

            System.out.print("Masukkan ID yang dicari: ");
            String id = input.nextLine();

            Pelanggan hasil = wedPlan.cariPelanggan(id);

            if (hasil != null) {
                hasil.tampilkanInfo();
            } else {
                System.out.println(
                        "Pelanggan dengan ID tersebut tidak ditemukan."
                );
            }

        } else if (pilihan == 3) {

            wedPlan.tampilkanSemuaPelanggan();

        } else {

            System.out.println("Pilihan tidak valid.");
        }
    }

    public static void prosesTransaksi() {

        System.out.println("\n========== TRANSAKSI PERNIKAHAN ==========");

        System.out.print("Masukkan ID Pelanggan: ");
        String id = input.nextLine();

        Pelanggan pelanggan = wedPlan.cariPelanggan(id);

        if (pelanggan == null) {
            System.out.println("Pelanggan tidak ditemukan.");
            return;
        }

        if (wedPlan.getJumlahPaket() == 0) {
            System.out.println("Belum ada paket yang tersedia.");
            return;
        }

        wedPlan.tampilkanSemuaPaket();

        System.out.print("Pilih nomor paket: ");
        int nomor = input.nextInt();
        input.nextLine();

        PaketPernikahan paket = wedPlan.getPaket(nomor - 1);

        if (paket != null) {

            transaksi.prosesTransaksi(pelanggan, paket);

        } else {

            System.out.println("Nomor paket tidak valid.");
        }
    }
}
