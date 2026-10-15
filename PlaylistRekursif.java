// Nama dan NIM Team Assignment 3 DSAA (LEC)
// CHRISTIAN HOTASI VICCARE HUTAGAOL - 2902804014
// FAIZA KURNIAWATI - 2902807880
// ARI JUNIANTO - 2902828701
// UTAS PRASOJO - 2902809551
// HANDSON PANGGABEAN - 2902820005

public class PlaylistRekursif {
    static Lagu[] playlist = {
        new Lagu("Perfect", "Ed Sheeran", 4.23),
        new Lagu("Shivers", "Ed Sheeran", 3.50),
        new Lagu("Fix You", "Coldplay", 4.52),
        new Lagu("Yellow", "Coldplay", 4.30),
        new Lagu("Viva La Vida", "Coldplay", 4.52),
        new Lagu("Photograph", "Ed Sheeran", 4.19),
        new Lagu("A Thousand Years", "Christina Perri", 4.45),
        new Lagu("Someone Like You", "Adele", 4.45),
        new Lagu("Let Her Go", "Passenger", 4.12),
        new Lagu("Night Changes", "One Direction", 3.46),
        new Lagu("Counting Stars", "OneRepublic", 4.17),
        new Lagu("All of Me", "John Legend", 4.29),
        new Lagu("Memories", "Maroon 5", 3.09),
        new Lagu("The Scientist", "Coldplay", 5.09),
        new Lagu("Treat You Better", "Shawn Mendes", 3.07),
        new Lagu("Happier", "Ed Sheeran", 3.27),
        new Lagu("In the End", "Linkin Park", 3.36),
        new Lagu("Demons", "Imagine Dragons", 2.57),
        new Lagu("Love Yourself", "Justin Bieber", 3.53),
        new Lagu("As It Was", "Harry Styles", 2.47)
    };

    /*
        * Tujuan: menjumlahkan durasi semua lagu dari lagu pertama sampai lagu ke-n.
        * Base case: kalau n sudah 0 (tidak ada lagu yang tersisa), hasilnya 0.0.
        * Recursive case: ambil durasi lagu ke-n, lalu tambahkan dengan total durasi n-1 lagu sebelumnya.
        * Kompleksitas: O(n) waktu karena tiap lagu dijumlahkan satu kali, dan O(n) ruang karena ada n pemanggilan yang menumpuk di call stack.
    */
    public static double totalDurasi(Lagu[] list, int n) {
        // tidak ada lagu tersisa? kembalikan 0.0
        if (n <= 0) {
            return 0.0;
        }

        return list[n - 1].getDurasi() + totalDurasi(list, n - 1); // durasi lagu terakhir + total durasi lagu-lagu sebelumnya
    }

    /*
        * Tujuan: mencari durasi terpanjang di antara lagu index 0 sampai index.
        * Base case: kalau tinggal satu lagu (index == 0), durasinya otomatis yang terpanjang.
        * Recursive case: cari dulu durasi terpanjang dari lagu-lagu sebelum index ini, lalu bandingkan dengan durasi lagu di index ini.
        * O(n) waktu karena tiap lagu dibandingkan satu kali, dan O(n) ruang karena kedalaman call stack sebanyak n.
    */
    static double cariDurasiTerpanjang(Lagu[] list, int index) {
        // Validasi jika playlist kosong atau index tidak vaid
        if (list == null || list.length == 0 || index < 0) {
            return 0.0;
        }

        // Base case: cuma ada satu lagu
        if (index == 0) {
            return list[0].getDurasi();
        }

        // Cari dulu durasi terpanjang dari lagu-lagu sebelumnya
        double durasiSebelumnya = cariDurasiTerpanjang(list, index - 1);

        // Bandingkan dengan lagu di index ini. Pakai >= agar kalau ada durasi yang sama, lagu yang urutannya lebih belakang yang dipilih
        if (list[index].getDurasi() >= durasiSebelumnya) {
            return list[index].getDurasi();
        }

        // Kalau tidak, durasi dari lagu-lagu sebelumnya berarti masih lebih panjang
        return durasiSebelumnya;
    }

    /*
        * Tujuan: sama seperti cariDurasiTerpanjang, tapi yang dikembalikan objek lagu nya, agar judul dan artisnya bisa ditampilkan.
        * Base case: kalau tinggal satu lagu (index == 0), lagu itu yang terpanjang.
        * Recursive case: cari lagu terpanjang dari lagu-lagu sebelumnya, lalu bandingkan durasinya dengan lagu di index ini.
        * Kompleksitas: O(n) waktu dan O(n) ruang (call stack).
    */
    static Lagu cariLaguTerpanjang(Lagu[] list, int index) {
        // Validasi jika playlist kosong atau index tidak valid
        if (list == null || list.length == 0 || index < 0) {
            return null;
        }

        // Base case: cuma ada satu lagu
        if (index == 0) {
            return list[0];
        }

        // Cari dulu lagu terpanjang dari lagu-lagu sebelumnya
        Lagu terpanjangSebelumnya = cariLaguTerpanjang(list, index - 1);

        // Pakai >= agar konsisten dengan cariDurasiTerpanjang
        if (list[index].getDurasi() >= terpanjangSebelumnya.getDurasi()) {
            return list[index];
        }

        return terpanjangSebelumnya;
    }

    /*
        * Tujuan: menampilkan daftar lagu dari yang paling akhir sampai yang pertama.
        * Base case: kalau index sudah kurang dari 0, semua lagu sudah tampil, jadi berhenti.
        * Recursive case: cetak lagu di index ini, lalu panggil lagi untuk lagu sebelumnya.
        * Kompleksitas: O(n) waktu karena tiap lagu dicetak satu kali, dan O(n) ruang karena kedalaman call stack sebanyak n.
    */
    public static void tampilkanMundur(Lagu[] list, int index) {
        // semua lagu sudah ditampilkan? rekursi berhenti
        if (index < 0) {
            return;
        }

        // Nomor urut dimulai dari 1, jadi index ditambah 1
        System.out.println((index + 1) + ". " + list[index].getJudul());

        // Lanjut ke lagu sebelumnya
        tampilkanMundur(list, index - 1);
    }

    /*
        * Tujuan: sama seperti tampilkanMundur, tapi hasilnya disimpan ke StringBuilder, agar waktu rekursinya bisa diukur tanpa tercampur waktu print.
        * Base case: index kurang dari 0 -> berhenti.
        * Recursive case: tambahkan lagu di index ini ke sb, lalu lanjut ke lagu sebelumnya.
        * Kompleksitas: O(n) waktu dan O(n) ruang (call stack).
    */
    static void susunMundur(Lagu[] list, int index, StringBuilder sb) {
        if (index < 0) {
            return;
        }

        sb.append(index + 1).append(". ").append(list[index].getJudul()).append("\n");

        susunMundur(list, index - 1, sb);
    }

    public static void main(String[] args) {
        // Ganti nilai dibawah untuk eksperimen: 3, 5, atau 10 (max = 20)
        int jumlahLagu = 10;

        jalankanAnalisis(jumlahLagu);
    }

    /*
        * Tujuan: menjalankan semua analisis (total durasi, lagu terpanjang, daftar terbalik) untuk n lagu pertama di playlist, lalu menampilkan hasil dan waktu eksekusinya.
        * Kompleksitas: tiap fungsi yang dipanggil O(n), jadi keseluruhan juga O(n).
    */
    static void jalankanAnalisis(int n) {
        // validasi untuk memastikan jumlah lagu yang mau di tes minimal 1 dan tidak lebih dari isi playlist (max = 20)
        if (n < 1 || n > playlist.length) {
            System.out.println("Jumlah lagu harus 1 sampai " + playlist.length);
            return;
        }

        System.out.println("=== ANALISIS REKURSIF PLAYLIST ===");
        System.out.println("Jumlah lagu : " + n);

        // Hitung total durasi, sambil mengukur waktunya dalam milidetik
        long mulaiTotal = System.nanoTime();

        double total = totalDurasi(playlist, n);

        long selesaiTotal = System.nanoTime();

        double waktuTotal = (selesaiTotal - mulaiTotal) / 1_000_000.0;

        System.out.printf("Total durasi : %.2f menit%n", total);

        // Cari durasi terpanjang, sambil mengukur waktunya
        long mulaiCari = System.nanoTime();

        double durasiTerpanjang = cariDurasiTerpanjang(playlist, n - 1);

        long selesaiCari = System.nanoTime();

        double waktuCari = (selesaiCari - mulaiCari) / 1_000_000.0;

        // Cari juga lagunya agar judul dan artisnya bisa ditampilkan.
        Lagu laguTerpanjang = cariLaguTerpanjang(playlist, n - 1);

        System.out.printf(
            "Lagu terpanjang : \"%s\" - %s (%.2f menit)%n",
            laguTerpanjang.getJudul(),
            laguTerpanjang.getArtis(),
            durasiTerpanjang
        );

        // Tampilkan playlist dari lagu terakhir ke pertama (DESC)
        System.out.println("\nDaftar lagu (ditampilkan terbalik):");

        // Cetak daftar ke layar (tidak di ukur)
        tampilkanMundur(playlist, n - 1);

        // start mengukur: waktu rekursi murni (tanpa print)
        long mulaiMundur = System.nanoTime();

        susunMundur(playlist, n - 1, new StringBuilder());

        // selesai mengukur: waktu rekursi murni (tanpa print)
        long selesaiMundur = System.nanoTime();

        double waktuMundur = (selesaiMundur - mulaiMundur) / 1_000_000.0;

        // Tampilkan waktu eksekusi ketiga fungsi (dalam milidetik)
        System.out.printf("%nExecution Time (totalDurasi): %.6f ms%n", waktuTotal);

        System.out.printf("Execution Time (tampilkanMundur): %.6f ms%n", waktuMundur);

        System.out.printf("Execution Time (cariDurasiTerpanjang): %.6f ms%n", waktuCari);
    }
}