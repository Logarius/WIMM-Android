package net.oschina.git.roland.wimm.common.base;

import android.support.v4.app.Fragment;

import net.oschina.git.roland.wimm.common.view.CommonHeader;

/**
 * Created by Roland on 2017/4/24.
 */

public abstract class HeaderFragment extends Fragment {

    protected CommonHeader header;

    public void setHeader(CommonHeader header) {
        this.header = header;
    }

    public abstract void refreshHeader();
}
