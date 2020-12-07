package miguelangellopezblanca.psp.practica2_psp_ad.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import java.util.List;

import miguelangellopezblanca.psp.practica2_psp_ad.model.Repository;
import miguelangellopezblanca.psp.practica2_psp_ad.model.room.pojo.Amigo;
import miguelangellopezblanca.psp.practica2_psp_ad.model.room.pojo.CallFriend;
import miguelangellopezblanca.psp.practica2_psp_ad.model.room.pojo.Llamada;

public class ViewModelActivity extends AndroidViewModel {

    private Repository repository;
    private long contador;
    private Amigo currentAmigo;

    public ViewModelActivity(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public Amigo getCurrentAmigo() {
        return currentAmigo;
    }

    public void setCurrentAmigo(Amigo currentAmigo) {
        this.currentAmigo = currentAmigo;
    }

    public long getContador() {
        return contador;
    }

    public void setContador(long contador) {
        this.contador = contador;
    }

    public Amigo getAmigo() {
        return repository.getCurrentAmigo();
    }

    public LiveData<List<Amigo>> getLiveAmigoList() {
        return repository.getLiveAmigoList();
    }

    public LiveData<List<CallFriend>> getLiveAmigoCallList() {
        return repository.getLiveCallFriendList();
    }


    public MutableLiveData<Long> getLiveAmigoInsertId() {
        return repository.getLiveFriendInsertId();
    }

    public void insert(Amigo amigo) {
        repository.insert(amigo);
    }

    public void insert(Llamada llamada) {
        repository.insert(llamada);
    }

    public void setAmigo(Amigo amigo) {
        repository.setCurrentAmigo(amigo);
    }

    public void update(Amigo amigo) {
        repository.update(amigo);
    }

    public void delete(long id){
        repository.delete(id);
    }



}