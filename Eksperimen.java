// Nama dan NIM Team Assignment 3 DSAA (LEC)
// CHRISTIAN HOTASI VICCARE HUTAGAOL - 2902804014
// FAIZA KURNIAWATI - 2902807880
// ARI JUNIANTO - 2902828701
// UTAS PRASOJO - 2902809551
// HANDSON PANGGABEAN - 2902820005

// Program eksperimen pengukuran waktu
public class Eksperimen {
    static final int PEMANASAN = 20000; // putaran warm-up (JIT)
    static final int ULANG = 100000; // jumlah pengulangan per pengukuran

    static double sink = 0; // mencegah hasil dibuang oleh JIT

    public static void main(String[] args) {
        Lagu[] p = PlaylistRekursif.playlist;
        int[] ukuran = {2, 3, 4, 5, 6, 8, 10};

        System.out.println("n,totalDurasi_ms,tampilkanMundur_ms,cariDurasiTerpanjang_ms");

        for (int n : ukuran) {
            // Pemanasan
            for (int i = 0; i < PEMANASAN; i++) {
                sink += PlaylistRekursif.totalDurasi(p, n);
                sink += PlaylistRekursif.cariDurasiTerpanjang(p, n - 1);
                StringBuilder sb = new StringBuilder();
                PlaylistRekursif.susunMundur(p, n - 1, sb);
                sink += sb.length();
            }

            // Ukur totalDurasi: ulangi ULANG kali
            long t0 = System.nanoTime();
            for (int i = 0; i < ULANG; i++) {
                sink += PlaylistRekursif.totalDurasi(p, n);
            }
            double total = (System.nanoTime() - t0) / 1_000_000.0 / ULANG; // simpan rata-ratanya (ms)

            // Ukur susunMundur (versi tanpa print dari tampilkanMundur)
            t0 = System.nanoTime();
            for (int i = 0; i < ULANG; i++) {
                StringBuilder sb = new StringBuilder();
                PlaylistRekursif.susunMundur(p, n - 1, sb);
                sink += sb.length();
            }
            double mundur = (System.nanoTime() - t0) / 1_000_000.0 / ULANG; // simpan rata-ratanya (ms)

            // Ukur cariDurasiTerpanjang
            t0 = System.nanoTime();
            for (int i = 0; i < ULANG; i++) {
                sink += PlaylistRekursif.cariDurasiTerpanjang(p, n - 1);
            }
            double cari = (System.nanoTime() - t0) / 1_000_000.0 / ULANG; // simpan rata-ratanya (ms)

            System.out.printf("%d,%.8f,%.8f,%.8f%n", n, total, mundur, cari);
        }
        System.err.println("(sink=" + sink + ")");
    }
}