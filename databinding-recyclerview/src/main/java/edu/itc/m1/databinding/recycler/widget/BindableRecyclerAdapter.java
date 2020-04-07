package edu.itc.m1.databinding.recycler.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * A general simple {@link androidx.recyclerview.widget.RecyclerView.Adapter} using with data binding.
 *
 * @author MAO Hieng on 7/26/16.
 */
public class BindableRecyclerAdapter<T> extends RecyclerView.Adapter<BindableViewHolder<T>> {

    private List<T> mData;

    OnItemRecyclerClickListener onItemRecyclerClickListener;

    private final LayoutInflater mInflater;

    @LayoutRes
    private final int mViewLayoutRes;

    private final int mBindingVariableId;

    public BindableRecyclerAdapter(Context context, int viewLayoutRes, int bindingVariableId) {
        this.mInflater = LayoutInflater.from(context);
        this.mViewLayoutRes = viewLayoutRes;
        this.mBindingVariableId = bindingVariableId;
    }

    @Override
    public BindableViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(mViewLayoutRes, parent, false);
        BindableViewHolder<T> holder = new BindableViewHolder<>(view, mBindingVariableId);
        holder.setOnItemRecyclerClickListener(onItemRecyclerClickListener);

        return holder;
    }

    @Override
    public void onBindViewHolder(BindableViewHolder<T> holder, int position) {
        final T item = mData.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public List<T> getData() {
        return mData;
    }

    public T getItemData(BindableViewHolder<T> viewHolder) {
        return mData == null ? null : mData.get(viewHolder.getAdapterPosition());
    }

    public void notifyNewDataSetChanged(List<T> newData) {
        if (mData != null && !mData.isEmpty()) {
            mData.clear();
        }

        mData = newData;
        notifyDataSetChanged();
    }

    public void setOnItemRecyclerClickListener(OnItemRecyclerClickListener onItemRecyclerClickListener) {
        this.onItemRecyclerClickListener = onItemRecyclerClickListener;
    }
}
