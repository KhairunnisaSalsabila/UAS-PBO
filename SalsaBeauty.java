import java.sql.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//Class Program
public class SalsaBeauty {
    // koneksi ke database
    static Connection conn;
    static String link = "jdbc:mysql://localhost:3306/salsa_beauty";
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        System.out.println("\n==========SALSA BEAUTY==========");
        System.out.println("\n         SELAMAT DATANG           ");
        String salamSapa = "  HALO!";
        String sapa = salamSapa.replace("HALO!", "Semoga Hari Anda Menyenangkan\n"); // method string
        System.out.println(sapa.toUpperCase());

        admin();

        menu();

        // date
        DateFormat formatTanggal = new SimpleDateFormat("dd MMMM yyyy");
        DateFormat formatJam = new SimpleDateFormat("HH:mm:ss");
        Date tanggal = new Date();

        // date
        System.out.println("================================");
        System.out.println("Dibuat pada   : " + formatTanggal.format(tanggal));
        System.out.println("Diupdate pada : " + formatJam.format(tanggal) + " WIB ");
        System.out.println("================================");
    }

    private static void admin() throws SQLException {
        // membuat objek hashmap baru
        Map<String, String> Login = new HashMap<String, String>();

        // membaca data di database tabel login
        String inputUsername, inputPassword;
        String sql = "SELECT * FROM login";
        boolean relogin = true;
        conn = DriverManager.getConnection(link, "root", "");
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);

        // perulangan while
        while (result.next()) {
            // mengambil nilai di database dan menyimpannya ke dalam variable, utk menu
            // login
            String username = result.getString("username");
            String password = result.getString("password");

            // input key dan value
            Login.put(username, password);
        }
        System.out.println("--------------------------------");

        // perulangan while
        while (relogin) {

            System.out.print("Username : ");
            inputUsername = input.nextLine();
            System.out.print("Password : ");
            inputPassword = input.nextLine();

            if (Login.get(inputUsername).equals(inputPassword) == true) {
                System.out.println("\n*Login Berhasil*");
                relogin = false;
            }

            if (Login.get(inputUsername).equals(inputPassword) == false) {
                System.out.println("\n*Login Gagal*");
                System.out.println("Masukkan Username dan Password yang Benar!\n");
                relogin = true;
            }
        }
        statement.close();
    }

    private static void menu() throws SQLException {
        boolean Kembali = true;
        boolean Tanya = true;
        Integer pilihan;
        String pertanyaan;

        // perulangan while
        while (Kembali) {
            System.out.println("--------------------------------");
            System.out.println("\n-------------MENU---------------");
            System.out.println("1. Lihat Jadwal Treatment");
            System.out.println("2. Input Treatment Baru");
            System.out.println("3. Ubah Jadwal Treatment");
            System.out.println("4. Booking");
            System.out.println("5. Batal Booking");
            System.out.println("6. Keluar");
            System.out.print("\nPilihan Anda : ");

            pilihan = input.nextInt();
            input.nextLine();
            JadwalTreatment jadwal_treatment = new JadwalTreatment();
            Booking booking = new Booking();

            // percabangan switch case
            switch (pilihan) {
                case 1:
                    jadwal_treatment.view();
                    Tanya = true;
                    break;

                case 2:
                    jadwal_treatment.insert();
                    Tanya = true;
                    break;

                case 3:
                    jadwal_treatment.update();
                    Tanya = true;
                    break;

                case 4:
                    booking.save();
                    Tanya = true;
                    break;

                case 5:
                    booking.delete();
                    Tanya = true;
                    break;

                case 6:
                    Tanya = false;
                    Kembali = false;
                    break;

                default:
                    System.out.println("Inputan Angka  1-6 !");
                    break;
            }

            // perulangan while
            while (Tanya) {
                System.out.print("\nKembali ke menu utama [y/n]? ");
                pertanyaan = input.nextLine();
                // percabangan if else if
                if (pertanyaan.equalsIgnoreCase("n")) // method string
                {
                    Kembali = false;
                    Tanya = false;
                } else if (pertanyaan.equalsIgnoreCase("y")) // method string
                {
                    Kembali = true;
                    Tanya = false;
                } else {
                    System.out.println("Inputkan 'y' atau 'n' saja");
                }
            }
        }
        System.out.println("\n\n\t\tSelesai\n");
    }
}