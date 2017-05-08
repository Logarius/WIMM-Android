package net.oschina.git.roland.wimm.model.function;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;

import net.oschina.git.roland.wimm.common.base.WIMMApplication;

/**
 * Created by Roland on 2017/4/26.
 */

public class FunctionItem {

    private String title;

    private Drawable icon;

    private String action;

    private boolean enable;

    public FunctionItem(String title, Drawable icon) {
        this.title = title;
        this.icon = icon;
    }

    public FunctionItem(int titleId, int iconId) {
        Context context = WIMMApplication.getApplication();
        this.title = context.getString(titleId);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.icon = context.getDrawable(iconId);
        } else {
            this.icon = context.getResources().getDrawable(iconId);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
