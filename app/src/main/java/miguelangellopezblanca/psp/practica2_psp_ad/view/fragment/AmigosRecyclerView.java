package miguelangellopezblanca.psp.practica2_psp_ad.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import miguelangellopezblanca.psp.practica2_psp_ad.R;
import miguelangellopezblanca.psp.practica2_psp_ad.model.room.pojo.Amigo;
import miguelangellopezblanca.psp.practica2_psp_ad.model.room.pojo.CallFriend;
import miguelangellopezblanca.psp.practica2_psp_ad.view.adapter.ContactsRecyclerAdapter;
import miguelangellopezblanca.psp.practica2_psp_ad.view.adapter.FriendsRecyclerAdapter;
import miguelangellopezblanca.psp.practica2_psp_ad.viewmodel.ViewModelActivity;


public class AmigosRecyclerView extends Fragment {

    ViewModelActivity viewModelActivity;

    //List<Amigo> friends = new ArrayList<>();
    List<CallFriend> call = new ArrayList<>();

    public AmigosRecyclerView() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_amigos_recycler_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModelActivity = new ViewModelProvider(getActivity()).get(ViewModelActivity.class);


        FloatingActionButton fbAñadirAmigos = view.findViewById(R.id.fbAñadirAmigo);
        fbAñadirAmigos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.contactosRecyclerView);
            }
        });

        RecyclerView recyclerFriend = view.findViewById(R.id.recyclerAmigos);
        FriendsRecyclerAdapter adapter = new FriendsRecyclerAdapter(getActivity(), call);

        recyclerFriend.setAdapter(adapter);
        recyclerFriend.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModelActivity.getLiveAmigoCallList().observe(getActivity(), new Observer<List<CallFriend>>() {
            @Override
            public void onChanged(List<CallFriend> callFriends) {
                call.clear();
                call.addAll(callFriends);
                adapter.notifyDataSetChanged();

            }
        });

/*
        viewModelActivity.getLiveAmigoList().observe(getActivity(), new Observer<List<Amigo>>() {
            @Override
            public void onChanged(List<Amigo> amigos) {
                friends.clear();
                friends.addAll(amigos);
                adapter.notifyDataSetChanged();
            }
        });
*/

    }

}