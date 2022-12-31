import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

//class JadwalTreatment
public class JadwalTreatment implements Database // class JadwalTreatment mengimplementasikan interface Database
{
    // koneksi ke database
    Connection conn;
    String link = "jdbc:mysql://localhost:3306/salsa_beauty";
    Scanner input = new Scanner(System.in);

    String id_treatment;
    String nama_treatment;
    String jadwal;
    Integer harga;

    public void method() {

    }

    // implementasi dari interface

    @Override
    public void insert() throws SQLException {
        System.out.print("\n----Tambah Jadwal Treatment----");
        System.out.print("\nID Treatment      : ");
        this.id_treatment = input.nextLine();
        System.out.print("Nama Treatment    : ");
        this.nama_treatment = input.nextLine();
        System.out.print("Jadwal            : ");
        this.jadwal = input.nextLine();
        System.out.print("Harga             : ");
        this.harga = input.nextInt();

        String sql = "INSERT INTO jadwal_treatment (id_treatment, nama_treatment, jadwal, harga) VALUES ('"
                + id_treatment + "','" + nama_treatment + "','" + jadwal + "', '" + harga + "' )";
        conn = DriverManager.getConnection(link, "root", "");
        Statement statement = conn.createStatement();
        statement.execute(sql);
        System.out.println("\nInput Data Berhasil!");

        statement.close();
    }

    @Override
    public void view() throws SQLException {
        // akses data yang ada di database jadwal_treatment
        String sql = "SELECT * FROM jadwal_treatment";
        conn = DriverManager.getConnection(link, "root", "");
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()) {
            System.out.print("\nID Treatment      : ");
            System.out.println(result.getString("id_treatment"));
            System.out.print("Nama Treatment    : ");
            System.out.println(result.getString("nama_treatment"));
            System.out.print("Jadwal            : ");
            System.out.println(result.getString("jadwal"));
            System.out.print("Harga             : ");
            System.out.println(result.getInt("harga"));
        }

        statement.close();
    }

    @Override
    public void update() throws SQLException {
        // try
        try {
            view();
            Integer pil;
            System.out.print("\n-----Ubah Jadwal Treatment-----");
            System.out.print("\nID Treatment : ");
            String ubah = input.nextLine();

            // akses data database tabel jadwal_treatment
            String sql = "SELECT * FROM jadwal_treatment WHERE id_treatment ='" + ubah + "'";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            // percabangan if
            if (result.next()) {
                System.out.println("\n-----Data yang akan diubah-----");
                System.out.println("1. Jadwal Treatment");
                System.out.println("2. Harga Treatment");
                System.out.print("\nPilihan Anda  : ");
                pil = input.nextInt();
                input.nextLine();

                // percabangan switch case
                switch (pil) {
                    case 1:
                        System.out.print("\nJadwal Treatment [" + result.getString("jadwal") + "] : ");
                        String ubah1 = input.nextLine();
                        // update data database tabel jadwal_treatment
                        sql = "UPDATE jadwal_treatment SET jadwal = '" + ubah1 + "' WHERE id_treatment ='" + ubah + "'";
                        if (statement.executeUpdate(sql) > 0) {
                            System.out.println("Berhasil Memperbarui Jadwal Treatment " + ubah + "!");
                        }
                        break;

                    case 2:
                        System.out.print("\nHarga Treatment [" + result.getInt("harga") + "] : ");
                        Integer ubah2 = input.nextInt();
                        // update data database tabel jadwal_treatment
                        sql = "UPDATE jadwal_treatment SET harga = " + ubah2 + " WHERE id_treatment ='" + ubah + "'";
                        input.nextLine();
                        if (statement.executeUpdate(sql) > 0) {
                            System.out.println("Berhasil Memperbarui Harga Treatment " + ubah + "!");
                        }
                        break;

                    default:
                        System.out.println("\n\tERROR");
                        System.out.println("Input Angka 1 atau 2!");
                        break;
                }
            }

            else {
                System.out.println("ERROR !");
            }
        }

        // exeption SQL
        catch (SQLException e) {
            System.err.println("Update Data Gagal");
        }

        // exception input tidak sesuai jenis data
        catch (InputMismatchException e) {
            System.err.println("Gagal! Masukkan Data yang Benar");
        }
    }

    public void save() throws SQLException {

    }

    public void delete() throws SQLException {

    }
}
