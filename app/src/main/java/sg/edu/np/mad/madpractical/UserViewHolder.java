package sg.edu.np.mad.madpractical;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {
    TextView tv1;
    TextView tv2;

    ImageView iv;
    ImageView b_iv;

    public UserViewHolder(View itemView){
        super(itemView);
        tv1 = itemView.findViewById(R.id.ul_Name);
        tv2 = itemView.findViewById(R.id.ul_Description);
        iv = itemView.findViewById(R.id.ul_smallImg);
        b_iv = itemView.findViewById(R.id.ul_bigImg);
    }
}