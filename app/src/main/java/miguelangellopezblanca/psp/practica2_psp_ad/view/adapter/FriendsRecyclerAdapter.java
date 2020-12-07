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

import java.util.List;

import miguelangellopezblanca.psp.practica2_psp_ad.R;
import miguelangellopezblanca.psp.practica2_psp_ad.model.room.pojo.Amigo;
import miguelangellopezblanca.psp.practica2_psp_ad.model.room.pojo.CallFriend;
import miguelangellopezblanca.psp.practica2_psp_ad.viewmodel.ViewModelActivity;

public class FriendsRecyclerAdapter extends RecyclerView.Adapter<FriendsRecyclerAdapter.ViewHolder> {

    private List<CallFriend> friend;

    private Amigo amigo = new Amigo();

    private Activity activity;

    ViewModelActivity viewModelActivity;

    public FriendsRecyclerAdapter(Activity activity,List<CallFriend> friend) {
        this.activity = activity;
        this.friend = friend;
    }

    @NonNull
    @Override
    public FriendsRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyler_view_amigos, parent, false);
        ViewHolder holder = new ViewHolder(vista);
        viewModelActivity = new ViewModelProvider((ViewModelStoreOwner) activity).get(ViewModelActivity.class);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameFriend.setText(friend.get(position).getAmigo().toString());
        holder.constraint_Layout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                try{
                    final NavController navController = Navigation.findNavController(view);

                    amigo = friend.get(position).getAmigo();
                    viewModelActivity.setCurrentAmigo(amigo);
                    viewModelActivity.setContador(friend.get(position).getCount());

                    navController.navigate(R.id.friendsFragment);

                }catch (Exception e){
                    e.getMessage();

                }

            }
        });
    }


    @Override
    public int getItemCount() {
        return friend.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameFriend;
        ImageView img;
        ConstraintLayout constraint_Layout;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            nameFriend = itemView.findViewById(R.id.tvNameFriend);
            img = itemView.findViewById(R.id.imgFriend);
            constraint_Layout = itemView.findViewById(R.id.constraintLayoutFriend);
        }
    }
}
