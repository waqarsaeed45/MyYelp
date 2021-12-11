package compioneerx1.httpsgithub.myrestaurants.ui;


import android.content.ContentValues;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import compioneerx1.httpsgithub.myrestaurants.R;
import compioneerx1.httpsgithub.myrestaurants.adapters.RestaurantListAdapter;
import compioneerx1.httpsgithub.myrestaurants.database.DBManager;
import compioneerx1.httpsgithub.myrestaurants.models.Restaurant;
import compioneerx1.httpsgithub.myrestaurants.services.YelpService;
import compioneerx1.httpsgithub.myrestaurants.util.OnRestaurantSelectedListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantListFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.filterSpinner)
    Spinner mFilterSpinner;

    @BindView(R.id.etSearch)
    EditText etSearch;

    private RestaurantListAdapter mAdapter;
    public List<Restaurant> mRestaurants = new ArrayList<>();


    DBManager dbManager;

    String[] filters = {"Price", "Ratings"};


    public RestaurantListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        dbManager = new DBManager(requireContext(), null, null, 1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_list, container, false);
        ButterKnife.bind(this, view);

        //code for spinner
        mFilterSpinner.setOnItemSelectedListener(this);
        ArrayAdapter ad
                = new ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                filters);
        mFilterSpinner.setAdapter(ad);

        //*******ends*******

        getRestaurants("");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListners();
    }

    private void initListners() {

        etSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (etSearch.getRight() - etSearch.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        etSearch.setText("");
                        return true;
                    }
                    if (event.getRawX() >= (etSearch.getLeft() - etSearch.getCompoundDrawables()[DRAWABLE_LEFT].getBounds().width())) {
                        // your action here
                        getRestaurants(etSearch.getText().toString());
                        return true;
                    }
                }
                return false;
            }
        });
    }

    private void getRestaurants(String category) {
        final YelpService yelpService = new YelpService();

        yelpService.findRestaurants(category, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mRestaurants = yelpService.processResults(response);

                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mAdapter = new RestaurantListAdapter(getActivity(), mRestaurants, new OnRestaurantSelectedListener() {
                            @Override
                            public void onRestaurantSelected(Integer position, List<Restaurant> restaurants, String source) {
                                showFavDialog(restaurants.get(position));
                            }
                        });
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }

    void showFavDialog(Restaurant restaurant) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add to favourite?")
                .setMessage("Do you want to add this item to favourites?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> {
                    insertToDBAsFav(restaurant);

                })
                .setNegativeButton("No", (dialog, id) -> {
                    Toast.makeText(getContext(), "clicked = " + restaurant.getName(), Toast.LENGTH_SHORT).show();
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void insertToDBAsFav(Restaurant restaurant) {

        ContentValues values = new ContentValues();
        values.put("name", restaurant.getName());
        values.put("phone", restaurant.getPhone());
        values.put("website", restaurant.getWebsite());
        values.put("rating", restaurant.getRating());
        values.put("imageUrl", restaurant.getImageUrl());
        values.put("price", restaurant.getPrice());
        values.put("address", restaurant.getAddress().toString());
        values.put("latitude", restaurant.getLatitude());
        values.put("longitude", restaurant.getLongitude());
        values.put("categories", restaurant.getCategories().toString());
        values.put("pushId", restaurant.getPushId());
        long check = dbManager.Insert(values);
        if (check == 0) {
            Toast.makeText(requireContext(), "record not inserted is = " + check + "", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(requireContext(), "record inserted ID is = " + check + "", Toast.LENGTH_LONG).show();

        }
    }

//    @Override
//// Method is now void, menu inflater is now passed in as argument:
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//
//        // Call super to inherit method from parent:
//        super.onCreateOptionsMenu(menu, inflater);
//
//        inflater.inflate(R.menu.menu_search, menu);
//
//        MenuItem menuItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
//        searchView.setMaxWidth(Integer.MAX_VALUE);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                getRestaurants(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
//    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (filters[position].equals("Price")) {
            sortByPrice();
        } else if (filters[position].equals("Ratings")) {
            sortByRatings();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private void sortByPrice() {
        for (int c = 0; c < (mRestaurants.size() - 1); c++) {
            for (int d = 0; d < mRestaurants.size() - c - 1; d++) {
                if (mRestaurants.get(d).getPrice().length() >
                        mRestaurants.get(d + 1).getPrice().length() || mRestaurants.get(d + 1).getPrice().length() == 0) /* For descending order use < */ {
                    Restaurant swap = new Restaurant();
                    swap = mRestaurants.get(d);
                    mRestaurants.set(d, mRestaurants.get(d + 1));
                    mRestaurants.set(d + 1, swap);
                }
            }
        }
        if (mAdapter != null)
            mAdapter.notifyDataSetChanged();
    }

    private void sortByRatings() {
        for (int c = 0; c < (mRestaurants.size() - 1); c++) {
            for (int d = 0; d < mRestaurants.size() - c - 1; d++) {
                if (mRestaurants.get(d).getRating() <
                        mRestaurants.get(d + 1).getRating()) /* For descending order use < */ {
                    Restaurant swap = new Restaurant();
                    swap = mRestaurants.get(d);
                    mRestaurants.set(d, mRestaurants.get(d + 1));
                    mRestaurants.set(d + 1, swap);
                }
            }
        }
        if (mAdapter != null)
            mAdapter.notifyDataSetChanged();
    }
}
