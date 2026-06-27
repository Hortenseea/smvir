public class Pelanggan {  //buat class pelanggan
    private String id;  //buat atribut id bertipe data string dengan access modifer private
    private String nama;  //buat atribut nama bertipe data string dengan access modifer private
    private String nomorHP;  //buat atribut nomorHP bertipe data string dengan access modifer private

    public Pelanggan(String id, String nama, String nomorHP) { //buat constructor untu pelanggan dengan parameter id,nama,nomorHP
        this.id = id;  //isi variabel class yaitu id dengan variabel parameter id
        this.nama = nama;   //isi variabel class yaitu nama dengan variabel parameter nama
        this.nomorHP = nomorHP;   //isi variabel class yaitu nomorHP dengan variabel parameter nomorHP
    }

    public String getId() {  //buat getter untu atribut id
        return id;
    }

    public String getNama() {     //buat getter untu atribut nama
        return nama;
    }

    public String getNomorHP() {  //buat getter untu atribut nomorHP
        return nomorHP;
    }
}
