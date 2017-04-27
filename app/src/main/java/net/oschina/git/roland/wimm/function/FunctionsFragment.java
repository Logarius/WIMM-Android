package net.oschina.git.roland.wimm.function;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import net.oschina.git.roland.wimm.R;
import net.oschina.git.roland.wimm.common.base.HeaderFragment;
import net.oschina.git.roland.wimm.function.rental.RentalAssistantActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roland on 2017/4/21.
 */
@ContentView(R.layout.fragment_functions)
public class FunctionsFragment extends HeaderFragment implements FunctionsItemClickListener {

    @ViewInject(R.id.functions_grid)
    private RecyclerView functionsGrid;

    private RecyclerView.LayoutManager mLayoutManager;

    private FunctionsGridAdapter adapter;

    private List<FunctionItem> functions;

    @Override
    public void refreshHeader() {
        super.refreshHeader();
        header.setTitle(R.string.str_functions);
    }

    @Override
    protected void initComp() {
        functions = new ArrayList<>();
        adapter = new FunctionsGridAdapter(getContext(), functions);
        adapter.setFunctionsItemClickListener(this);
        mLayoutManager = new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false);
        functionsGrid.setLayoutManager(mLayoutManager);
        functionsGrid.setAdapter(adapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
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
