package com.foodsharetest.android.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.foodsharetest.android.R;
import com.foodsharetest.android.db.model.Article;
import com.foodsharetest.android.widget.RoundImageView;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private List<Article> mArticleList;
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

    public ArticleAdapter(List<Article> articleList){
        mArticleList = articleList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article_layout, parent,false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){
        Article article = mArticleList.get(position);
        holder.header.setImageResource(R.mipmap.default_header);
//        holder.author.setText(article.getAuthor_id());
//        holder.time.setText(article.getTime());
        holder.text.setText(article.getText());

        //设置监听器
//        if(listener!=null){
//            holder.delete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    listener.myClick(v,position);
//                }
//            });
//        }
    }

    @Override
    public int getItemCount(){
        return mArticleList.size();
    }

    /**
     * 添加或删除Item
     */
    public void addItem(Article message, int position){
        mArticleList.add(message);
        notifyItemChanged(position);
    }

    public void removeItem(int position){
        mArticleList.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }


    /**
     * 在Holder中对控件findviewbyid
     */
    static class ViewHolder extends RecyclerView.ViewHolder{
        RoundImageView header;
        TextView author, time, text;
        ImageView share,comment,like;
        public ViewHolder(View view){
            super(view);
            header = (RoundImageView)view.findViewById(R.id.header);
            author = (TextView)view.findViewById(R.id.title);
            time = (TextView)view.findViewById(R.id.time);
            text = (TextView)view.findViewById(R.id.text);
            share = (ImageView) view.findViewById(R.id.share);
            comment = (ImageView) view.findViewById(R.id.comment);
            like = (ImageView) view.findViewById(R.id.like);
        }
    }

}