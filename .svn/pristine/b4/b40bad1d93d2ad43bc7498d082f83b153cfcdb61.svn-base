package com.example.yangbang.miowner.fragment;

import android.os.Message;
import android.widget.ExpandableListView;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.adapter.CommentAdapter;
import com.example.yangbang.miowner.entity.Comment;
import com.example.yangbang.miowner.util.HttpConstant;
import com.example.yangbang.miowner.util.MyApplication;
import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseFragment;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.utils.ToastUtil;
import com.hbw.library.utils.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * 评价Fragment
 *
 * @FileName: com.example.yangbang.miowner.fragment.FragmentComment.java
 * @author: Yangbang
 * @date: 2016-06-27 16:34
 */
public class FragmentComment extends BaseFragment {
    public static final int COMMENT_DEL = 15;
    ExpandableListView el_fragment_comment;
    List<Comment> commentList = new ArrayList<>();
    CommentAdapter commentAdapter;

    @Override
    protected void initWidget() {
        el_fragment_comment = (ExpandableListView) getView().findViewById(R.id.el_fragment_comment);
//        el_fragment_comment.setGroupIndicator(null);
        getCommentList();
    }

    public void getCommentList() {
        params.clear();
        params.put("token", MyApplication.TOKEN);
        analyzeJson.requestData(HttpConstant.CommentList, params, REQUEST_SUCCESS);
    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.fragment_comment;
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                commentList = gson.fromJson(data.data, new TypeToken<List<Comment>>() {
                }.getType());
                if (commentAdapter == null) {
                    commentAdapter = new CommentAdapter(commentList, getActivity(), analyzeJson);
                    el_fragment_comment.setAdapter(commentAdapter);
                } else {
                    commentAdapter.notifyDataSetChanged(commentList);
                }
                break;
            case COMMENT_DEL:
                ToastUtil.show(getActivity(), "成功删除该条评论");
                getCommentList();
                break;
        }
        return super.handleMessage(msg);
    }
}
