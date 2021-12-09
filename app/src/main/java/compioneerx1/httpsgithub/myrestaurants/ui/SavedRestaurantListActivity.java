package compioneerx1.httpsgithub.myrestaurants.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.ButterKnife;
import compioneerx1.httpsgithub.myrestaurants.Constants;
import compioneerx1.httpsgithub.myrestaurants.R;
import compioneerx1.httpsgithub.myrestaurants.adapters.FirebaseRestaurantListAdapter;
import compioneerx1.httpsgithub.myrestaurants.adapters.FirebaseRestaurantViewHolder;
import compioneerx1.httpsgithub.myrestaurants.models.Restaurant;
import compioneerx1.httpsgithub.myrestaurants.util.OnStartDragListener;
import compioneerx1.httpsgithub.myrestaurants.util.SimpleItemTouchHelperCallback;

public class SavedRestaurantListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_restaurant_list);
    }
}