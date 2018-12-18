package seyedabdollahi.ir.Adapters;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import org.greenrobot.eventbus.EventBus;
import java.util.ArrayList;
import java.util.List;

import seyedabdollahi.ir.EventBus.ChangeNumber;
import seyedabdollahi.ir.Models.Base;
import seyedabdollahi.ir.R;

public class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {

    private List<Base> items;


    public BaseAdapter(List<Base> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.base_template , viewGroup , false);
        return new BaseAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.baseTxt.setText(items.get(position).getBase());
        if (items.get(position).getValue() == null || items.get(position).getValue().equals("")){
            holder.baseNumber.setHint(items.get(position).getBase());
        }else {
            holder.baseNumber.setText(items.get(position).getValue());
        }
        holder.baseNumber.setSelection(holder.baseNumber.getText().toString().length());
        holder.baseNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                items.get(position).setValue(holder.baseNumber.getText().toString());
                EventBus.getDefault().post(new ChangeNumber(items , position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView baseTxt;
        private EditText baseNumber;

        private ViewHolder(View itemView){
            super(itemView);
            baseTxt = itemView.findViewById(R.id.base_template_txt);
            baseNumber = itemView.findViewById(R.id.base_template_number);
        }
    }
}
