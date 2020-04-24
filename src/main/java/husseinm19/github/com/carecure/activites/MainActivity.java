package husseinm19.github.com.carecure.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import husseinm19.github.com.carecure.R;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;


import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

/**
 * Created by Hussein on 18-04-2020
 */


public class MainActivity extends AppCompatActivity {

    private long mLastClickTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        cardClicked();

    }

    public void cardClicked(){
        CardView hospital = (CardView) findViewById(R.id.hospitalCard);
        CardView pharmacy = (CardView) findViewById(R.id.pharmacylCard);
        CardView clinick = (CardView) findViewById(R.id.clinicksCard);
        CardView pet = (CardView) findViewById(R.id.petCard);


        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Hospitals Clicked", Toast.LENGTH_SHORT).show();
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                Intent intent = new Intent(getApplicationContext() , MapsActivity.class);
                intent.putExtra("id","1");
                startActivity(intent);
            }
        });


        pharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Pharmacies Clicked", Toast.LENGTH_SHORT).show();
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                Intent intent = new Intent(MainActivity.this , MapsActivity.class);
                intent.putExtra("id","2");
                startActivity(intent);
            }
        });

        clinick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Clinics Clicked", Toast.LENGTH_SHORT).show();
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                Intent intent = new Intent(MainActivity.this , MapsActivity.class);
                intent.putExtra("id","3");
                startActivity(intent);
            }
        });

        pet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Veterinaries Clicked", Toast.LENGTH_SHORT).show();
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                Intent intent = new Intent(MainActivity.this , MapsActivity.class);
                intent.putExtra("id","4");
                startActivity(intent);
            }
        });
    }
}
