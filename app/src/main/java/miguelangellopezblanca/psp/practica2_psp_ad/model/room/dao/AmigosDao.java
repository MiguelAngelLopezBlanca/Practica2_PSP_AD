package miguelangellopezblanca.psp.practica2_psp_ad.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import miguelangellopezblanca.psp.practica2_psp_ad.model.room.pojo.Amigo;
import miguelangellopezblanca.psp.practica2_psp_ad.model.room.pojo.CallFriend;

@Dao
public interface AmigosDao {

    @Delete
    int delete(Amigo amigo );

    @Query("delete from amigos where id = :id")
    void delete(long id);

    @Query("select * from amigos order by name")
    LiveData<List<Amigo>> getAll();

    @Query("select a.id, a.name, a.phone, a.fechaNacimiento, count(c.id) count from amigos a left join llamada c on a.id = c.idAmigo group by a.id, a.name, a.phone, a.fechaNacimiento order by name")
    LiveData<List<CallFriend>> getAllLlamada();

    @Query("select id from amigos where phone = :phone")
    long getIDCall(String phone);

    @Update
    int update(Amigo amigo);

    @Insert
    long insert(Amigo amigo);

}
