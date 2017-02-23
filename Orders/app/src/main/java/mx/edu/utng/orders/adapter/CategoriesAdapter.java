package mx.edu.utng.orders.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mx.edu.utng.orders.R;
import mx.edu.utng.orders.model.Categories;
import mx.edu.utng.orders.model.Product;

/**
 * Created by Jorge on 23/02/2017.
 */
public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> implements View.OnClickListener {

    List<Categories> categories;
    View.OnClickListener listener;

    public CategoriesAdapter(List<Categories> categories){
        this.categories = categories;
    }

    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }


    @Override
    public CategoriesAdapter.CategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categories,parent,false);
        CategoriesViewHolder holder = new CategoriesViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(CategoriesAdapter.CategoriesViewHolder holder, int position) {
        holder.tvCategoryName.setText(categories.get(position).getName());
        holder.tvCategoryDescription.setText(categories.get(position).getDescription());
        holder.tvCategoryNumber.setText(String.valueOf(categories.get(position).getNumber()));
        holder.ivCategory.setImageResource(R.mipmap.ic_launcher);
        holder.setListener(this);

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


    @Override
    public void onClick(View v) {
        if(listener!= null){
            listener.onClick(v);
        }
    }

    public static class CategoriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cvCategory;
        TextView tvCategoryName;
        TextView tvCategoryDescription;
        TextView tvCategoryNumber;
        ImageView ivCategory;
        ImageButton btEditCategory;
        ImageButton btDeleteCategory;
        View.OnClickListener listener;

        CategoriesViewHolder(View itemView) {
            super(itemView);
            cvCategory = (CardView) itemView.findViewById(R.id.cv_category);
            ivCategory = (ImageView) itemView.findViewById(R.id.iv_category);
            tvCategoryName = (TextView) itemView.findViewById(R.id.tv_category_name);
            tvCategoryDescription = (TextView) itemView.findViewById(R.id.tv_category_description);
            tvCategoryNumber = (TextView) itemView.findViewById(R.id.tv_category_number);
            btEditCategory = (ImageButton) itemView.findViewById(R.id.bt_edit_category);
            btDeleteCategory = (ImageButton) itemView.findViewById(R.id.bt_delete_category);
            btEditCategory.setOnClickListener(this);
            btDeleteCategory.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(listener!=null){
                listener.onClick(v);
            }
        }

        public void setListener(View.OnClickListener listener) {
            this.listener = listener;
        }
    }
}
