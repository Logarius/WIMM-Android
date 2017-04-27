package net.oschina.git.roland.wimm.common.base;

import net.oschina.git.roland.wimm.common.view.CommonHeader;

/**
 * Created by Roland on 2017/4/24.
 */

public abstract class HeaderFragment extends BaseFragment {

    protected CommonHeader header;

    public HeaderFragment setHeader(CommonHeader header) {
        this.header = header;
        return this;
    }

    public void refreshHeader() {
        header.reset();
    }
}
