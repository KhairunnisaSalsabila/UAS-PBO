//class Pelanggan
public class Pelanggan {
    String id_pelanggan;
    String nama_pelanggan;

    // constructor
    public Pelanggan() {

    }

    // constructor
    public Pelanggan(String id_pelanggan, String nama_pelanggan) {
        this.id_pelanggan = id_pelanggan;
        this.nama_pelanggan = nama_pelanggan;
        System.out.println("\n");
        System.out.println(this.id_pelanggan + " tercatat sebagai member baru!");
        System.out.println("Atas nama " + this.nama_pelanggan);
    }

    public void method() {

    }
}