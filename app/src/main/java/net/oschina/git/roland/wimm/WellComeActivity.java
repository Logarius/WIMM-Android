package net.oschina.git.roland.wimm;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import net.oschina.git.roland.wimm.common.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_well_come)
public class WellComeActivity extends BaseActivity {

    @ViewInject(R.id.btn_say)
    private Button btnSay;

    @Event(type = View.OnClickListener.class, value = R.id.btn_say)
    private void sayClick(View v) {
        Toast.makeText(this, "Button Say Clicked", Toast.LENGTH_LONG).show();
    }
}
