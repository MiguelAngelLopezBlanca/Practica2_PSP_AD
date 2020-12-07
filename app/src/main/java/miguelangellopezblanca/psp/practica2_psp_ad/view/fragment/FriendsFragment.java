package miguelangellopezblanca.psp.practica2_psp_ad.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import miguelangellopezblanca.psp.practica2_psp_ad.R;
import miguelangellopezblanca.psp.practica2_psp_ad.model.room.pojo.Amigo;
import miguelangellopezblanca.psp.practica2_psp_ad.viewmodel.ViewModelActivity;


public class FriendsFragment extends Fragment {

    ViewModelActivity viewModelActivity;


    public FriendsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friends, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        viewModelActivity = new ViewModelProvider(getActivity()).get(ViewModelActivity.class);

        EditText etNombre = view.findViewById(R.id.etNombre);
        TextView tvTelefono = view.findViewById(R.id.tvTelefono);
        EditText etFechaNacimiento = view.findViewById(R.id.etFechaNacimiento);
        TextView tvNumberCall = view.findViewById(R.id.tvNumberCall);

        String nombre = viewModelActivity.getCurrentAmigo().getName();
        String telefono = viewModelActivity.getCurrentAmigo().getPhone();
        String fecha = viewModelActivity.getCurrentAmigo().getFechaNacimiento();

        long numberCall = viewModelActivity.getContador();
/*
        Log.v("xyzyx", nombre);
        Log.v("xyzyx", telefono);
        Log.v("xyzyx", fecha + "Hola");
        Log.v("xyzyx", numberCall + "");
*/
        etNombre.setText(nombre);
        tvTelefono.setText(telefono);
        etFechaNacimiento.setText(fecha);
        tvNumberCall.setText(String.valueOf(numberCall));

        Button btActualizar = view.findViewById(R.id.btActualizar);
        btActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amigo amigo = new Amigo(etNombre.getText().toString(), etFechaNacimiento.getText().toString(), tvTelefono.getText().toString());
                amigo.setId(viewModelActivity.getCurrentAmigo().getId());

                Log.v("xyzyx", amigo.toString());
                viewModelActivity.update(amigo);
                final NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.amigosRecyclerView);

            }
        });

        Button btBorrar = view.findViewById(R.id.btBorrar);
        btBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModelActivity.delete(viewModelActivity.getCurrentAmigo().getId());
                final NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.amigosRecyclerView);
            }
        });
    }
}