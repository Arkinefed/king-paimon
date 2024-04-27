package com.arkinefed.kingpaimonrest.service;

import java.util.List;

import com.arkinefed.kingpaimonrest.data.request.AddCommentRequest;
import com.arkinefed.kingpaimonrest.data.response.CommentData;
import com.arkinefed.kingpaimonrest.model.AppUser;
import com.arkinefed.kingpaimonrest.model.Comment;

public interface CommentService {
    Comment getComment(Long id);

    List<CommentData> getPostComments(Long postId);

    Comment addComment(AddCommentRequest data, AppUser author);

    void removeComment(Long id);
}
