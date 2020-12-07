package miguelangellopezblanca.psp.practica2_psp_ad.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import miguelangellopezblanca.psp.practica2_psp_ad.model.Repository;
import miguelangellopezblanca.psp.practica2_psp_ad.viewmodel.ViewModelActivity;

public class LlamadasReceiver extends BroadcastReceiver {

    private String incomingNumber;
    private Repository repository;


    @Override
    public void onReceive(Context context, Intent intent) {

        repository = new Repository(context);

        if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_RINGING)){

            incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

            repository.insertarLlamada(incomingNumber);

            Toast.makeText(context, "Llamada desde: "+ incomingNumber, Toast.LENGTH_LONG).show();
        }

    }
}
