package com.foodsharetest.android.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.foodsharetest.android.R;
import com.foodsharetest.android.db.model.Message;
import com.foodsharetest.android.widget.RoundImageView;

import java.util.List;

/**
* 用来适配消息的适配器，并且内置一个MyClickListener用来解决开源库和RecycleView的点击事件监听
 * */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    private List<Message> mMessageList;
    private OnMyItemClickListener listener;

/**
* 定义一个接口和一个
 * */
    public interface OnMyItemClickListener {
        void myClick(View v, int position);
    }

    public void setOnMyItemClickListener(OnMyItemClickListener onMyItemClickListener) {
        this.listener = onMyItemClickListener;
    }

    public MessageAdapter(List<Message> messageList){
        mMessageList = messageList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){
        Message message = mMessageList.get(position);
        holder.header.setImageResource(R.mipmap.default_header);
        holder.title.setText(message.getTitle());
        holder.text.setText(message.getText());

        //设置监听器
        if(listener!=null){
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.myClick(v,position);
                }
            });
        }
    }

    @Override
    public int getItemCount(){
        return mMessageList.size();
    }

    /**
     * 添加或删除Item
    */
    public void addItem(Message message, int position){
        mMessageList.add(message);
        notifyItemChanged(position);
    }

    public void removeItem(int position){
        mMessageList.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }


    /**
     * 在Holder中对控件findviewbyid
     */
    static class ViewHolder extends RecyclerView.ViewHolder{
        RoundImageView header;
        TextView title;
        TextView text;
        Button delete;
        public ViewHolder(View view){
            super(view);
            header = (RoundImageView)view.findViewById(R.id.header);
            title = (TextView)view.findViewById(R.id.title);
            text = (TextView)view.findViewById(R.id.text);
            delete = (Button)view.findViewById(R.id.delete_item);
        }
    }

}
