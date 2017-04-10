package net.oschina.git.roland.wimm.runningacount;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.oschina.git.roland.wimm.R;

/**
 * Created by Roland on 2017/4/10.
 */

public class RunningAcountFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_welcome, container, false);

        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText("RunningAcountFragment");

        return view;
    }
}
