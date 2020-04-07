package edu.itc.m1.databinding.recycler.widget;


import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by MAO Hieng on 7/26/16.
 */
public class RecyclerConfiguration {

    public static class Builder {
        private boolean hasFixedSize = true;
        private RecyclerView.LayoutManager layoutManager;
        private RecyclerView.ItemDecoration itemDecoration;
        private RecyclerView.ItemAnimator itemAnimator;
        private RecyclerView.Adapter adapter;

        public Builder() {
        }

        public Builder setHasFixedSize(boolean hasFixedSize) {
            this.hasFixedSize = hasFixedSize;
            return this;
        }

        public Builder setAdapter(RecyclerView.Adapter adapter) {
            this.adapter = adapter;
            return this;
        }

        public Builder setLayoutManager(RecyclerView.LayoutManager layoutManager) {
            this.layoutManager = layoutManager;
            return this;
        }

        public Builder setItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
            this.itemDecoration = itemDecoration;
            return this;
        }

        public Builder setItemAnimator(RecyclerView.ItemAnimator itemAnimator) {
            this.itemAnimator = itemAnimator;
            return this;
        }

        public RecyclerConfiguration build() {
            return new RecyclerConfiguration(this);
        }
    }

    // required
    private boolean hasFixedSize = true;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    // optional
    private ObservableField<RecyclerView.ItemDecoration> itemDecoration = new ObservableField<>();
    private ObservableField<RecyclerView.ItemAnimator> itemAnimator = new ObservableField<>();

    private RecyclerConfiguration(Builder b) {
        this.hasFixedSize = b.hasFixedSize;
        this.adapter = b.adapter;
        this.layoutManager = b.layoutManager;
        this.itemDecoration.set(b.itemDecoration);
        this.itemAnimator.set(b.itemAnimator);
    }

    @BindingAdapter("app:recyclerConfig")
    public static void configureRecyclerView(RecyclerView recyclerView, RecyclerConfiguration configuration) {
        recyclerView.setHasFixedSize(configuration.hasFixedSize);
        recyclerView.setAdapter(configuration.adapter);
        recyclerView.setLayoutManager(configuration.layoutManager);

        RecyclerView.ItemAnimator itemAnimator = configuration.itemAnimator.get();
        if (itemAnimator != null)
            recyclerView.setItemAnimator(itemAnimator);

        RecyclerView.ItemDecoration itemDecoration = configuration.itemDecoration.get();
        if (itemDecoration != null)
            recyclerView.addItemDecoration(itemDecoration);
    }

//    public RecyclerView.LayoutManager getLayoutManager() {
//        return layoutManager;
//    }
//
//    public RecyclerView.ItemDecoration getItemDecoration() {
//        return itemDecoration;
//    }
//
//    public RecyclerView.ItemAnimator getItemAnimator() {
//        return itemAnimator;
//    }
//
//    public RecyclerView.Adapter getAdapter() {
//        return adapter;
//    }
//
//    public boolean isHasFixedSize() {
//        return hasFixedSize;
//    }
}
