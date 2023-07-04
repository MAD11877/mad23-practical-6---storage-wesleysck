package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    ArrayList<User> myObject_List = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        for (int i = 0; i <= 20; i++){
            int name = new Random().nextInt();
            int desc = new Random().nextInt();
            Boolean follow = new Random().nextBoolean();

            User user = new User();
            user.setName(String.format("Name%s",String.valueOf(name)));
            user.setDescription("Description "+String.valueOf(desc));
            user.setFollowed(follow);

            myObject_List.add(user);
        }
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        UserAdapter userAdaptor = new UserAdapter(myObject_List, this);
        LinearLayoutManager myLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(myLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(userAdaptor);

    }
}