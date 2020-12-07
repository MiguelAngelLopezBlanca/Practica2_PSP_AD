package miguelangellopezblanca.psp.practica2_psp_ad.model.room.pojo;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "amigos", indices = {@Index(value = {"phone"}, unique = true)})
public class Amigo {


    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @Nullable
    @ColumnInfo(name = "fechaNacimiento")
    private String fechaNacimiento;

    @NonNull
    @ColumnInfo(name = "phone")
    private String phone;

    public Amigo( @NonNull String name, @Nullable String fechaNacimiento, @NonNull String phone) {
        this.name = name;
        this.fechaNacimiento = fechaNacimiento;
        this.phone = phone;
    }

    public Amigo(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @Nullable
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(@Nullable String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @NonNull
    public String getPhone() {
        return phone;
    }

    public void setPhone(@NonNull String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return  name + "\n" + phone ;
    }
}
