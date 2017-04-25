package net.oschina.git.roland.wimm.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.oschina.git.roland.wimm.R;
import net.oschina.git.roland.wimm.common.base.HeaderFragment;

/**
 * Created by Roland on 2017/4/10.
 */

public class SettingsFragment extends HeaderFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_welcome, container, false);

        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText("SettingsFragment");

        return view;
    }

    @Override
    public void refreshHeader() {
        super.refreshHeader();
        header.setTitle(R.string.str_settings);
    }
}
