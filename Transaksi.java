public class Transaksi {
    private String idTransaksi;
    private Pelanggan pelanggan;
    private Voucher voucher;

    public Transaksi(String idTransaksi, Pelanggan pelanggan, Voucher voucher) {
        this.idTransaksi = idTransaksi;
        this.pelanggan = pelanggan;
        this.voucher = voucher;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    public Voucher getVoucher() {
        return voucher;
    }
}
