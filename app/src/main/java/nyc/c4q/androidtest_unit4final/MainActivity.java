package nyc.c4q.androidtest_unit4final;


import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ColorAdapter adapter;
    protected HashMap<String, String> colorDict;
    protected List<String> colorsList;
    private TextView appInfo;
    private TextView moreInfo;
    private ToggleButton moreInfoButton;
    private Backend colorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appInfo = findViewById(R.id.app_info);
        moreInfo = findViewById(R.id.more_textView);
        moreInfoButton = findViewById(R.id.more_button);

        colorDict = new HashMap<>();
        colorDict.put("indigo", "#4b0082");
        colorDict.put("green", "#00ff00");
        colorDict.put("blue", "#0000ff");
        colorDict.put("red", "#ff0000");
        colorDict.put("purple", "#800080");
        colorDict.put("orange", "#ffa500");
        colorDict.put("brown", "#a52a2a");
        colorDict.put("black", "#000000");

        // TODO: adding all the colors and their values would be tedious, instead fetch it from the url below
        // https://raw.githubusercontent.com/operable/cog/master/priv/css-color-names.json

        colorsList = new ArrayList<>();
        String[] names = new String[]{"blue", "red", "purple", "indigo", "orange", "brown", "black", "green"};
        for (String n : names) colorsList.add(n);

        Sort.selectionSort(colorsList,false);

        RecyclerView recyclerView = findViewById(R.id.rv);
        adapter = new ColorAdapter(colorsList, colorDict);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


//        Call<Color> color = colorService.getNames();
//                color.enqueue(new Callback<Color>() {
//                    @Override
//                    public void onResponse(Call<Color> call, Response<Color> response) {
//                        Log.d("Color call: ", "onResponse: " + response.body().getColorDict());
//                    }
//
//                    @Override
//                    public void onFailure(Call<Color> call, Throwable t) {
//                        Log.d("Color call: ", "onResponse: " + t.toString());
//                    }
//                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.info_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.info:
                Toast.makeText(this, "INFO CLICKED", Toast.LENGTH_SHORT).show();
                moreInfo.setVisibility(View.INVISIBLE);
                moreInfoButton.setVisibility(View.INVISIBLE);
                appInfo.setVisibility(View.INVISIBLE);

                return true;
            default:
                moreInfo.setVisibility(View.VISIBLE);
                moreInfoButton.setVisibility(View.VISIBLE);
                appInfo.setVisibility(View.VISIBLE);
                return super.onOptionsItemSelected(item);
        }
    }

    // TODO: When "Info" menu item is clicked, display the fragment InfoFragment
    // TODO: If InfoFragment is already visible and I click "Info", remove InfoFragment from the view.
    // Link to creating options menu: https://github.com/C4Q/AC-Android/tree/v2/Android/Lecture-9-Menus-and-Navigation#anatomy-of-menu-xml


}