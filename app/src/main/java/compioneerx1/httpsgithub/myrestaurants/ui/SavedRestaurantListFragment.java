package compioneerx1.httpsgithub.myrestaurants.ui;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedRestaurantListFragment extends Fragment {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private RestaurantListAdapter mAdapter;
    DBManager dbManager;
    public ArrayList<Restaurant> mRestaurants = new ArrayList<>();


    public SavedRestaurantListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DBManager(requireContext(), null, null, 1);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_restaurant_list, container, false);
        ButterKnife.bind(this, view);
        getRestaurants();
        return view;
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

            mAdapter = new RestaurantListAdapter(getActivity(), mRestaurants, new OnRestaurantSelectedListener() {
                @Override
                public void onRestaurantSelected(Integer position, List<Restaurant> restaurants, String source) {
                    Toast.makeText(getContext(), "clicked = " + restaurants.get(position).getName(), Toast.LENGTH_SHORT).show();
                }
            });

            mRecyclerView.setAdapter(mAdapter);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setHasFixedSize(true);

        }
    }
}

