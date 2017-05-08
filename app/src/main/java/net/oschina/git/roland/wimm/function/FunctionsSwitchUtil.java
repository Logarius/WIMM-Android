package net.oschina.git.roland.wimm.function;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;

import net.oschina.git.roland.wimm.R;
import net.oschina.git.roland.wimm.common.base.WIMMApplication;
import net.oschina.git.roland.wimm.common.entities.Function;

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

    private String[] functionAction;

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

        functionAction = context.getResources().getStringArray(R.array.functions_action);
    }

    public void enableAllFunctions(String userId) {
        for (String functionName : functionNameMap.keySet()) {
            setFunctionEnabled(userId, functionName, true);
        }
    }

    public void disableAllFunctions(String userId) {
        List<Function> functions = Function.findBy(userId);
        if (functions != null) {
            for (Function function : functions) {
                function.setEnable(false);
                function.saveOrUpdate();
            }
        }
    }

    public void setFunctionEnabled(String userId, String functionName, boolean enable) {
        if (functionNameMap.containsKey(functionName)) {
            Function function = Function.findBy(userId, functionName);
            if (function == null && enable) {
                function = new Function();
                function.setUserId(userId);
                function.setFunctionName(functionName);
            }

            if (function != null) {
                function.setEnable(enable);
                function.saveOrUpdate();
            }
        }
    }

    public List<FunctionItem> convertToFunctionItems(List<Function> functions) {
        Context context = WIMMApplication.getApplication();
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
                    item.setAction(functionAction[functionId]);
                    item.setEnable(function.isEnable());
                    result.add(item);
                } else {
                    function.deleteFromDb();
                }
            }
        }

        return result;
    }

    public List<FunctionItem> findEnabledFunctionItems(String userId) {
        List<Function> functions = Function.findBy(userId);
        List<FunctionItem> result = convertToFunctionItems(functions);
        for (FunctionItem item : result) {
            if (!item.isEnable()) {
                result.remove(item);
            }
        }
        return result;
    }
}
