public class Voucher { //buat class voucher
    private String kodeVoucher; //buat atribut kodeVoucher bertipe data string dengan acccess modifer private
    private String paketVoucher; //buat atribut paketVoucher bertipe data string dengan acccess modifer private
    private double hargaVoucher; //buat atribut hargaVoucher bertipe data string dengan acccess modifer private

    public Voucher(String kodeVoucher, String paketVoucher, double hargaVoucher) { //buat constructor voucher dengan parameter semua atribut voucher
        this.kodeVoucher = kodeVoucher; //isi semua data atribur class dengan semua atribut parameter
        this.paketVoucher = paketVoucher;
        this.hargaVoucher = hargaVoucher;
    }

    public String getKodeVoucher() { //buat getter kodeVoucher
        return kodeVoucher;
    }

    public String getPaketVoucher() { //buat getter paketVoucher
        return paketVoucher;
    }

    public double getHargaVoucher() { //buat getter hargaVoucher
        return hargaVoucher;
    }
}
