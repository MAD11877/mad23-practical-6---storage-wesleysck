package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    String TAG = "RecyclerView";
    ArrayList<User> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        DBHandler DB = new DBHandler(this, null,null,1);

        for(int i = 0; i<20; i++){
            Random random = new Random();
            long Name = random.nextLong() + 100000000L;// Generates ID bigger than  10million
            String ID = Long.toString(Name);
            long Desc = random.nextLong() + 100000000L;// Generates ID bigger than  10million
            String description = Long.toString(Desc);
            Log.v(TAG,ID);
            User obj = new User(ID, description, i,false);


            DB.addUser(obj);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        UserAdapter myCustomAdaptor = new UserAdapter(DB.getUsers(),this);
        LinearLayoutManager myLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(myLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myCustomAdaptor);

    }
}