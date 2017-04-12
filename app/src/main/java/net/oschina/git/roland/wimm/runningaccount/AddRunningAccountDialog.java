package net.oschina.git.roland.wimm.runningaccount;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import net.oschina.git.roland.wimm.R;
import net.oschina.git.roland.wimm.common.view.DateSelectDialog;

/**
 * Created by Roland on 2017/4/12.
 */

class AddRunningAccountDialog {

    private Context mContext;

    private AlertDialog.Builder dialogBuilder;

    private EditText etDate, etAmount, etRemark;

    AddRunningAccountDialog(Context context) {
        this.mContext = context;
        dialogBuilder = new AlertDialog.Builder(mContext);
        dialogBuilder.setTitle(R.string.str_add_running_account);

        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.dialog_add_running_account, null);
        dialogBuilder.setView(dialogView);

        dialogBuilder.setNegativeButton(R.string.str_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialogBuilder.setPositiveButton(R.string.str_confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        etDate = (EditText) dialogView.findViewById(R.id.et_date);
        etAmount = (EditText) dialogView.findViewById(R.id.et_inout);
        etRemark = (EditText) dialogView.findViewById(R.id.et_remark);

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DateSelectDialog(mContext).show();
            }
        });
    }

    void show() {
        dialogBuilder.show();
    }
}
