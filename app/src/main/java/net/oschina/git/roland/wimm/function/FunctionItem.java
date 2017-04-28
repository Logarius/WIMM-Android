package net.oschina.git.roland.wimm.function;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;

import net.oschina.git.roland.wimm.common.base.WIMMApplication;

/**
 * Created by Roland on 2017/4/26.
 */

class FunctionItem {

    private String title;

    private Drawable icon;

    private String action;

    FunctionItem(String title, Drawable icon) {
        this.title = title;
        this.icon = icon;
    }

    FunctionItem(int titleId, int iconId) {
        Context context = WIMMApplication.getApplication();
        this.title = context.getString(titleId);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.icon = context.getDrawable(iconId);
        } else {
            this.icon = context.getResources().getDrawable(iconId);
        }
    }

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    Drawable getIcon() {
        return icon;
    }

    void setIcon(Drawable icon) {
        this.icon = icon;
    }

    String getAction() {
        return action;
    }

    void setAction(String action) {
        this.action = action;
    }
}
