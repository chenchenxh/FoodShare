package com.foodsharetest.android.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.foodsharetest.android.R;
import com.foodsharetest.android.db.LoginUser;
import com.foodsharetest.android.db.model.Article;
import com.foodsharetest.android.db.model.User;

import org.litepal.LitePal;

import java.util.List;

public class ShopPageFragment extends Fragment {

    private Button find_all_user,find_all_article,find_user_article,add,test;
    private LoginUser loginUser = LoginUser.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_shop_page, container, false);
        find_all_user = view.findViewById(R.id.find_all_user);
        find_all_article = view.findViewById(R.id.find_all_article);
        find_user_article = view.findViewById(R.id.find_user_article);
        add = view.findViewById(R.id.add);
        test = view.findViewById(R.id.test);

        initView();

        return view;
    }

    private void initView(){
        find_all_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<User> userList = LitePal.findAll(User.class);
                for (User user : userList){
                    Log.d("food", user.toString());
                }
            }
        });
        find_all_article.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Article> articleList = LitePal.findAll(Article.class);
                for (Article article : articleList){
                    Log.d("food", article.toString());
                }
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = LitePal.where("id=?",String.valueOf(loginUser.getId())).findFirst(User.class);
                Article article = new Article("Title", "text",1,user);
                article.save();
                user.getArticleList().add(article);
                user.update(user.getId());
                Log.d("food","保存成功");
            }
        });
        find_user_article.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Article> articleList = loginUser.getArticleListFromLitePal();
                for (Article article : articleList){
                    Log.d("food",article.toString());
                }
            }
        });
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = LitePal.find(User.class, 1);
                Log.d("food",user.toString());
                List<Article> articleList = user.getArticleListFromLitePal();
                for (Article article : articleList){
                    Log.d("food",article.toString());
                }
            }
        });
    }
}