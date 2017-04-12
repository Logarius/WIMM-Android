package net.oschina.git.roland.wimm.common.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.DatePicker;

import net.oschina.git.roland.wimm.R;

/**
 * Created by Roland on 2017/4/12.
 */

public class DateSelectDialog {

    private static final String TAG = DateSelectDialog.class.getSimpleName();

    private AlertDialog.Builder dialog;

    private DatePicker datePicker;

    private OnDateSelectListener onDateSelectListener;

    public DateSelectDialog(Context context) {
        dialog = new AlertDialog.Builder(context);
        dialog.setTitle(R.string.str_select_date);

        View dialogView = View.inflate(context, R.layout.dialog_date_select, null);
        dialog.setView(dialogView);

        datePicker = (DatePicker) dialogView.findViewById(R.id.date_picker);

        dialog.setPositiveButton(R.string.str_confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (onDateSelectListener != null) {
                    onDateSelectListener.onDateSelect(datePicker.getYear(), datePicker.getMonth()+1, datePicker.getDayOfMonth());
                }
            }
        });

        dialog.setNegativeButton(R.string.str_cancel, null);
    }

    public void show() {
        dialog.show();
    }

    public void setOnDateSelectListener(OnDateSelectListener onDateSelectListener) {
        this.onDateSelectListener = onDateSelectListener;
    }

    public interface OnDateSelectListener {
        void onDateSelect(int year, int month, int dayOfMonth);
    }
}
