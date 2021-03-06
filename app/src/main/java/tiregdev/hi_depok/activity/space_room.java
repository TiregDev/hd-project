package tiregdev.hi_depok.activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.arnaudguyon.smartfontslib.FontButton;
import fr.arnaudguyon.smartfontslib.FontEditText;
import fr.arnaudguyon.smartfontslib.FontTextView;
import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.events.OnBannerClickListener;
import ss.com.bannerslider.views.BannerSlider;
import tiregdev.hi_depok.R;
import tiregdev.hi_depok.utils.AppConfig;
import tiregdev.hi_depok.utils.AppController;
import tiregdev.hi_depok.utils.SQLiteHandler;

import static tiregdev.hi_depok.R.layout.activity_signup;

public class space_room extends AppCompatActivity implements View.OnClickListener {

    private FontTextView moreInfoSS;
    private FontTextView jadwalKosong;
    private FontEditText nama;
    private FontEditText namaInstansi;
    private FontEditText notelp;
    private FontButton tgl;
    private FontEditText keperluan;
    private FontEditText jumlah;
    private FontButton kirim;
    private BannerSlider banner;
    private SimpleDateFormat sdf;
    private Calendar dateAndTime;
    private SQLiteHandler db;
    private ProgressDialog pDialog;
    private static final String TAG = space_room.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space_room);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViews();
    }

    private void settingTanggal(){
        dateAndTime = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener d =
                new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int month,
                                          int day){
                        dateAndTime.set(Calendar.YEAR, year);
                        dateAndTime.set(Calendar.MONTH, month);
                        dateAndTime.set(Calendar.DAY_OF_MONTH, day);

                        tgl.setText(sdf.format(dateAndTime.getTime()));
                    }
                };

        new DatePickerDialog(space_room.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void moreInfoSS(){
        final LayoutInflater factory = LayoutInflater.from(space_room.this);
        final View exitDialogView = factory.inflate(R.layout.dialog_spaceroom, null);
        final AlertDialog exitDialog = new AlertDialog.Builder(space_room.this).create();
        exitDialog.setView(exitDialogView);
        exitDialogView.findViewById(R.id.btnClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitDialog.dismiss();
            }
        });
        exitDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                space_room.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void findViews() {
        moreInfoSS = (FontTextView)findViewById( R.id.moreInfoSS );
        jadwalKosong = (FontTextView)findViewById( R.id.jadwalKosong );
        nama = (FontEditText)findViewById( R.id.nama );
        namaInstansi = (FontEditText)findViewById( R.id.namaInstansi );
        notelp = (FontEditText)findViewById( R.id.notelp );
        tgl = (FontButton)findViewById( R.id.tgl );
        keperluan = (FontEditText)findViewById( R.id.keperluan );
        jumlah = (FontEditText)findViewById( R.id.jumlah );
        kirim = (FontButton)findViewById( R.id.kirim );
        banner = (BannerSlider) findViewById(R.id.banner_slide);

        sdf = new SimpleDateFormat("dd-MM-yyyy");
        pDialog = new ProgressDialog(this);
        db = new SQLiteHandler(getApplicationContext());

        List<Banner> banners = new ArrayList<>();
        banners.add(new DrawableBanner(R.drawable.space_1));
        banners.add(new DrawableBanner(R.drawable.space_2));
        banners.add(new DrawableBanner(R.drawable.space_3));
        banner.setBanners(banners);

        tgl.setOnClickListener( this );
        kirim.setOnClickListener( this );
        moreInfoSS.setOnClickListener(this);
    }

    private void sendData(){

        final String nama_pendaftar= nama.getText().toString().trim();
        final String instansi = namaInstansi.getText().toString().trim();
        final String jumlah_anggota = jumlah.getText().toString().trim();
        final String no_telp = notelp.getText().toString().trim();
        final String _keperluan = keperluan.getText().toString().trim();
        final String waktu = tgl.getText().toString().trim();
        final String id_user = db.getUserDetails().get("uid");

        String tag_string_req = "req_spaceroom";

        pDialog.setMessage("Mengirim Permintaan ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.REG_SPACEROOM, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        // User successfully stored in MySQL
                        // Now store the user in sqlite
//                        String uid = jObj.getString("uid");
//
//                        JSONObject user = jObj.getJSONObject("user");
//                        String name = user.getString("name");
//                        String email = user.getString("email");
//                        String alamat = user.getString("alamat");
//                        String no_telp = user.getString("no_telp");
//                        String tanggal_lahir = user.getString("tanggal_lahir");
//                        String bio = user.getString("bio");
//                        String foto_user = user.getString("foto");
//
//                        // Inserting row in users table
//                        db.updateUser(name, email, uid, alamat, no_telp, tanggal_lahir, bio, foto_user);

                        Toast.makeText(getApplicationContext(), "Data berhasil terkirim!", Toast.LENGTH_LONG).show();

                        // Launch login activity
                        Intent intent = new Intent(getBaseContext(),
                                MenuActivity.class);
                        startActivity(intent);
                        finish();
                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                Log.d(TAG, "Failed with error msg:\t" + error.getMessage());
                Log.d(TAG, "Error StackTrace: \t" + error.getStackTrace());
                // edited here
                try {
                    byte[] htmlBodyBytes = error.networkResponse.data;
                    Log.e(TAG, new String(htmlBodyBytes), error);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("instansi", instansi);
                params.put("nama_pendaftar", nama_pendaftar);
                params.put("keperluan", _keperluan);
                params.put("waktu", waktu);
                params.put("jumlah_anggota", jumlah_anggota);
                params.put("no_telp", no_telp);
                params.put("id_user", id_user);

                return params;
            }

        };

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-10-16 13:06:31 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == tgl ) {
            settingTanggal();
            // Handle clicks for tgl
        } else if ( v == kirim ) {
            sendData();
            // Handle clicks for kirim
        } else if ( v == moreInfoSS ) {
            moreInfoSS();
        }
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
