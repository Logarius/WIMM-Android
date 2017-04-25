package net.oschina.git.roland.wimm.function.rental;

import net.oschina.git.roland.wimm.R;
import net.oschina.git.roland.wimm.common.base.BaseActivity;
import net.oschina.git.roland.wimm.common.view.CommonHeader;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_rental_assistant)
public class RentalAssistantActivity extends BaseActivity {

    @ViewInject(R.id.header)
    private CommonHeader header;

    @Override
    protected void initComp() {
        header.setTitle(R.string.str_rental_assistant);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}
