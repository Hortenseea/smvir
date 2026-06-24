public class Pelanggan {
    private String id;
    private String nama;
    private String nomorHP;

    public Pelanggan(String id, String nama, String nomorHP) {
        this.id = id;
        this.nama = nama;
        this.nomorHP = nomorHP;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getNomorHP() {
        return nomorHP;
    }
}
