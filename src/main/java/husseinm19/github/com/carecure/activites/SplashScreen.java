package husseinm19.github.com.carecure.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import husseinm19.github.com.carecure.R;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Hussein on 18-04-2020
 */



public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ImageView imageView =(ImageView) findViewById(R.id.logo_Secur);
        imageView.setImageResource(R.drawable.doctort);
        ProgressBar progressBar= (ProgressBar) findViewById(R.id.intro_progress);
        progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.colorWhite), android.graphics.PorterDuff.Mode.SRC_IN);
        // getSupportActionBar().hide();
        Timer myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                // If you want to modify a view in your Activity
                SplashScreen.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(ContextCompat.checkSelfPermission(SplashScreen.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                            startActivity(new Intent(SplashScreen.this, MainActivity.class));
                            finish();
                        }else {
                            Dexter.withActivity(SplashScreen.this)
                                    .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                                    .withListener(new PermissionListener() {
                                        @Override
                                        public void onPermissionGranted(PermissionGrantedResponse response) {
                                            startActivity(new Intent(SplashScreen.this, MainActivity.class));
                                            finish();
                                        }

                                        @Override
                                        public void onPermissionDenied(PermissionDeniedResponse response) {
                                            if(response.isPermanentlyDenied()){
                                                AlertDialog.Builder builder = new AlertDialog.Builder(SplashScreen.this);
                                                builder.setTitle(R.string.Permission_Denied)
                                                        .setMessage(R.string.Permission_DeniedText)
                                                        .setNegativeButton(R.string.cancel, null)
                                                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                Intent intent = new Intent();
                                                                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                                                intent.setData(Uri.fromParts("package", getPackageName(), null));
                                                            }
                                                        })
                                                        .show();
                                            } else {
                                                Toast.makeText(SplashScreen.this, R.string.Permission_Denied, Toast.LENGTH_SHORT).show();
                                                finish();
                                            }
                                        }

                                        @Override
                                        public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                            token.continuePermissionRequest();
                                        }
                                    })
                                    .check();
                        }

                    }
                });
            }
        }, 3000);
    }
}
