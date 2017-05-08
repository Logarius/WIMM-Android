package net.oschina.git.roland.wimm.module.rental;

import android.util.Log;

import net.oschina.git.roland.wimm.R;
import net.oschina.git.roland.wimm.common.base.BaseActivity;
import net.oschina.git.roland.wimm.common.view.CommonHeader;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by Roland on 2017/4/21.
 */

@ContentView(R.layout.activity_rental_assistant)
public class RentalAssistantActivity extends BaseActivity {

    @ViewInject(R.id.header)
    private CommonHeader header;

    @Override
    protected void initComp() {
        header.setTitle(R.string.str_rental_assistant);
        header.setLeftFuncIcon(R.drawable.menu);
        header.setRightFuncIcon(R.drawable.plus);
    }

    @Override
    protected void initListener() {
        header.setCommonHeaderListener(commonHeaderListener);
    }

    @Override
    protected void initData() {

    }

    private CommonHeader.CommonHeaderListener commonHeaderListener = new CommonHeader.CommonHeaderListener() {
        @Override
        public void onClick(int viewId) {
            switch (viewId) {
                case CommonHeader.VIEW_IV_LEFT_FUNC:
                    Log.d(TAG, "show apartment list");
                    break;

                case CommonHeader.VIEW_IV_RIGHT_FUNC:
                    Log.d(TAG, "display detail information of selected apartment");
                    break;

                default:
                    break;
            }
        }
    };
}
