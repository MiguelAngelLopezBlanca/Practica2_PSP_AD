package miguelangellopezblanca.psp.practica2_psp_ad.model;


import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import miguelangellopezblanca.psp.practica2_psp_ad.model.room.AmigosDataBase;
import miguelangellopezblanca.psp.practica2_psp_ad.model.room.dao.AmigosDao;
import miguelangellopezblanca.psp.practica2_psp_ad.model.room.dao.LlamadasDao;
import miguelangellopezblanca.psp.practica2_psp_ad.model.room.pojo.Amigo;
import miguelangellopezblanca.psp.practica2_psp_ad.model.room.pojo.CallFriend;
import miguelangellopezblanca.psp.practica2_psp_ad.model.room.pojo.Llamada;
import miguelangellopezblanca.psp.practica2_psp_ad.util.UtilThread;

public class Repository {

    private Amigo currentAmigo;

    private AmigosDao amigosDao;
    private LlamadasDao llamadasDao;


    private LiveData<List<Amigo>> liveFriendList;
    private LiveData<List<CallFriend>> liveCallFriendList;

    private MutableLiveData<Long> liveFriendInsertId = new MutableLiveData<>();

    public Repository(Context context) {
        AmigosDataBase db = AmigosDataBase.getDb(context);
        amigosDao = db.getAmigoDao();
        llamadasDao = db.getLlamadaDao();
        liveFriendList = amigosDao.getAll();
        liveCallFriendList = amigosDao.getAllLlamada();
    }

    public Amigo getCurrentAmigo() {
        return currentAmigo;
    }

    public MutableLiveData<Long> getLiveFriendInsertId() {
        return liveFriendInsertId;
    }

    public LiveData<List<Amigo>> getLiveAmigoList() {
        return liveFriendList;
    }

    public LiveData<List<CallFriend>> getLiveCallFriendList() {
        return liveCallFriendList;
    }

    public void setLiveCallFriendList(LiveData<List<CallFriend>> liveCallFriendList) {
        this.liveCallFriendList = liveCallFriendList;
    }

    public void insert(Amigo amigo) {
        UtilThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                try {

                    long id = amigosDao.insert(amigo);
                    liveFriendInsertId.postValue(id);
                } catch (Exception e) {
                    Log.v("xyz", e.toString());
                    liveFriendInsertId.postValue(0l);
                }
            }
        });
    }

    public void insert(Llamada llamada) {
        UtilThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                llamadasDao.insert(llamada);
            }
        });
    }

    public void setCurrentAmigo(Amigo currentAmigo) {
        this.currentAmigo = currentAmigo;
    }

    public void update(Amigo amigo) {
        UtilThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    amigosDao.update(amigo);
                } catch (Exception e) {
                    Log.v("xyz", e.toString());
                }
            }
        });
    }

    public void delete(long id){
        UtilThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                try{
                    amigosDao.delete(id);
                    Log.v("xyzyx", "Borradp");
                }catch(Exception e){
                    Log.v("xyzyx", e.toString());
                    Log.v("xyzyx", "No se ha podido borrar");
                }
            }
        });
    }

    public void insertarLlamada(String phone){
        UtilThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {

                    long id = amigosDao.getIDCall(phone);
                    String fecha = obtenerFecha();

                    Llamada call = new Llamada();
                    call.setIdAmigo(id);
                    call.setFechaLlamada(fecha);
                    Log.v("xyzyx", call.toString());
                    llamadasDao.insert(call);


            }
        });
    }


    private String obtenerFecha() {
        String fecha;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        formato.setTimeZone(TimeZone.getTimeZone("GMT+1"));
        fecha = formato.format(cal.getTime());
        return fecha;
    }

}
