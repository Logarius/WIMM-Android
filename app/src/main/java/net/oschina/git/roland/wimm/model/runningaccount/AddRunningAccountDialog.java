package net.oschina.git.roland.wimm.model.runningaccount;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import net.oschina.git.roland.wimm.R;
import net.oschina.git.roland.wimm.common.base.WIMMConstants;
import net.oschina.git.roland.wimm.common.entities.RunningAccount;
import net.oschina.git.roland.wimm.common.utils.StringUtils;
import net.oschina.git.roland.wimm.common.view.DateSelectDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Roland on 2017/4/12.
 */

class AddRunningAccountDialog {

    private Context mContext;

    private AlertDialog.Builder dialogBuilder;

    private AlertDialog alertDialog;

    private EditText etDate, etAmount, etRemark;

    private OnNewRunningAccountAddListener onNewRunningAccountAddListener;

    AddRunningAccountDialog(Context context) {
        this.mContext = context;
        dialogBuilder = new AlertDialog.Builder(mContext);
        dialogBuilder.setTitle(R.string.str_add_running_account);

        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.dialog_add_running_account, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);
        dialogBuilder.setNegativeButton(R.string.str_cancel, null);
        dialogBuilder.setPositiveButton(R.string.str_confirm, null);

        etDate = (EditText) dialogView.findViewById(R.id.et_date);
        etAmount = (EditText) dialogView.findViewById(R.id.et_inout);
        etRemark = (EditText) dialogView.findViewById(R.id.et_remark);

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateSelectDialog dateSelectDialog = new DateSelectDialog(mContext);
                dateSelectDialog.setOnDateSelectListener(new DateSelectDialog.OnDateSelectListener() {
                    @Override
                    public void onDateSelect(int year, int month, int dayOfMonth) {
                        String strDate = String.format(Locale.US, "%d-%d-%d", year, month, dayOfMonth);
                        etDate.setText(strDate);
                    }
                });
                dateSelectDialog.show();
            }
        });

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(WIMMConstants.RUNNING_ACCOUNT_DATE_FORMAT, Locale.US);
        String strDateToday = simpleDateFormat.format(new Date());
        etDate.setText(strDateToday);
    }

    private View.OnClickListener btnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (StringUtils.isEmpty(etAmount.getText().toString())) {
                Toast.makeText(mContext, R.string.warning_inout_empty, Toast.LENGTH_SHORT).show();
                return;
            }

            if (onNewRunningAccountAddListener != null) {
                double amount = Double.valueOf(etAmount.getText().toString());
                RunningAccount result = new RunningAccount();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(WIMMConstants.RUNNING_ACCOUNT_DATE_FORMAT, Locale.US);
                try {
                    result.setTimeStamp(simpleDateFormat.parse(etDate.getText().toString()).getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                result.setAmount(amount);
                result.setRemark(etRemark.getText().toString());
                onNewRunningAccountAddListener.onNewRunningAccount(result);
            }
            alertDialog.dismiss();
        }
    };

    void setOnNewRunningAccountAddListener(OnNewRunningAccountAddListener onNewRunningAccountAddListener) {
        this.onNewRunningAccountAddListener = onNewRunningAccountAddListener;
    }

    interface OnNewRunningAccountAddListener {
        void onNewRunningAccount(RunningAccount runningAccount);
    }

    void show() {
        alertDialog = dialogBuilder.create();
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(btnClickListener);
    }
}
