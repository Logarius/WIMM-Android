package net.oschina.git.roland.wimm.function;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import net.oschina.git.roland.wimm.R;
import net.oschina.git.roland.wimm.common.base.HeaderFragment;
import net.oschina.git.roland.wimm.function.rental.RentalAssistantActivity;

/**
 * Created by Roland on 2017/4/21.
 */

public class FunctionsFragment extends HeaderFragment implements View.OnClickListener {

    private static final String TAG = FunctionsFragment.class.getSimpleName();

    private View contentView;

    private Button btnRentalAssistant;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_functions, container, false);
        initComp();
        initListener();
        return contentView;
    }

    private void initComp() {
        btnRentalAssistant = (Button) contentView.findViewById(R.id.btn_rental_assistant);
    }

    private void initListener() {
        btnRentalAssistant.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()) {
            case R.id.btn_rental_assistant:
                intent = new Intent(getContext(), RentalAssistantActivity.class);
                break;

            default:
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }
    }

    @Override
    public void refreshHeader() {
        header.reset();
        header.setTitle(R.string.str_functions);
    }
}
