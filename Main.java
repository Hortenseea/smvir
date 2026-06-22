package hostpot;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Pelanggan> listPelanggan = new ArrayList<>();  // buat listPelanggan untuk menyimpan data class pelanggan
    static ArrayList<Voucher> listVoucher = new ArrayList<>();  // buat listVoucher untuk menyimpan data class voucher
    static ArrayList<Transaksi> listTransaksi = new ArrayList<>();  //// buat listTransaksi untuk menyimpan data class Transaksi

    static Scanner input = new Scanner(System.in);  //buat objek input untuk input data

    public static void main(String[] args) {

        Admin admin = new Admin("admin", "123"); //buat objek admmin dengan parameter admin dan 123, untuk nantinya login sistem

        importVoucherCSV(); //memanggil method importVouvher

        boolean statusLogin = false;  //buat variabel statusLogin dengan tipe data boolean dan isi datanya false
        int percobaan = 0;  //buat variabel percobaan dengan tipe data integer dan data awalnya 0

        while (!statusLogin && percobaan < 3) {  //buat while untuk yg bisa di jalankan ketika 
                                                 //!statuslogin artinya kebalikan false yaitu true dan percobaan < 3

            System.out.print("Username : ");
            String username = input.nextLine(); //input username

            System.out.print("Password : ");
            String password = input.nextLine(); //input password

            if (admin.login(username, password)) {  //jika username & password input sesuai sama variabel username dan password di class admin, maka :
                statusLogin = true;  //status
                System.out.println("Login Berhasil"); //tampil login berhasil
            } else { //jika tida sesuai maka :
                percobaan++; //percobaan +1
                System.out.println("Login Gagal"); //tampil login gagal
            }
        }

        if (!statusLogin) {  //jika status login false, maka:
            System.out.println("Akses Ditolak"); //tampil akses di tolak
            return; //program berhenti
        }

        int pilihan; //buat variabel pilhan dengan tipe data integer

        do {
            System.out.println("\n===== MENU =====");    //tampil menu
            System.out.println("1. Registrasi Pelanggan");  //menu 1
            System.out.println("2. Import Voucher CSV");    //menu 2
            System.out.println("3. Lihat Stok Voucher");    //menu 3
            System.out.println("4. Input Transaksi");       //menu 4
            System.out.println("5. Riwayat Transaksi");     //menu 5
            System.out.println("6. Export Laporan CSV");    //menu 6
            System.out.println("7. Export Data Pelanggan"); //menu 7
            System.out.println("0. Keluar");                //menu 0

            System.out.print("Pilih : ");
            pilihan = Integer.parseInt(input.nextLine());  //input pilihan 

            switch (pilihan) {

                case 1:  //jika input 1
                    registrasiPelanggan(); //panggil method registrasiPelanggan
                    break;

                case 2: //jika input 2
                    importVoucherCSV(); //panggil method importVoucherCSV
                    break;

                case 3:  //jika input 3
                    lihatVoucher(); //panggil method lihatVoucher
                    break;

                case 4:  //jika input 4
                    inputTransaksi();   //panggil method inputTransaksi
                    break;

                case 5:  //jika input 5
                    riwayatTransaksi(); //panggil method riwayatTransaksi
                    break;

                case 6:  //jika input 6
                    exportCSV();    //panggil method exportCSV
                    break;
                        
                case 7:  //jika input 7
                    exportPelanggan();  //panggil method exportPelanggan
                    break;

                case 0:  //jika input 0
                    System.out.print("Apakah anda yakin ingin keluar? [y/n] : "); 
                    String konfirmasi = input.nextLine(); //input konfirmasi

                    if (!konfirmasi.equalsIgnoreCase("y")){ //jika konfirmasi bukan y
                        pilihan = -1; //balik ke tampil menu awal
                    } else { //jika input selain y
                        System.out.println("Program selesai"); // tampil program selesai
                        break; //program selesai
                    }    

                default:     //jika input selain pilihan
                    System.out.println("Menu Tidak Valid"); //tampil menu tidak valid
            }

        } while (pilihan != 0);
    }

    static void registrasiPelanggan() { //buat method registrasiPelanggan

        System.out.print("Nama : ");
        String nama = input.nextLine();

        System.out.print("Nomor HP : ");
        String hp = input.nextLine();

        String id = "PLG" + (listPelanggan.size() + 1);

        listPelanggan.add(new Pelanggan(id, nama, hp));

        System.out.println("Registrasi Berhasil");
    }

    static void importVoucherCSV() {

        try (BufferedReader br =
                     new BufferedReader(new FileReader("data/voucher.csv"))) {

            listVoucher.clear();

            String baris;

            while ((baris = br.readLine()) != null) {

                String[] data = baris.split(",");

                if (data.length >= 3) {

                    String kode = data[0];
                    String paket = data[1];
                    double harga = Double.parseDouble(data[2]);

                    listVoucher.add(
                            new Voucher(kode, paket, harga)
                    );
                }
            }

            System.out.println("Import Berhasil");

        } catch (Exception e) {
            System.out.println("File voucher.csv tidak ditemukan");
        }
    }

    static void lihatVoucher() {

        if (listVoucher.isEmpty()) {
            System.out.println("Stok Kosong");
            return;
        }

        for (Voucher v : listVoucher) {
            System.out.println(
                    v.getKodeVoucher() + " | "
                            + v.getPaketVoucher() + " | Rp"
                            + v.getHargaVoucher()
            );
        }
    }

    static void inputTransaksi() {

    if (listPelanggan.isEmpty()) {
        System.out.println("Belum Ada Pelanggan");
        return;
    }

    if (listVoucher.isEmpty()) {
        System.out.println("Stok Voucher Habis");
        return;
    }

    // Tampilkan pelanggan
    System.out.println("\n=== DAFTAR PELANGGAN ===");
    for (Pelanggan p : listPelanggan) {
        System.out.println(
                p.getId() + " - " + p.getNama()
        );
    }

    System.out.print("ID Pelanggan : ");
    String idCari = input.nextLine();

    Pelanggan pelangganDipilih = null;

    for (Pelanggan p : listPelanggan) {
        if (p.getId().equalsIgnoreCase(idCari)) {
            pelangganDipilih = p;
            break;
        }
    }

    if (pelangganDipilih == null) {
        System.out.println("Pelanggan Tidak Ditemukan");
        return;
    }

    // Tampilkan voucher
    System.out.println("\n=== DAFTAR VOUCHER ===");

    for (int i = 0; i < listVoucher.size(); i++) {

        Voucher v = listVoucher.get(i);

        System.out.println(
                (i + 1) + ". "
                        + v.getKodeVoucher()
                        + " | "
                        + v.getPaketVoucher()
                        + " | Rp"
                        + v.getHargaVoucher()
        );
    }

    System.out.print("Pilih Voucher : ");
    int pilihanVoucher =
            Integer.parseInt(input.nextLine());

    if (pilihanVoucher < 1
            || pilihanVoucher > listVoucher.size()) {

        System.out.println("Pilihan Tidak Valid");
        return;
    }

    Voucher voucher =
            listVoucher.remove(pilihanVoucher - 1);

    String idTransaksi =
            "TRX" + (listTransaksi.size() + 1);

    Transaksi trx =
            new Transaksi(
                    idTransaksi,
                    pelangganDipilih,
                    voucher
            );

    listTransaksi.add(trx);

    System.out.println("\n===== STRUK =====");
    System.out.println("ID Transaksi : "
            + trx.getIdTransaksi());
    System.out.println("Nama Pelanggan : "
            + pelangganDipilih.getNama());
    System.out.println("Kode Voucher : "
            + voucher.getKodeVoucher());
    System.out.println("Paket : "
            + voucher.getPaketVoucher());
    System.out.println("Harga : Rp"
            + voucher.getHargaVoucher());
}

    static void riwayatTransaksi() {

        if (listTransaksi.isEmpty()) {
            System.out.println("Belum Ada Transaksi");
            return;
        }

        for (Transaksi trx : listTransaksi) {

            System.out.println(
                    trx.getIdTransaksi()
                            + " | "
                            + trx.getPelanggan().getNama()
                            + " | "
                            + trx.getVoucher().getKodeVoucher()
                            + " | "
                            + trx.getVoucher().getPaketVoucher()
                            + " | Rp"
                            + trx.getVoucher().getHargaVoucher()
            );
        }
    }

    static void exportCSV() {
        
         if (listTransaksi.isEmpty()) {
            System.out.println("Belum Ada Transaksi");
            return;
        }

        try (PrintWriter pw =
                     new PrintWriter("output/transaksi.csv")) {

            pw.println(
                    "ID,Nama,KodeVoucher,Paket,Harga"
            );

            for (Transaksi trx : listTransaksi) {

                pw.println(
                        trx.getIdTransaksi() + ","
                                + trx.getPelanggan().getNama() + ","
                                + trx.getVoucher().getKodeVoucher() + ","
                                + trx.getVoucher().getPaketVoucher() + ","
                                + trx.getVoucher().getHargaVoucher()
                );
            }

            System.out.println("Export Berhasil");

        } catch (Exception e) {
            System.out.println("Export Gagal" + e.getMessage());
        }
    }
    
     
    static void exportPelanggan() {

    try (PrintWriter pw = new PrintWriter("output/pelanggan.csv")) {

        pw.println("ID,Nama,NoTelp");

        for (Pelanggan p : listPelanggan) {
            pw.println(
                    p.getId() + ","
                    + p.getNama() + ","
                    + p.getNomorHP()
            );
        }

        System.out.println("Export Pelanggan Berhasil");

    } catch (Exception e) {
        System.out.println("Export Gagal");
        e.printStackTrace();
    }
}
}
