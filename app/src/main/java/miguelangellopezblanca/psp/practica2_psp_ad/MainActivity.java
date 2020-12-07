package miguelangellopezblanca.psp.practica2_psp_ad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.Manifest.permission.READ_CALL_LOG;
import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.READ_PHONE_STATE;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermission();

    }

    //----- PEDIR PERMISOS -----
    private void requestPermission() {
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                READ_CONTACTS,
                READ_PHONE_STATE,
                READ_CALL_LOG
        }, PERMISSION_CODE);
    }

}