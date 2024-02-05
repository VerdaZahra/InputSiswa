<?php
//Mendefinisikan Konstanta
define('HOST', 'localhost'); 
define('USER','root');
define('PASS','');
define('DB','db_android');
//membuat koneksi dengan database
$con = mysqli_connect(HOST, USER, PASS, DB) or die('Unable to Connect');



if (mysqli_connect_errno()) {
    die("Koneksi database gagal: " . mysqli_connect_error());
}
?>  