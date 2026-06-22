# Sistem Manajemen Penjualan Voucher Internet Rumahan (CLI)

Program Java sederhana berbasis CLI (Command Line Interface) yang dirancang khusus sebagai alat bantu bagi penjual voucher internet rumahan. Program ini berfungsi sebagai sistem kasir mini yang membantu mengelola data pelanggan, stok voucher, dan laporan transaksi secara terstruktur.

## 🚀 Fitur Utama

* **Autentikasi Admin:** Sistem login untuk keamanan akses data.
* **Manajemen Pelanggan:** Registrasi data pelanggan baru.
* **Manajemen Voucher:** Impor stok voucher dari file CSV.
* **Transaksi:** Pemrosesan penjualan voucher dengan pembaruan stok otomatis (pengurangan stok).
* **Laporan:** Riwayat transaksi yang dapat dilihat langsung atau diekspor ke format CSV.

## 📂 Struktur Proyek

```text
src/
├── Main.java         # Entry point aplikasi
├── Admin.java        # Logika autentikasi admin
├── Pelanggan.java    # Model data pelanggan
├── Voucher.java      # Model data voucher
├── Transaksi.java    # Model data transaksi
└── Data/
    └── voucher.csv   # Database sumber voucher

```

## 🛠️ Alur Kerja (Pseudocode)

Program mengikuti alur sistem manajemen transaksi standar:

1. **Login:** Validasi akses admin (maksimal 3 kali percobaan).
2. **Menu Utama:**
* Registrasi Pelanggan.
* Import Voucher (via file CSV).
* Cek Stok Voucher.
* Proses Transaksi Penjualan.
* Lihat Riwayat Transaksi.
* Ekspor Laporan ke CSV.
* Keluar.



## ⚙️ Persyaratan Sistem

* **Java Development Kit (JDK):** Versi 8 atau lebih baru.
* **Lingkungan:** Terminal/Command Prompt untuk menjalankan aplikasi.

## 📝 Panduan Penggunaan

1. **Persiapan Data:** Pastikan file `voucher.csv` sudah tersedia di dalam folder `src/Data/` dengan format yang sesuai.
2. **Kompilasi:**
```bash
javac src/*.java

```


3. **Menjalankan Program:**
```bash
java src.Main

```


4. **Ikuti Instruksi:** Masukkan kredensial admin dan pilih menu yang tersedia melalui CLI.

## 🤝 Kontributor

Proyek ini dikembangkan sebagai tugas Projek UAS.

* Annurman Hakim Pradana (2025340014)
* Rasya Pramduya Teja (2025310027)
