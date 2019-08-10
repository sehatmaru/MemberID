package sehatmaru.memberid.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import sehatmaru.memberid.R;
import sehatmaru.memberid.realms.ItemRealm;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

    private List<ItemRealm> listData;
    private OnClickItemListener onClickItem;

    public ItemAdapter(Context context, List<ItemRealm> listData, OnClickItemListener onClick) {
        this.listData = listData;
        LayoutInflater.from(context);
        this.onClickItem = onClick;
    }

    public interface OnClickItemListener {
        void OnClickItem(String id);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        return new MyViewHolder(itemView);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ItemRealm item = listData.get(position);

        if (item.getType().equals("Voucher")) holder.itemCV.setCardBackgroundColor(R.color.voucherColor);
        else if (item.getType().equals("Product")) holder.itemCV.setCardBackgroundColor(R.color.productColor);

        holder.typeTV.setText(item.getType());
        holder.poinTV.setText(item.getPoin() + " Poin");
        holder.giftTV.setText("Gift Card IDR" + item.getGift());

        holder.itemCV.setOnClickListener(view -> onClickItem.OnClickItem(item.getId()));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        CardView typeCV, itemCV;
        TextView typeTV, poinTV, giftTV;

        MyViewHolder(View itemView) {
            super(itemView);
            typeCV = itemView.findViewById(R.id.typeCV);
            itemCV = itemView.findViewById(R.id.itemCV);
            typeTV = itemView.findViewById(R.id.typeTV);
            poinTV = itemView.findViewById(R.id.poinTV);
            giftTV = itemView.findViewById(R.id.giftTV);
        }
    }
    public void animateTo(List<ItemRealm> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }
    private void applyAndAnimateRemovals(List<ItemRealm> newModels) {
        for (int i = listData.size() - 1; i >= 0; i--) {
            final ItemRealm model = listData.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<ItemRealm> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final ItemRealm model = newModels.get(i);
            if (!listData.contains(model)) {
                addItem(i, model);
            }
        }
    }
    private void addItem(int position, ItemRealm model) {
        listData.add(position, model);
        notifyItemInserted(position);
    }
    private void applyAndAnimateMovedItems(List<ItemRealm> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final ItemRealm model = newModels.get(toPosition);
            final int fromPosition = listData.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    private void moveItem(int fromPosition, int toPosition) {
        final ItemRealm model = listData.remove(fromPosition);
        listData.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }
    private void removeItem(int position) {
        listData.remove(position);
        notifyItemRemoved(position);
    }
}
