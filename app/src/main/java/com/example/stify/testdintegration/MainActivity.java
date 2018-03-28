package com.example.stify.testdintegration;

import android.app.ActionBar;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {
    private final String _restoName = "Matsuri Lyon Presqu'île";
    private final String _restoAddress = "7 Rue de la Fromagerie, Lyon";
    private final String _restoTag = "Asiatique";
    private final String _restoOpeningTime = "7:00 à 10:00, 12:00 à 15:30";
    private final String _restoNotation = "4,5";
    private final List<MenuCell> listEntries = new ArrayList<>();
    private final String ALL_CATEGORY = "This String will never be the name of a dish";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enter fake data
        listEntries.add(new MenuCell("Sushi thon", "Sushis", 4.3, "2 pièces sushi thon et riz vinaigré"));
        listEntries.add(new MenuCell("Sushi saumon à la carte", "Sushis", 4, "2 pièces sushi saumon et riz vinaigré"));
        listEntries.add(new MenuCell("Sushi saumon et avocat", "Sushis", 4, "2 sushis saumon avocat en surface et riz vinaigré"));
        listEntries.add(new MenuCell("Jus de mangue", "Boissons", 3, "Jus de mangue Granini en bouteille verre 25cl"));
        listEntries.add(new MenuCell("Soupe miso", "Entrées", 3.5, "Soupe au soja fermenté et agrémentée de tofu, poireaux et algues"));
        listEntries.add(new MenuCell("Riz nature", "Entrées", 2.5, "300 grammes"));
        listEntries.add(new MenuCell("Salade d'algues", "Entrées", 2.5, "Salade aux algues légérement épicées"));

        setupToolbar();

        setupTabs();

        setupCardView();

        setupSearch();

        setupRecyclerView(ALL_CATEGORY);

        setupScroll();
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.buttonCallWhite:
                call();
                return true;
            case R.id.buttonLocateWhite:
                locate();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void toast(View v) {
        View parent = (View) v.getParent();
        TextView textView = parent.findViewById(R.id.cellName);
        CharSequence text = textView.getText();
        final StringBuilder sb = new StringBuilder(text.length());
        sb.append(text);
        String name = sb.toString();
        Toast toast = Toast.makeText(this, "Vous avez ajouté " + name + " au panier.", Toast.LENGTH_SHORT);
        toast.show();
    }

    private void call() {
        Toast toast = Toast.makeText(this, "Vous pouvez joindre ce restaurant au 04 78 27 83 06", Toast.LENGTH_LONG);
        toast.show();
    }

    private void locate() {
        Toast toast = Toast.makeText(this, "Adresse: 7 Rue de la Fromagerie, 69001 Lyon", Toast.LENGTH_LONG);
        toast.show();
    }

    private void setupCardView() {

        TextView name = findViewById(R.id.card_name);
        name.setText(_restoName);

        TextView notation = findViewById(R.id.card_notation);
        notation.setText(_restoNotation);

        TextView address = findViewById(R.id.card_address);
        address.setText(_restoAddress);

        TextView tag = findViewById(R.id.card_tag);
        tag.setText(_restoTag);

        TextView openingTime = findViewById(R.id.card_openingtime);
        openingTime.setText(_restoOpeningTime);
    }

    private void setupRecyclerView(String category) {
        MenuListAdapter adapter = new MenuListAdapter(this);
        RecyclerView recyclerView = findViewById(R.id.menu_list);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);

        for (int i = 0; i < listEntries.size(); i++)
            if (category == ALL_CATEGORY || category == listEntries.get(i).getCategory())
                adapter.add(listEntries.get(i));

        recyclerView.setAdapter(adapter);
        recyclerView.setFocusable(false);
    }

    private void setupToolbar() {
        final Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        final int colorBackground = findViewById(R.id.main_root_layout).getDrawingCacheBackgroundColor();
        //getSupportActionBar().setTitle(_restoName);
        TextView title = findViewById(R.id.toolbar_text);
        title.setText(_restoName);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable leftArrow = getResources().getDrawable(R.drawable.left_arrow_white);
        getSupportActionBar().setHomeAsUpIndicator(leftArrow);
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.finish();
            }
        });

        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_container);
        AppBarLayout appBar= (AppBarLayout)  findViewById(R.id.my_appbar_layout);

        final int colorbackground = ContextCompat.getColor(this, R.color.appBackground);
        final int colorbackgroundsecond = ContextCompat.getColor(this, R.color.nodesBackground);

        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Menu menu = myToolbar.getMenu();
                TextView title = findViewById(R.id.toolbar_text);
                SearchView searchBarFirst = findViewById(R.id.searchbarfirst);
                SearchView searchBarSecond = findViewById(R.id.searchbarsecond);
                NestedScrollView scroll = findViewById(R.id.scroll_view);
                CardView card = findViewById(R.id.card_view);
                RecyclerView list = findViewById(R.id.menu_list);
                View scrollLayout = findViewById(R.id.relativelayout_of_scrollview);
                TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs_layout);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)list.getLayoutParams();

                if ((collapsingToolbar.getHeight() + verticalOffset) < (2 * ViewCompat.getMinimumHeight(collapsingToolbar))) {
                    myToolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
                    title.setVisibility(View.VISIBLE);
                    findViewById(R.id.main_root_layout).setBackgroundColor(colorbackgroundsecond);
                    list.setBackgroundColor(colorbackgroundsecond);
                    tabLayout.setVisibility(View.VISIBLE);
                    //card.setVisibility(View.INVISIBLE);
                    searchBarFirst.setVisibility(View.INVISIBLE);
                    searchBarSecond.setVisibility(View.VISIBLE);
                    //RelativeLayout.LayoutParams paramsSecond = params;
                    //paramsSecond.addRule(RelativeLayout.BELOW, R.id.tabs_layout);
                    //list.setLayoutParams(paramsSecond);

                    for(int i = 0; i < menu.size(); i++){
                        Drawable drawable = menu.getItem(i).getIcon();
                        if(drawable != null) {
                            drawable.mutate();
                            drawable.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
                        }
                    }
                }
                else {
                    myToolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
                    title.setVisibility(View.INVISIBLE);
                    //searchBar.setVisibility(View.VISIBLE);
                    card.setVisibility(View.INVISIBLE);
                    card.setVisibility(View.VISIBLE);
                    findViewById(R.id.main_root_layout).setBackgroundColor(colorbackground);
                    list.setBackgroundColor(colorbackground);
                    tabLayout.setVisibility(View.INVISIBLE);
                    searchBarFirst.setVisibility(View.INVISIBLE);
                    searchBarFirst.setVisibility(View.VISIBLE);
                    searchBarSecond.setVisibility(View.INVISIBLE);

                    for(int i = 0; i < menu.size(); i++){
                        Drawable drawable = menu.getItem(i).getIcon();
                        if(drawable != null) {
                            drawable.mutate();
                            drawable.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
                        }
                    }
                }
            }
        });
    }

    private void setupTabs() {
        String lastCategory = "";
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs_layout);
        for (int i = 0; i < listEntries.size(); i++)
            if (listEntries.get(i).getCategory() != lastCategory) {
                lastCategory = listEntries.get(i).getCategory();
                tabLayout.addTab(tabLayout.newTab().setText(lastCategory));
            }
    }

    private void setupScroll() {
        NestedScrollView scroll = findViewById(R.id.scroll_view);
        LinearLayout layout = findViewById(R.id.linearlayout_of_scrollview);
        layout.requestFocus();
    }

    private void setupSearch() {
        SearchView searchView = findViewById(R.id.searchbarfirst);
        searchView.setQueryHint("Choisissez dans le resto");
        searchView.setIconifiedByDefault(false);
        searchView.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        searchView = findViewById(R.id.searchbarsecond);
        searchView.setQueryHint("Choisissez dans le resto");
        searchView.setIconifiedByDefault(false);
        searchView.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
    }
}
