# Final Project Android

Aplikasi Kotlin + Jetpack Compose dengan Navigation Compose dan Supabase.

## Struktur kode

Sumber aplikasi: `app/src/main/java/com/bintangg/finalproject/`.

- `MainActivity.kt`: entry point, tema, dan pemanggilan `AppNavigation()`.
- `navigation/AppRoute.kt`: definisi route dan penanda halaman yang menampilkan bottom bar.
- `navigation/AppNavigation.kt`: satu `NavController`, pendaftaran layar, callback perpindahan, dan pengaturan back stack.
- `screens/`: tampilan dan state layar. Aksi navigasi memakai callback; layar tidak menerima `NavController`.
- `components/`: elemen UI bersama. `BottomAppBar.kt` menerima route aktif dan callback, tanpa membuat controller sendiri.
- `data/`: model, repository, dan klien Supabase.
- `ui/theme/`: warna, tipografi, dan tema aplikasi.

## Route dan pemanggil

Halaman awal tetap **Dashboard**. Belum ada pemeriksaan sesi atau pengalihan otomatis ke login.

| Route | Layar | Akses dari UI |
| --- | --- | --- |
| `Dashboard` | `DashboardScreen` | Aplikasi dibuka; bottom bar Home |
| `TaskList` | `TaskListScreen` | Bottom bar Tasks; kartu Pending Tasks dan tugas di Dashboard |
| `StudyProgress` | `StudyProgressScreen` | Bottom bar Stats |
| `AddTask` | `AddTaskBottomSheetScreen` | Tombol tambah di Dashboard dan TaskList |
| `EditTask` | `EditTaskScreen` | Kartu Final Project Submission di Dashboard |
| `CategoryList` | `CategoryListScreen` | Shortcut Categories di Dashboard |
| `AddCategoryScreen` | `AddCategoryScreen` | Shortcut Add Category; tombol tambah di CategoryList |
| `EditCategory` | `EditCategoryScreen` | Tombol opsi pada kartu CategoryList |
| `AddRoleScreen` | `AddRoleScreen` | Shortcut Add Role di Dashboard |
| `EditRole` | `EditRoleScreen` | Terdaftar, tetapi belum ada pemanggil UI; daftar peran belum tersedia |
| `AddSchedule` | `AddScheduleScreen` | Shortcut Add Schedule di Dashboard |
| `EditSchedule` | `EditScheduleScreen` | Kartu Today's Schedules di Dashboard |
| `CreateAccount` | `CreateAccountScreen` | Shortcut Create Account; tautan daftar di Login |
| `Login` | `LoginScreen` | Tautan Masuk di sini pada CreateAccount |

`MainActivity` memanggil `AppNavigation`; `AppNavigation` menghubungkan callback layar ke route di atas. Nama route tidak ditulis ulang di layar atau bottom bar.

Bottom bar hanya tampil di Dashboard, TaskList, dan StudyProgress. Perpindahan ketiga tab memakai `popUpTo`, `saveState`, `restoreState`, dan `launchSingleTop`. Form kembali ke layar asal lewat `onBack`. Perpindahan Login ↔ CreateAccount mengganti form saat ini agar tombol Back tidak menelusuri formulir autentikasi yang berulang.

## Batas fitur yang sudah ada

- AddCategory dan AddTask memiliki pemanggilan insert Supabase. Keduanya menutup form setelah insert berhasil.
- Daftar dan detail contoh masih memakai data statis. EditCategory belum menerima identitas item; membuka opsi kartu belum berarti mengedit data kartu tersebut di database.
- Penyimpanan peran/jadwal, perubahan/penghapusan kategori, autentikasi, login sosial, dan pemulihan kata sandi belum memiliki implementasi lengkap. Perapian navigasi tidak menambahkan keberhasilan simpan/login palsu.
- `ListRole.kt` sebelumnya hanya berisi deklarasi package; file kosong tersebut sudah dihapus.

## Menjalankan proyek

Gunakan Android Studio atau Java 25 sesuai `gradle/gradle-daemon-jvm.properties`. Proyek memakai Gradle Wrapper 9.5.0, compile/target SDK 37, dan min SDK 26.

Atur lokasi SDK melalui Android Studio, `ANDROID_HOME`, atau `sdk.dir` di `local.properties`. Untuk operasi Supabase, tambahkan konfigurasi berikut ke `local.properties` tanpa membagikan kredensial:

```properties
SUPABASE_URL=https://PROJECT_ID.supabase.co
SUPABASE_ANON_KEY=PUBLIC_ANON_KEY
```

Build APK debug:

```bash
./gradlew :app:assembleDebug
```

Jalankan pengujian dengan emulator/perangkat Android aktif:

```bash
./gradlew :app:connectedDebugAndroidTest :app:testDebugUnitTest
```

`NavigationTest.kt` memeriksa back stack saat berganti tab, batal form jadwal, kembali dari form kategori bertingkat, dan perpindahan Login ↔ CreateAccount. Pengujian ini tidak membutuhkan login atau koneksi Supabase karena tidak menekan tombol simpan.

## Hasil verifikasi perapian

- APK debug berhasil dibangun: `app/build/outputs/apk/debug/app-debug.apk`.
- Android 15/API 35: 5 pengujian instrumentasi lulus, termasuk 4 skenario navigasi pada `NavigationTest`.
- `testDebugUnitTest` lulus.
- Smoke manual dengan tap dan tombol Back berhasil untuk Dashboard → Tasks → Dashboard, pembatalan jadwal, serta kategori → tambah kategori → kembali ke daftar → Dashboard. Tampilan Dashboard dan form jadwal juga diperiksa lewat screenshot emulator.
- Operasi database dan autentikasi tidak diuji; lingkungan verifikasi tidak memakai kredensial Supabase.
