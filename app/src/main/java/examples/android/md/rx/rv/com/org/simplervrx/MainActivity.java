package examples.android.md.rx.rv.com.org.simplervrx;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import examples.android.md.rx.rv.com.org.simplervrx.eventBus.ResultEvent;
import examples.android.md.rx.rv.com.org.simplervrx.fragments.AsyncTaskFragment;
import examples.android.md.rx.rv.com.org.simplervrx.fragments.BehaviorFragment;
import examples.android.md.rx.rv.com.org.simplervrx.fragments.CustomViewFragment;
import examples.android.md.rx.rv.com.org.simplervrx.fragments.DetailGithubFragment;
import examples.android.md.rx.rv.com.org.simplervrx.fragments.EventBusFragment;
import examples.android.md.rx.rv.com.org.simplervrx.fragments.FragmentDrawer;
import examples.android.md.rx.rv.com.org.simplervrx.fragments.ParentFragment;
import examples.android.md.rx.rv.com.org.simplervrx.fragments.PostFragment;
import examples.android.md.rx.rv.com.org.simplervrx.fragments.HomeFragment;
import examples.android.md.rx.rv.com.org.simplervrx.fragments.MessagesFragment;
import examples.android.md.rx.rv.com.org.simplervrx.fragments.RVSwipeFragment;
import examples.android.md.rx.rv.com.org.simplervrx.fragments.RxFragment;
import examples.android.md.rx.rv.com.org.simplervrx.fragments.WebViewFragment;
import examples.android.md.rx.rv.com.org.simplervrx.model.Result;

public class MainActivity extends AppCompatActivity
        implements FragmentDrawer.FragmentDrawerListener,
        WebViewFragment.OnFragmentInteractionListener,
        BehaviorFragment.OnFragmentInteractionListener{
    private static final String TAG = MainActivity.class.getName();
    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        // display the first navigation drawer view on app launch
        displayView(0);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected");
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (item.getItemId() == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Log.d(TAG, "displayView");
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                title = getString(R.string.title_home);
                break;
            case 1:
                fragment = new PostFragment();
                title = getString(R.string.title_posts);
                break;
            case 2:
                fragment = new MessagesFragment();
                title = getString(R.string.title_messages);
                break;
            case 3:
                fragment = new RxFragment();
                title = getString(R.string.title_rx_android);
                break;
            case 4:
                fragment = new WebViewFragment();
                title = "WebView Fragment";
                break;
            case 5:
                fragment = new AsyncTaskFragment();
                title = "AsyncTask Fragment";
                break;
            case 6:
                fragment = new EventBusFragment();
                title = "EventBus Fragment";
                break;
            case 7:
                fragment = new BehaviorFragment();
                title = "Behavior Fragment";
                break;
            case 8:
                fragment = new ParentFragment();
                title = "Nested Fragments";
                break;
            case 9:
                fragment = new CustomViewFragment();
                title = "Custom View";
                break;
            case 10:
                fragment = new RVSwipeFragment();
                title = "Recycler View with Swiping Functionality";
                break;
            default:
                break;
        }

        if (fragment != null) {
            Log.d(TAG, "--------------------" + fragment.getClass().getSimpleName() + "--------------------");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_body, fragment, fragment.getClass().getSimpleName())
                    .commit();
            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }

    public void openHTTP() throws IOException {
        Log.d(TAG, "openHTTP");

        URL url = new URL("www.google.com");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = httpURLConnection.getInputStream();
        InputStreamReader reader = new InputStreamReader(inputStream);

        while(reader.read() != -1){
            Log.d(TAG, String.valueOf(reader.read()));
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.d(TAG, "onFragmentInteraction");
    }

    @Override
    public void onFragmentInteraction(Result result) {
        Log.d(TAG, "*********************************************" +
                "************************************************" +
                "BehaviorFragment::onFragmentInteraction::"
                + result.getName() + "::" + getSupportFragmentManager().getFragments().size());

        DetailGithubFragment fragment = new DetailGithubFragment();
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.container_body, fragment, "DetailGithubFragment")
//                .commit();
        fragment.getMessage(result);
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        }
        else {
            getFragmentManager().popBackStack();
        }
    }
}
