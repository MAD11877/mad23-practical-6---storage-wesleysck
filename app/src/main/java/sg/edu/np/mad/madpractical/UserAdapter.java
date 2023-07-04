package sg.edu.np.mad.madpractical;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder>{
    private ArrayList<User> list_items;
    private ListActivity activity;
    public UserAdapter(ArrayList<User> list_objects, ListActivity activity){
        this.list_items = list_objects;
        this.activity = activity;
    }
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false);
        UserViewHolder holder = new UserViewHolder(view);
        return holder;
    }
    public void onBindViewHolder(UserViewHolder holder, int position){
        User list_item = list_items.get(position);
        holder.tv1.setText(list_item.getName());
        holder.tv2.setText(list_item.getDescription());


        holder.b_iv.setVisibility(
                list_item.name.endsWith("7")
                        ? View.VISIBLE
                        : View.GONE
        );

        holder.iv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("Profile");
                builder.setMessage(String.valueOf(holder.tv1.getText()));
                builder.setPositiveButton("View", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){

                        Intent profile = new Intent(activity, MainActivity.class);
                        profile.putExtra("name", holder.tv1.getText());
                        profile.putExtra("desc", holder.tv2.getText());
                        activity.startActivity(profile);
                    }
                });
                builder.setNegativeButton("Close", null);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    public int getItemCount(){
        return list_items.size();
    }

}