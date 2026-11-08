
package com.mycompany.wedplanmanager;

import java.util.ArrayList;
import java.util.HashMap;

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
}
