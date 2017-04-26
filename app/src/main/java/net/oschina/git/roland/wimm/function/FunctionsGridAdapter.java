package net.oschina.git.roland.wimm.function;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.oschina.git.roland.wimm.R;

import java.util.List;

/**
 * Created by Roland on 2017/4/26.
 */

class FunctionsGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private Context context;

    private List<FunctionItem> functions;

    private FunctionsItemClickListener functionsItemClickListener;

    FunctionsGridAdapter(Context context, List<FunctionItem> functions) {
        this.context = context;
        this.functions = functions;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.layout_function_grid_item, null);
        view.setOnClickListener(this);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ItemViewHolder) holder).load(functions.get(position));
    }

    @Override
    public int getItemCount() {
        return functions == null ? 0 : functions.size();
    }

    @Override
    public void onClick(View v) {
        if (functionsItemClickListener != null) {
            functionsItemClickListener.onClick(v, (FunctionItem) v.getTag());
        }
    }

    public void setFunctionsItemClickListener(FunctionsItemClickListener functionsItemClickListener) {
        this.functionsItemClickListener = functionsItemClickListener;
    }

    private static class ItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView icon;

        private TextView title;

        ItemViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            title = (TextView) itemView.findViewById(R.id.title);
        }

        void load(FunctionItem functionItem) {
            itemView.setTag(functionItem);
            icon.setImageDrawable(functionItem.getIcon());
            title.setText(functionItem.getTitle());
        }

    }
}
