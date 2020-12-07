package miguelangellopezblanca.psp.practica2_psp_ad.model.room.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import miguelangellopezblanca.psp.practica2_psp_ad.model.room.pojo.Llamada;

@Dao
public interface LlamadasDao {

    @Delete
    int delete(Llamada llamada);

    @Query("delete from llamada where id = :id")
    int delete(long id);

    @Query("select * from llamada order by idAmigo")
    LiveData<List<Llamada>> getAll();

    @Insert
    long insert(Llamada llamada);

    @Update
    int update(Llamada llamada);

}
