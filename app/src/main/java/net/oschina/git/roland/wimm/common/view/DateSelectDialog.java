package net.oschina.git.roland.wimm.common.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

import net.oschina.git.roland.wimm.R;

/**
 * Created by Roland on 2017/4/12.
 */

public class DateSelectDialog {

    private AlertDialog.Builder dialog;

    public DateSelectDialog(Context context) {
        dialog = new AlertDialog.Builder(context);
        dialog.setTitle(R.string.str_select_date);

        View dialogView = View.inflate(context, R.layout.dialog_date_select, null);
        dialog.setView(dialogView);

        dialog.setPositiveButton(R.string.str_confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialog.setNegativeButton(R.string.str_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
    }

    public void show() {
        dialog.show();
    }
}
