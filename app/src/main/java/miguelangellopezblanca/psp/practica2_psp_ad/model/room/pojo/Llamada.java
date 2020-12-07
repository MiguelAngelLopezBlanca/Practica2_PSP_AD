package miguelangellopezblanca.psp.practica2_psp_ad.model.room.pojo;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "llamada", foreignKeys = @ForeignKey(entity = Amigo.class, parentColumns = "id", childColumns = "idAmigo", onDelete = ForeignKey.RESTRICT))
public class Llamada {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "idAmigo")
    private long idAmigo;

    @Nullable
    @ColumnInfo(name = "fechaLlamada")
    private String fechaLlamada;

    public Llamada(long idAmigo, @Nullable String fechaLlamada) {
        this.idAmigo = idAmigo;
        this.fechaLlamada = fechaLlamada;
    }

    public Llamada(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdAmigo() {
        return idAmigo;
    }

    public void setIdAmigo(long idAmigo) {
        this.idAmigo = idAmigo;
    }

    @Nullable
    public String getFechaLlamada() {
        return fechaLlamada;
    }

    public void setFechaLlamada(@Nullable String fechaLlamada) {
        this.fechaLlamada = fechaLlamada;
    }

    @Override
    public String toString() {
        return "Llamada{" +
                "id=" + id +
                ", idAmigo=" + idAmigo +
                ", fechaLlamada='" + fechaLlamada + '\'' +
                '}';
    }
}
