package miguelangellopezblanca.psp.practica2_psp_ad.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import miguelangellopezblanca.psp.practica2_psp_ad.R;
import miguelangellopezblanca.psp.practica2_psp_ad.model.room.pojo.Amigo;
import miguelangellopezblanca.psp.practica2_psp_ad.viewmodel.ViewModelActivity;

public class ContactsRecyclerAdapter extends RecyclerView.Adapter<ContactsRecyclerAdapter.ViewHolder> {

    private ArrayList<Amigo> contact;
    private Context context;

    private Activity activity;

    ViewModelActivity viewModelActivity;

    public ContactsRecyclerAdapter(Context context, Activity activity, ArrayList<Amigo> contact) {
        this.contact = contact;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ContactsRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view_contacts, parent, false);
        ViewHolder holder = new ViewHolder(vista);
        viewModelActivity = new ViewModelProvider((ViewModelStoreOwner) activity).get(ViewModelActivity.class);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.v("xyzyx", contact.get(position).toString());

        holder.nameContact.setText(contact.get(position).toString());
        holder.constraint_Layout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                try{
                    final NavController navController = Navigation.findNavController(view);
                    Amigo amigo;
                    amigo = contact.get(position);
                    viewModelActivity.setAmigo(amigo);
                    viewModelActivity.insert(amigo);

                    navController.navigate(R.id.amigosRecyclerView);

                }catch (Exception e){
                    e.getMessage();

                }

            }
        });
    }


    @Override
    public int getItemCount() {
        return contact.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameContact;
        ImageView img;
        ConstraintLayout constraint_Layout;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            nameContact = itemView.findViewById(R.id.tvNameContacts);
            img = itemView.findViewById(R.id.imgContacts);
            constraint_Layout = itemView.findViewById(R.id.constraintLayout);
        }
    }
}
