public class Transaksi {        //buat class Transaksi
    private String idTransaksi; //buat atribut idTransaksi bertipe data string dengan acccess modifer private
    private Pelanggan pelanggan;    //buat atribut pelanggan bertipe data class pelanggan dengan acccess modifer private
    private Voucher voucher;    //buat atribut voucher bertipe data class voucher dengan acccess modifer private

    public Transaksi(String idTransaksi, Pelanggan pelanggan, Voucher voucher){//buat constructor dengan parameter semua atribur class transaksi
        this.idTransaksi = idTransaksi; //isi semua data variabel class dengan variabel parameter
        this.pelanggan = pelanggan;
        this.voucher = voucher;
    }

    public String getIdTransaksi() { //buat getter untu atribut idTransaksi
        return idTransaksi;
    }

    public Pelanggan getPelanggan() { //buat getter untu atribut pelanggan
        return pelanggan;
    }

    public Voucher getVoucher() { //buat getter untu atribut voucher
        return voucher;
    }
}
