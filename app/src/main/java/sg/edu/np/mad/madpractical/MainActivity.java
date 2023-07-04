package sg.edu.np.mad.madpractical;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v(TAG, "onCreate");

        TextView UserID = findViewById(R.id.textView2);
        Button follow = findViewById(R.id.button);

        Intent receivedIntent = getIntent();
        if (receivedIntent != null) {
            String name = receivedIntent.getStringExtra("name");
            String desc = receivedIntent.getStringExtra("desc");

            if (name != null) {
                UserID.setText(name);
            }

            // Retrieve the user object from the database
            DBHandler DB = new DBHandler(this, null, null, 1);
            user = DB.getUser();
            if (user == null) {
                // If user not found in the database, create a new user object
                user = new User(name, desc, 0, false);
                DB.addUser(user);
            } else {
                // Update the user object with the received name and description
                user.setName(name);
                user.setDescription(desc);
            }
        }

        if (user != null) {
            if (user.isFollowed()) {
                follow.setText("Unfollow");
            } else {
                follow.setText("Follow");
            }
        }

        DBHandler DB = new DBHandler(this, null, null, 1);
        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null) {
                    if (user.isFollowed()) {
                        user.setFollowed(false);
                        follow.setText("Follow");
                        Toast.makeText(MainActivity.this, "Unfollowed", Toast.LENGTH_SHORT).show();
                    } else {
                        user.setFollowed(true);
                        follow.setText("Unfollow");
                        Toast.makeText(MainActivity.this, "Followed", Toast.LENGTH_SHORT).show();
                    }
                    DB.updateUser(user);
                }
            }
        });

    }
}