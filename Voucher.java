package hostpot;
public class Voucher {
    private String kodeVoucher;
    private String paketVoucher;
    private double hargaVoucher;

    public Voucher(String kodeVoucher, String paketVoucher, double hargaVoucher) {
        this.kodeVoucher = kodeVoucher;
        this.paketVoucher = paketVoucher;
        this.hargaVoucher = hargaVoucher;
    }

    public String getKodeVoucher() {
        return kodeVoucher;
    }

    public String getPaketVoucher() {
        return paketVoucher;
    }

    public double getHargaVoucher() {
        return hargaVoucher;
    }
}