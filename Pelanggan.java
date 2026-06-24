public class Pelanggan { //buat class pelanggan
    private String id;  //buat variabel id bertipe data string dsengan acces modifer private
    private String nama;    //buat variabel nama bertipe data string dsengan acces modifer private
    private String nomorHP;    //buat variabel nomorHP bertipe data string dsengan acces modifer private

    public Pelanggan(String id, String nama, String nomorHP) { //buat constructor pelanggan dengan parameter id,nama,nomorHP
        this.id = id;  //isi variabel class id dengan variabel parameter id
        this.nama = nama;    //isi variabel class nama dengan variabel parameter nama
        this.nomorHP = nomorHP;    //isi variabel class id dengan variabel parameter id
    }

    public String getId() {  //buat getter untuk atribur id
        return id;
    }

    public String getNama() { //buat getter untuk atribur nama
        return nama;
    }

    public String getNomorHP() {    //buat getter untuk atribur nomorHP
        return nomorHP;
    }
}
