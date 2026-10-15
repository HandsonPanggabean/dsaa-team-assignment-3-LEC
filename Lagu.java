// Nama dan NIM Team Assignment 3 DSAA (LEC)
// CHRISTIAN HOTASI VICCARE HUTAGAOL - 2902804014
// FAIZA KURNIAWATI - 2902807880
// ARI JUNIANTO - 2902828701
// UTAS PRASOJO - 2902809551
// HANDSON PANGGABEAN - 2902820005

public class Lagu {
    // Atribut untuk menyimpan informasi lagu
    private String judul;
    private String artis;
    private double durasi;

    // Constructor untuk membuat objek Lagu
    public Lagu(String judul, String artis, double durasi) {
        this.judul = judul;
        this.artis = artis;
        this.durasi = durasi;
    }

    // Method untuk menampilkan informasi lagu
    public void tampilkanInfo() {
        System.out.println("Judul  : " + judul);
        System.out.println("Artis  : " + artis);
        System.out.println("Durasi : " + String.format("%.2f", durasi) + " menit");
    }

    // Getter judul
    public String getJudul() {
        return judul;
    }

    // Getter artis
    public String getArtis() {
        return artis;
    }

    // Getter durasi
    public double getDurasi() {
        return durasi;
    }
}