<?php
include('koneksi.php');
    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    
        //Mendapatkan Nilai Variable
        $name = $_POST['name'];
        $nis = $_POST['nis'];
        $alamat = $_POST['alamat'];
        $kota = $_POST['kota'];
        $kelamin = $_POST['kelamin'];
        $umur = $_POST['umur'];




        //Pembuatan Syntax SQL
        $sql = "INSERT INTO tb_siswa (nama, nis, alamat, kota, kelamin, umur) VALUES ('$name', '$nis', '$alamat', '$kota', '$kelamin', '$umur')";

        //Import File Koneksi Database

        //Eksekusi Query Database
        if (mysqli_query($con, $sql)) {
            echo 'Berhasil Menambahkan Pegawai';
        } else {    
            echo 'Gagal Menambahkan Pegawai';
        }

        mysqli_close($con);
    }
?>