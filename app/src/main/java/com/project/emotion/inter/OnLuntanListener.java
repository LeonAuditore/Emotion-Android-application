package com.project.emotion.inter;


import com.project.emotion.entity.Luntan;

/**
 * @author 袁茏天
 * @description:
 * @date :2022/2/23 23:10
 */
public interface OnLuntanListener {
    void onCommentClick(Luntan luntan);//评论
    void onPraiseClick(Luntan luntan);//赞
}
