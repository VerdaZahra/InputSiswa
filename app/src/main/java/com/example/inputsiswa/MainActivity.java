package com.example.inputsiswa;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.inputsiswa.R;
import com.example.inputsiswa.RequestHandler;
import com.example.inputsiswa.konfigurasi;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextName;
    private EditText editTextDesg;
    private EditText editTextSal;
    private EditText editTextNis;
    private EditText editTextAlamat;
    private Spinner spinnerKota;
    private RadioGroup radioGroupGender;
    private EditText editTextUmur;
    private Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        editTextName = findViewById(R.id.editTextName);
        editTextNis = findViewById(R.id.editTextNis);
        editTextAlamat = findViewById(R.id.editTextAlamat);
        spinnerKota = findViewById(R.id.spinnerKota);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        editTextUmur = findViewById(R.id.editTextUmur);
        buttonAdd = findViewById(R.id.buttonAdd);

        // Setting listeners to button
        buttonAdd.setOnClickListener(this);
    }

    // Method to add employee
    private void addEmployee() {
        // Retrieve values from input fields
        final String name = editTextName.getText().toString().trim();
        // Remove the following lines
        // final String desg = editTextDesg.getText().toString().trim();
        // final String sal = editTextSal.getText().toString().trim();
        final String nis = editTextNis.getText().toString().trim();
        final String alamat = editTextAlamat.getText().toString().trim();
        final String kota = spinnerKota.getSelectedItem().toString().trim();
        final RadioButton selectedRadioButton = findViewById(radioGroupGender.getCheckedRadioButtonId());
        final String gender = selectedRadioButton != null ? selectedRadioButton.getText().toString().trim() : "";
        final String umur = editTextUmur.getText().toString().trim();

        class AddEmployee extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this, "Menambahkan...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put(konfigurasi.KEY_EMP_NAMA, name);

                params.put(konfigurasi.KEY_EMP_NIS, nis);
                params.put(konfigurasi.KEY_EMP_ALAMAT, alamat);
                params.put(konfigurasi.KEY_EMP_KOTA, kota);
                params.put(konfigurasi.KEY_EMP_GENDER, gender);
                params.put(konfigurasi.KEY_EMP_UMUR, umur);

                RequestHandler rh = new RequestHandler();
                return rh.sendPostRequest(konfigurasi.URL_ADD, params);
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }


    @Override
    public void onClick(View v) {
        if (v == buttonAdd) {
            addEmployee();
        }
    }
}
