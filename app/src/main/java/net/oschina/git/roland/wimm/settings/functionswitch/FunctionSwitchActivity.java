package net.oschina.git.roland.wimm.settings.functionswitch;

import android.widget.ListView;

import net.oschina.git.roland.wimm.R;
import net.oschina.git.roland.wimm.common.base.BaseActivity;
import net.oschina.git.roland.wimm.common.entities.Function;
import net.oschina.git.roland.wimm.common.entities.User;
import net.oschina.git.roland.wimm.common.view.CommonHeader;
import net.oschina.git.roland.wimm.function.FunctionItem;
import net.oschina.git.roland.wimm.function.FunctionsSwitchUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roland on 2017/4/28.
 */

@ContentView(R.layout.activity_function_switch)
public class FunctionSwitchActivity extends BaseActivity {

    @ViewInject(R.id.header)
    private CommonHeader header;

    @ViewInject(R.id.lv_function_switch)
    private ListView lvFunctionSwitch;

    private FunctionSwitchAdapter adapter;

    private List<FunctionItem> functionItems;

    private List<Function> functions;

    private User user;

    private FunctionsSwitchUtil functionsSwitchUtil = FunctionsSwitchUtil.getInstance();

    @Override
    protected void initComp() {
        header.setTitle(R.string.str_functions_setting);
        functionItems = new ArrayList<>();
        adapter = new FunctionSwitchAdapter(this, functionItems);
        lvFunctionSwitch.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        adapter.setOnFunctionItemSwitchListener(functionItemSwitchListener);
    }

    @Override
    protected void initData() {
        user = application.getUser();
        functions = Function.findBy(user.getUserId());
        functionItems.clear();
        List<FunctionItem> temp = functionsSwitchUtil.convertToFunctionItems(functions);
        functionItems.addAll(temp);
        adapter.notifyDataSetChanged();
    }

    private OnFunctionItemSwitchListener functionItemSwitchListener = new OnFunctionItemSwitchListener() {
        @Override
        public void onSwitch(int position) {
            Function function = functions.get(position);
            FunctionItem functionItem = functionItems.get(position);
            functionsSwitchUtil.setFunctionEnabled(user.getUserId(), function.getFunctionName(), functionItem.isEnable());
        }
    };
}
