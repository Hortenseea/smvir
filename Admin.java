import java.util.*; 

public class Admin {
    private String username;  //buat variabel username dengan accese modifer private
    private String password; //buat variabel password dengan accese modifer private

    public Admin(String username, String password) {  //buat constructor dengan parameter username dan password
        this.username = username; //memberikan nilai variabel username dengan variabel parameter username
        this.password = password; //memberikan nilai variabel password dengan variabel parameter password
    }

    public boolean login(String username, String password) {  //buat method login dengan parameter username dan password
        return this.username.equals(username)    //mengembalikan nilai jika username dan password parameter sesuai dengan variabel class
                && this.password.equals(password);
    }
}
