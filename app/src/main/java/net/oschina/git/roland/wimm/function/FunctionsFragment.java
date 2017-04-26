package net.oschina.git.roland.wimm.function;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.oschina.git.roland.wimm.R;
import net.oschina.git.roland.wimm.common.base.HeaderFragment;
import net.oschina.git.roland.wimm.function.rental.RentalAssistantActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roland on 2017/4/21.
 */

public class FunctionsFragment extends HeaderFragment implements FunctionsItemClickListener {

    private View contentView;

    private RecyclerView functionsGrid;

    private RecyclerView.LayoutManager mLayoutManager;

    private FunctionsGridAdapter adapter;

    private List<FunctionItem> functions;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_functions, container, false);
        initComp();
        initData();
        return contentView;
    }

    @Override
    public void refreshHeader() {
        super.refreshHeader();
        header.setTitle(R.string.str_functions);
    }

    private void initComp() {
        functions = new ArrayList<>();
        adapter = new FunctionsGridAdapter(getContext(), functions);
        adapter.setFunctionsItemClickListener(this);
        mLayoutManager = new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false);
        functionsGrid = (RecyclerView) contentView.findViewById(R.id.functions_grid);
        functionsGrid.setLayoutManager(mLayoutManager);
        functionsGrid.setAdapter(adapter);
    }

    private void initData() {
        FunctionItem functionItem = new FunctionItem(R.string.str_rental_assistant, R.drawable.rental);
        functionItem.setTarget(RentalAssistantActivity.class);
        functions.add(functionItem);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view, FunctionItem item) {
        Intent intent = new Intent(getContext(), item.getTarget());
        startActivity(intent);
    }
}
