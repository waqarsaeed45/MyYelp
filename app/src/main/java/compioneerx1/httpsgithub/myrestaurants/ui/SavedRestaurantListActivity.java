package compioneerx1.httpsgithub.myrestaurants.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import compioneerx1.httpsgithub.myrestaurants.R;
import compioneerx1.httpsgithub.myrestaurants.adapters.RestaurantListAdapter;
import compioneerx1.httpsgithub.myrestaurants.database.DBManager;
import compioneerx1.httpsgithub.myrestaurants.models.Restaurant;
import compioneerx1.httpsgithub.myrestaurants.util.OnRestaurantSelectedListener;

public class SavedRestaurantListActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private RestaurantListAdapter mAdapter;
    DBManager dbManager;
    public ArrayList<Restaurant> mRestaurants = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_restaurant_list);
        ButterKnife.bind(this);
        dbManager = new DBManager(this, null, null, 1);
        getRestaurants();

    }

    private void getRestaurants() {


        Cursor cursor = dbManager.getAllRestaurants();
        cursor.moveToFirst();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Restaurant restaurant = new Restaurant(cursor.getString(1), cursor.getString(2), cursor.getString(3), Float.parseFloat(cursor.getString(4)), cursor.getString(5), cursor.getString(6), new ArrayList<String>(Arrays.asList(cursor.getString(7).split(","))), Double.parseDouble(cursor.getString(8)), Double.parseDouble(cursor.getString(9)), new ArrayList<String>(Arrays.asList(cursor.getString(10).split(","))));
                mRestaurants.add(restaurant);
            }
            while (cursor.moveToNext());

            mAdapter = new RestaurantListAdapter(this, mRestaurants, new OnRestaurantSelectedListener() {
                @Override
                public void onRestaurantSelected(Integer position, List<Restaurant> restaurants, String source) {
                }
            });

            mRecyclerView.setAdapter(mAdapter);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setHasFixedSize(true);

        }
    }
}