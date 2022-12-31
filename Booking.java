import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

//class Booking
public class Booking extends JadwalTreatment { // merupakan polymorphysm dari subclass ke superclass
    // koneksi ke database
    Connection conn;
    String link = "jdbc:mysql://localhost:3306/salsa_beauty";

    Scanner input = new Scanner(System.in);
    String tanggal_booking, id_pelanggan, nama_pelanggan, id_treatment;
    Integer jmlorangMax = 3, treatment_tersedia, treatment_dipesan, total_harga;

    public void tanggal_booking() {
        System.out.print("Tanggal Booking  : ");
        this.tanggal_booking = input.nextLine();
    }

    public void id_pelanggan() {
        System.out.print("ID Pelanggan     : ");
        this.id_pelanggan = input.nextLine();

    }

    public void nama_pelanggan() {
        System.out.print("Nama Pelanggan   : ");
        this.nama_pelanggan = input.nextLine();
    }

    public void id_treatment() {
        System.out.print("ID Treatment     : ");
        this.id_treatment = input.nextLine();
    }

    public void harga() {
        System.out.print("Harga            : ");
        this.harga = input.nextInt();
    }

    public void jumlah_orang() {
        // maksimum 2 orang pada setiap treatment
        System.out.print("Jumlah Orang     : ");
        treatment_dipesan = input.nextInt();
        {
            try {
                if (treatment_dipesan <= 0 && treatment_dipesan > 2)
                    System.out.print("Treatment Tidak Tersedia");
            }

            catch (Exception nullpException) {
                System.out.println("\nERROR!\n");
            }
        }
    }

    public void total_harga() {
        // Total harga yang harus dibayar pelanggan
        this.total_harga = this.harga * this.treatment_dipesan;
        System.out.print("\nTotal Harga      : Rp" + this.total_harga + "");
    }

    public void pelanggan() throws SQLException {
        Pelanggan pelanggan = new Pelanggan(this.id_pelanggan, this.nama_pelanggan);
        pelanggan.method();
    }

    @Override
    public void save() throws SQLException {
        try {
            view();
            System.out.print("\n-------------Booking------------\n");
            tanggal_booking();
            id_pelanggan();
            nama_pelanggan();
            id_treatment();
            jumlah_orang();
            harga();
            total_harga();

            pelanggan();

            conn = DriverManager.getConnection(link, "root", "");
            Statement statement = conn.createStatement();
            String sql = "INSERT INTO booking (tanggal_booking, id_pelanggan, nama_pelanggan, id_treatment, jumlah_orang, harga) VALUES ('"
                    + this.tanggal_booking + "', '" + this.id_pelanggan + "', '" + this.nama_pelanggan + "', '"
                    + this.id_treatment + "','" + this.treatment_dipesan + "', '" + this.harga + "')";
            statement.execute(sql);
            System.out.println("\nBooking Berhasil");

        }

        // exception SQL
        catch (SQLException e) {
            System.err.println("\nBooking Gagal");
        }

        // excception input tidak sesuai dengan tipe data
        catch (InputMismatchException e) {
            System.out.println("\nTipe Inputan Data Harus Benar");
        }
    }

    @Override
    public void delete() throws SQLException {
        try {
            System.out.println("\n--Pembatalan Booking Treatment--");
            System.out.println("ID Pelanggan : ");
            this.id_pelanggan = input.next();

            String sql = "DELETE FROM booking WHERE id_pelanggan = " + id_pelanggan;
            conn = DriverManager.getConnection(link, "root", "");
            Statement statement = conn.createStatement();
            // ResultSet result = statement.executeQuery(sql);

            if (statement.executeUpdate(sql) > 0) {
                System.out.println("*Berhasil Menghapus Booking " + id_pelanggan + "*");
            }
        }

        catch (Exception e) {
            System.out.println("Masukkan ID Pelanggan Yang Benar");
        }

    }

}
