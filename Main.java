package hostpot;
import java.io.*; //mengimport semua library input output
import java.util.ArrayList; //mengimpor struktur data arraylist
import java.util.Scanner;  //mengimpor library untuk input

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
        String nama = input.nextLine(); //input nama

        System.out.print("Nomor HP : ");
        String hp = input.nextLine(); //input no hp

        String id = "PLG" + (listPelanggan.size() + 1);  //buat variabel id yg isinya PLG+1 dan trus bertambah jika ada data baru

        listPelanggan.add(new Pelanggan(id, nama, hp));  //membuat objek pelanggan dan memasukan data id,nama,no hp ke parameter

        System.out.println("Registrasi Berhasil"); //tampil registrasi berhasil
    }

    static void importVoucherCSV() {  //buat method importVoucherCSV

        try (BufferedReader br =
                     new BufferedReader(new FileReader("data/voucher.csv"))) {  //Membuka file voucher.csv di folder data. Menggunakan try-catch 
                                                                                //agar program tidak crash jika file tidak ditemukan.

            listVoucher.clear();  //mengosogan data list voucher

            String baris;  //buat variabel baris

            while ((baris = br.readLine()) != null) {  //membaca file bari perbaris hingga baris null

                String[] data = baris.split(",");  //memotong baris teks saat ada koma(,)

                if (data.length >= 3) { //jika panjang data lebih dari atau sama dengan 3 maka,

                    String kode = data[0]; //variabel kode isinya data indeks ke 0
                    String paket = data[1];  //variabel paket isinya data indeks ke 1
                    double harga = Double.parseDouble(data[2]);  //variabel harga isinya data indeks ke 2 dan di ubah ke double

                    listVoucher.add(
                            new Voucher(kode, paket, harga)  //menambahan data di atas tadi ke list voucher
                    );
                }
            }

            System.out.println("Import Berhasil"); //tampil import berhasil

        } catch (Exception e) {
            System.out.println("File voucher.csv tidak ditemukan");  //jika file tidak di temukan
        }
    }

    static void lihatVoucher() {  //buat method lihatVoucher

        if (listVoucher.isEmpty()) {        //jika data listVoucher kosong
            System.out.println("Stok Kosong");  //maka tampil stok kosong
            return;
        }

        for (Voucher v : listVoucher) {  //memanggil setiap setiap objek voucher dan di wakilkan dengan variabel v
            System.out.println(
                    v.getKodeVoucher() + " | "  //ambil semua data atribut kode voucher
                            + v.getPaketVoucher() + " | Rp"  //ambil semua data paket voucher
                            + v.getHargaVoucher()        // //ambil semua data harga voucher
            );
        }
    }

    static void inputTransaksi() {    //buat method inputTransaksi

    if (listPelanggan.isEmpty()) {   //jika data list pelanggan kosong maka
        System.out.println("Belum Ada Pelanggan"); // tampil belum ada pelanggan
        return;  //method selesai
    }

    if (listVoucher.isEmpty()) {   //jika data list voucher kosong maka
        System.out.println("Stok Voucher Habis"); //stok voucher habis
        return; //method selesai
    }

    // Tampilkan pelanggan
    System.out.println("\n=== DAFTAR PELANGGAN ===");
    for (Pelanggan p : listPelanggan) {  //ambil semu object pelanggan dan di wakilkan variabel p
        System.out.println(
                p.getId() + " - " + p.getNama() // ambil data nama
        );
    }

    System.out.print("ID Pelanggan : ");
    String idCari = input.nextLine();  //input id pelanggan yg di cari

    Pelanggan pelangganDipilih = null;  // buat variabel pelanggadipilih dan isinya null

    for (Pelanggan p : listPelanggan) {  //ambil semua objek pelanggan dari list pelanggan dan di wakilkan di variabel p
        if (p.getId().equalsIgnoreCase(idCari)) {  //mengecek apakah id yg di input ada di data list
            pelangganDipilih = p; //jika ada makan masukan ke variabel pelanggan di pilih
            break;  // proses selesai
        }
    }

    if (pelangganDipilih == null) {  // jika isi pelanggandipilih kosong maka
        System.out.println("Pelanggan Tidak Ditemukan"); //tampil pelanggan tidak di temukan
        return; //proses selesai
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
            Integer.parseInt(input.nextLine());  // input voucher yg di pilih

    if (pilihanVoucher < 1
            || pilihanVoucher > listVoucher.size()) {  //jika input kurang dari 1 dan lebih dari banyaknya data listvoucher

        System.out.println("Pilihan Tidak Valid"); //maka tampil pilihan tidak valid
        return; 
    }

    Voucher voucher =
            listVoucher.remove(pilihanVoucher - 1); //hapus data list voucher yg di pilih kurang 1

    String idTransaksi =
            "TRX" + (listTransaksi.size() + 1);  //buat id transkasi 

    Transaksi trx =   
            new Transaksi(         //buat objek transaksi yaitu trx dan langsung di isi parameternya
                    idTransaksi,
                    pelangganDipilih,
                    voucher
            );

    listTransaksi.add(trx);   // tambahkna data transaksi tadi ke dalma listtransaksi

    System.out.println("\n===== STRUK =====");  // tampil struk terkait transaksi
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

    static void riwayatTransaksi() {  // buat method riwayatTransaksi

        if (listTransaksi.isEmpty()) {   //jika list transaksi kosong maka
            System.out.println("Belum Ada Transaksi"); //tampil belum ada transaksi
            return;
        }

        for (Transaksi trx : listTransaksi) {  //ambil semua objek transaksi dan taro di variabel trx

            System.out.println(
                    trx.getIdTransaksi()
                            + " | "
                            + trx.getPelanggan().getNama()  //ambil data nama
                            + " | "
                            + trx.getVoucher().getKodeVoucher() //ambil data ode voucher
                            + " | "
                            + trx.getVoucher().getPaketVoucher() //ambil data paket voucher
                            + " | Rp"
                            + trx.getVoucher().getHargaVoucher() ////ambil data harga voucher
            );
        }
    }

    static void exportCSV() {  //buat methode exportCSV
        
         if (listTransaksi.isEmpty()) {
            System.out.println("Belum Ada Transaksi");  //validasi jika list transaksi kosong maka tampil belum ada transaksi
            return;  /proses selesai
        }

        try (PrintWriter pw =
                     new PrintWriter("output/transaksi.csv")) { // membuat file transaksi.csv

            pw.println(
                    "ID,Nama,KodeVoucher,Paket,Harga"  //menuliskan ke dalam baris di file transaksi.csv
            );

            for (Transaksi trx : listTransaksi) {   //ambil semua objek transaksi dan taro di variabel trx

                pw.println(
                        trx.getIdTransaksi() + ","
                                + trx.getPelanggan().getNama() + ","
                                + trx.getVoucher().getKodeVoucher() + ","   //tulisa semua data ke file transaksi.csv
                                + trx.getVoucher().getPaketVoucher() + ","
                                + trx.getVoucher().getHargaVoucher()
                );
            }

            System.out.println("Export Berhasil");  //tampil Export berhasil

        } catch (Exception e) {
            System.out.println("Export Gagal" + e.getMessage()); //catch jia export gagal atau ada eror, program tidak langsung berhenti
        }
    }
    
     
    static void exportPelanggan() {   //buat method exportPelanggan

    try (PrintWriter pw = new PrintWriter("output/pelanggan.csv")) {  // membuat file pelanggan.csv

        pw.println("ID,Nama,NoTelp"); //menuliskan ke dalam baris di file pelanggan.csv

        for (Pelanggan p : listPelanggan) { /ambil semua objek pelanggan dari list pelanggan dan di wakilkan di variabel p
            pw.println(
                    p.getId() + ","
                    + p.getNama() + "," //tulisakn ke dalam file pelanggan.csv
                    + p.getNomorHP()
            );
        }

        System.out.println("Export Pelanggan Berhasil"); //tampil export berhsil

    } catch (Exception e) {
        System.out.println("Export Gagal"); /catch jia export gagal atau ada eror, program tidak langsung berhenti
        e.printStackTrace();
    }
}
}
