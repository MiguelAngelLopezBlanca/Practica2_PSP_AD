package miguelangellopezblanca.psp.practica2_psp_ad.view.fragment;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import miguelangellopezblanca.psp.practica2_psp_ad.R;
import miguelangellopezblanca.psp.practica2_psp_ad.model.room.pojo.Amigo;
import miguelangellopezblanca.psp.practica2_psp_ad.view.adapter.ContactsRecyclerAdapter;


public class ContactosRecyclerView extends Fragment {

    private ArrayList<Amigo> nameContact = new ArrayList<>();

    String[] data = new String[]{ContactsContract.Data.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
    String order = ContactsContract.Data.DISPLAY_NAME + " ASC";
    String selectionNumber = ContactsContract.Data.MIMETYPE + "='" +
            ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "' AND "
            + ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT NULL";

    public ContactosRecyclerView() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contactos_recycler_view, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nameContact.clear();

        getContacts(getContext());

        RecyclerView recyclerContact = view.findViewById(R.id.recyclerContact);
        ContactsRecyclerAdapter adapter = new ContactsRecyclerAdapter(getContext(), getActivity(),nameContact);

        recyclerContact.setAdapter(adapter);
        recyclerContact.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    //----- OBTENER LISTA DE CONTACTOS DE LA AGENDA -----
    public void getContacts(Context context){
        Cursor cursor =  context.getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,
                data,
                selectionNumber,
                null,
                order);
        while(cursor.moveToNext()){
            String name = cursor.getString(0);
            String number = cursor.getString(1);

            Amigo amigo = new Amigo(name, null, number);
            //Contacto contacto = new Contacto(name, number);
            nameContact.add(amigo);
        }
        //Log.v("xyzyx", nameContact.toString());
        cursor.close();
    }


}