package com.arkinefed.kingpaimonrest.service;

import java.util.List;

import com.arkinefed.kingpaimonrest.data.request.AddPostRequest;
import com.arkinefed.kingpaimonrest.data.response.PostData;
import com.arkinefed.kingpaimonrest.model.AppUser;
import com.arkinefed.kingpaimonrest.model.Post;


public interface PostService {
    List<PostData> getPosts();

    PostData getPost(Long id);

    Post addPost(AddPostRequest data, AppUser author);

    void removePost(Long id);
}
