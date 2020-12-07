package miguelangellopezblanca.psp.practica2_psp_ad.model.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import miguelangellopezblanca.psp.practica2_psp_ad.model.room.dao.AmigosDao;
import miguelangellopezblanca.psp.practica2_psp_ad.model.room.dao.LlamadasDao;
import miguelangellopezblanca.psp.practica2_psp_ad.model.room.pojo.Amigo;
import miguelangellopezblanca.psp.practica2_psp_ad.model.room.pojo.Llamada;

@Database(entities = {Amigo.class, Llamada.class}, version = 1, exportSchema = false)
public abstract class AmigosDataBase extends RoomDatabase {

    public abstract AmigosDao getAmigoDao();
    public abstract LlamadasDao getLlamadaDao();

    private volatile static AmigosDataBase INSTANCE;

    public static synchronized AmigosDataBase getDb(final Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AmigosDataBase.class, "dbamigo.sqlite").build();
        }
        return INSTANCE;
    }

}
