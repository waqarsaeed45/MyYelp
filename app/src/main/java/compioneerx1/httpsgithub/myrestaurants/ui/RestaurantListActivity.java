package compioneerx1.httpsgithub.myrestaurants.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import org.parceler.Parcels;
import java.util.ArrayList;
import compioneerx1.httpsgithub.myrestaurants.Constants;
import compioneerx1.httpsgithub.myrestaurants.R;
import compioneerx1.httpsgithub.myrestaurants.models.Restaurant;
import compioneerx1.httpsgithub.myrestaurants.util.OnRestaurantSelectedListener;

public class RestaurantListActivity extends AppCompatActivity implements OnRestaurantSelectedListener{
    private Integer mPosition;
    ArrayList<Restaurant> mRestaurants;
    String mSource;

       @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
           if (savedInstanceState != null) {
               if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                   mPosition = savedInstanceState.getInt(Constants.EXTRA_KEY_POSITION);
                   mRestaurants = Parcels.unwrap(savedInstanceState.getParcelable(Constants.EXTRA_KEY_RESTAURANTS));
                   mSource = savedInstanceState.getString(Constants.KEY_SOURCE);

                   if (mPosition != null && mRestaurants != null) {
                       Intent intent = new Intent(this, RestaurantDetailActivity.class);
                       intent.putExtra(Constants.EXTRA_KEY_POSITION, mPosition);
                       intent.putExtra(Constants.EXTRA_KEY_RESTAURANTS, Parcels.wrap(mRestaurants));
                       intent.putExtra(Constants.KEY_SOURCE, mSource);
                       startActivity(intent);
                   }

               }

           }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mPosition != null && mRestaurants != null) {
            outState.putInt(Constants.EXTRA_KEY_POSITION, mPosition);
            outState.putParcelable(Constants.EXTRA_KEY_RESTAURANTS, Parcels.wrap(mRestaurants));
            outState.putString(Constants.KEY_SOURCE, mSource);
        }

    }

    @Override
    public void onRestaurantSelected(Integer position, ArrayList<Restaurant> restaurants, String source) {
        mPosition = position;
        mRestaurants = restaurants;
        mSource = source;
    }

}
