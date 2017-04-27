package net.oschina.git.roland.wimm.function;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;

import net.oschina.git.roland.wimm.R;
import net.oschina.git.roland.wimm.common.base.WIMMApplication;
import net.oschina.git.roland.wimm.common.data.Function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Roland on 2017/4/27.
 */

public class FunctionsSwitchUtil {

    private static FunctionsSwitchUtil instance;

    private String[] functionTitles;

    private int[] functionIconIds;

    private Map<String, Integer> functionNameMap = new HashMap<>();

    public static FunctionsSwitchUtil getInstance() {
        if (instance == null) {
            instance = new FunctionsSwitchUtil();
        }
        return instance;
    }

    private FunctionsSwitchUtil() {
        Context context = WIMMApplication.getApplication();
        functionTitles = context.getResources().getStringArray(R.array.functions_title);
        String[] names = context.getResources().getStringArray(R.array.functions_name);
        for (int i = 0; i < names.length; i++) {
            functionNameMap.put(names[i], i);
        }

        TypedArray typedArray = context.getResources().obtainTypedArray(R.array.functions_icon_id);
        functionIconIds = new int[typedArray.length()];
        for (int i = 0; i < typedArray.length(); i++) {
            functionIconIds[i] = typedArray.getResourceId(i, 0);
        }
        typedArray.recycle();
    }

    public void enableAllFunctions(String userId) {
        for (String functionName : functionNameMap.keySet()) {
            enableFunction(userId, functionName);
        }
    }

    public void disableAllFunctions(String userId) {
        Function.deleteByUserId(userId);
    }

    public void enableFunction(String userId, String functionName) {
        if (functionNameMap.containsKey(functionName)) {
            Function function = Function.findBy(userId, functionName);
            if (function == null) {
                function = new Function();
                function.setUserId(userId);
                function.setFunctionName(functionName);
                function.saveOrUpdate();
            }
        }
    }

    public void disableFunction(String userId, String functionName) {
        if (functionNameMap.containsKey(functionName)) {
            Function function = Function.findBy(userId, functionName);
            if (function != null) {
                function.deleteFromDb();
            }
        }
    }

    public List<FunctionItem> findFunctionItemListByUserId(String userId) {
        Context context = WIMMApplication.getApplication();
        List<Function> functions = Function.findFunctions(userId);
        List<FunctionItem> result = new ArrayList<>();

        if (functions != null) {
            for (Function function : functions) {
                String functionName = function.getFunctionName();
                if (functionNameMap.containsKey(functionName)) {
                    Drawable icon;
                    int functionId = functionNameMap.get(functionName);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        icon = context.getDrawable(functionIconIds[functionId]);
                    } else {
                        icon = context.getResources().getDrawable(functionIconIds[functionId]);
                    }
                    FunctionItem item = new FunctionItem(functionTitles[functionId], icon);
                    result.add(item);
                } else {
                    function.deleteFromDb();
                }
            }
        }

        return result;
    }
}
