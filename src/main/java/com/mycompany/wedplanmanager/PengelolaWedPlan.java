
package com.mycompany.wedplanmanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class PengelolaWedPlan {

    private ArrayList<PaketPernikahan> daftarPaket;
    private HashMap<String, Pelanggan> dataPelanggan;
    private FasilitasPernikahan fasilitas;

    public PengelolaWedPlan() {
        daftarPaket = new ArrayList<>();
        dataPelanggan = new HashMap<>();
        fasilitas = new FasilitasPernikahan(
                "Fasilitas Wedding",
                "Dekorasi, Sound System, dan Dokumentasi"
        );
    }

    public void tambahPaket(PaketPernikahan paket) {
        daftarPaket.add(paket);
        System.out.println("Paket berhasil ditambahkan.");
    }

    public void tambahPelanggan(Pelanggan pelanggan) {
        String id = pelanggan.getIdPelanggan();

        if (dataPelanggan.containsKey(id)) {
            System.out.println("ID pelanggan sudah terdaftar.");
        } else {
            dataPelanggan.put(id, pelanggan);
            System.out.println("Pelanggan berhasil ditambahkan.");
        }
    }

    public Pelanggan cariPelanggan(String id) {
        return dataPelanggan.get(id);
    }

    public void tampilkanSemuaPelanggan() {
        System.out.println("\n========== DAFTAR PELANGGAN ==========");

        if (dataPelanggan.isEmpty()) {
            System.out.println("Belum ada pelanggan.");
            return;
        }

        for (String id : dataPelanggan.keySet()) {
            dataPelanggan.get(id).tampilkanInfo();
            System.out.println("----------------------------------");
        }

        System.out.println("Total Pelanggan: " + dataPelanggan.size());
    }

    public PaketPernikahan cariPaket(String keyword)
            throws PaketTidakDitemukanException {

        for (int i = 0; i < daftarPaket.size(); i++) {
            PaketPernikahan paket = daftarPaket.get(i);

            if (paket.getNamaPaket().equalsIgnoreCase(keyword)
                    || paket.getKodePaket().equalsIgnoreCase(keyword)) {
                return paket;
            }
        }

        throw new PaketTidakDitemukanException(
                "Paket dengan nama atau kode tersebut tidak ditemukan!"
        );
    }

    public void cariPaket(double hargaMaksimal) {
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

    public void tampilkanSemuaPaket() {
        System.out.println("\n========== DAFTAR PAKET ==========");

        if (daftarPaket.isEmpty()) {
            System.out.println("Belum ada paket.");
            return;
        }

        for (int i = 0; i < daftarPaket.size(); i++) {
            System.out.println("\n========== PAKET " + (i + 1) + " ==========");
            daftarPaket.get(i).tampilkanInfo();
        }

        System.out.println("\nTotal paket: " + daftarPaket.size());
    }

    public PaketPernikahan getPaket(int index) {
        if (index >= 0 && index < daftarPaket.size()) {
            return daftarPaket.get(index);
        }

        return null;
    }

    public int getJumlahPaket() {
        return daftarPaket.size();
    }

    public void tampilkanFasilitas() {
        System.out.println("\n========== FASILITAS WEDPLAN ==========");
        fasilitas.tampilkanFasilitas();
    }

    public void simpanDataKeFile() {
        try {
            FileOutputStream fileKeluar =
                    new FileOutputStream("data_wedplan.dat");

            ObjectOutputStream objekKeluar =
                    new ObjectOutputStream(fileKeluar);

            objekKeluar.writeObject(daftarPaket);
            objekKeluar.writeObject(dataPelanggan);

            objekKeluar.close();

            System.out.println("Data paket dan pelanggan berhasil disimpan.");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void muatDataDariFile() {
        File file = new File("data_wedplan.dat");

        if (!file.exists()) {
            System.out.println("File data belum tersedia. Menggunakan data awal.");
            return;
        }

        try {
            FileInputStream fileMasuk =
                    new FileInputStream(file);

            ObjectInputStream objekMasuk =
                    new ObjectInputStream(fileMasuk);

            daftarPaket =
                    (ArrayList<PaketPernikahan>) objekMasuk.readObject();

            dataPelanggan =
                    (HashMap<String, Pelanggan>) objekMasuk.readObject();

            objekMasuk.close();

            System.out.println("Data paket dan pelanggan berhasil dimuat.");
            System.out.println("Total paket: " + daftarPaket.size());
            System.out.println("Total pelanggan: " + dataPelanggan.size());

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Gagal memuat data: " + e.getMessage());
        }
    }

    public void bacaLogTransaksi() {
        File file = new File("log_transaksi.txt");

        System.out.println("\n========== RIWAYAT TRANSAKSI ==========");

        if (!file.exists()) {
            System.out.println("Belum ada file riwayat transaksi.");
            return;
        }

        try {
            Scanner mesinBaca = new Scanner(file);

            while (mesinBaca.hasNextLine()) {
                System.out.println(mesinBaca.nextLine());
            }

            mesinBaca.close();

        } catch (FileNotFoundException e) {
            System.out.println("Gagal membaca file: " + e.getMessage());
        }
    }
}
