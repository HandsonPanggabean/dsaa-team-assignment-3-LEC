// Nama dan NIM Team Assignment 3 DSAA (LEC)
// CHRISTIAN HOTASI VICCARE HUTAGAOL - 2902804014
// FAIZA KURNIAWATI - 2902807880
// ARI JUNIANTO - 2902828701
// UTAS PRASOJO - 2902809551
// HANDSON PANGGABEAN - 2902820005

/*
    * Eksperimen tambahan: mengukur waktu rekursi untuk n yang besar (1000 sampai 5000) agar pola pertumbuhan linear O(n) terlihat jelas.
    * Playlist uji dibuat otomatis (tidak memakai playlist asli yang hanya 20 lagu).
*/
public class Besar {
    static double sink = 0; // dipakai agar hasil fungsi tidak dibuang oleh optimasi JIT

    public static void main(String[] args) {
        System.out.println("n,totalDurasi_ms,cariDurasiTerpanjang_ms");
        for (int n = 1000; n <= 5000; n += 1000) {
            // Buat playlist uji sebanyak n lagu dengan durasi yang bervariasi
            Lagu[] p = new Lagu[n];

            for (int i = 0; i < n; i++) {
                p[i] = new Lagu("L" + i, "A", 3 + (i % 50) / 10.0);
            }

            // Pemanasan (warm-up) agar JIT sudah mengoptimasi kode sebelum diukur
            for (int i = 0; i < 2000; i++) { 
                sink += PlaylistRekursif.totalDurasi(p, n); sink += PlaylistRekursif.cariDurasiTerpanjang(p, n - 1); 
            }

            int R = 3000; // tiap fungsi dipanggil 3000 kali, lalu diambil rata-ratanya

            long t0 = System.nanoTime();
            for (int i = 0; i < R; i++) {
                sink += PlaylistRekursif.totalDurasi(p, n);
            }
            double a = (System.nanoTime() - t0) / 1e6 / R; // rata-rata (ms)

            t0 = System.nanoTime();
            for (int i = 0; i < R; i++) {
                sink += PlaylistRekursif.cariDurasiTerpanjang(p, n - 1);
            }
            double b = (System.nanoTime() - t0) / 1e6 / R; // rata-rata (ms)

            System.out.printf("%d,%.6f,%.6f%n", n, a, b);
        }
    }
}