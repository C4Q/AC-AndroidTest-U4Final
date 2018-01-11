package nyc.c4q.androidtest_unit4final;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * Created by justiceo on 1/9/18.
 */

public class InfoFragment extends android.support.v4.app.Fragment {
    private View v;
    private TextView appInfo;
    private TextView moreInfo;
    private ToggleButton moreInfoButton;

    public InfoFragment() {
        //empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.info_fragment, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("MORE INFO", "IT WORKS");
        appInfo = v.findViewById(R.id.app_info);
        moreInfo = v.findViewById(R.id.more_textView);
        moreInfo.setVisibility(View.INVISIBLE);

        moreInfoButton = v.findViewById(R.id.more_button);
        moreInfoButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    moreInfo.setVisibility(View.VISIBLE);
                } else {
                    moreInfo.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
