package com.fanqu.personcentre.adapter;


import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.personcentre.interfacely.MoneyInputListener;
import com.fanqu.personcentre.model.MoneyEntity;

import java.util.List;

public class MoneyAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<MoneyEntity> datas;
    private LayoutInflater inflater;
    private int oldPostion = -1;
    private MoneyInputListener listener;


    public MoneyAdapter(Activity activity, List<MoneyEntity> data) {
        this.activity = activity;
        this.datas = data;
        inflater = activity.getLayoutInflater();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_moneyselect_item_layout, null);
        MyTextViewHolder textviewholder = new MyTextViewHolder(view);
        return textviewholder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final TextView textView = ((MyTextViewHolder) holder).textView;
        textView.setText(datas.get(position).getMoney());
        if (datas.get(position).isSelected()) {
            textView.setSelected(true);
        } else {
            textView.setSelected(false);
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setSelected(true);
                if (position != oldPostion) {
                    notifyItem(position);
                }
                if (listener != null) {
                    listener.onGetMoneyInput(datas.get(position).getMoney());

                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyTextViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyTextViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv);
        }
    }

    public void setMoneyInputListener(MoneyInputListener listener) {
        this.listener = listener;
    }

    private void notifyItem(int posiont) {
        if (oldPostion >= 0) {
            datas.get(oldPostion).setSelected(false);
            notifyItemChanged(oldPostion);
        }
        oldPostion = posiont;
    }
}
