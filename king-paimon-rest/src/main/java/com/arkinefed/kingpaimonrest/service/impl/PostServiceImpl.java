package com.arkinefed.kingpaimonrest.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkinefed.kingpaimonrest.data.request.AddPostRequest;
import com.arkinefed.kingpaimonrest.exception.PostNotFoundException;
import com.arkinefed.kingpaimonrest.model.AppUser;
import com.arkinefed.kingpaimonrest.model.Post;
import com.arkinefed.kingpaimonrest.repository.PostRepository;
import com.arkinefed.kingpaimonrest.service.PostService;

import jakarta.transaction.Transactional;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;

    @Override
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPost(Long id) throws PostNotFoundException {
        return postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("post " + String.valueOf(id) + " not found"));
    }

    @Override
    public Post addPost(AddPostRequest data, AppUser author) {
        Post post = Post.builder()
                .author(author)
                .title(data.getTitle())
                .content(data.getContent())
                .whenAdded(LocalDateTime.now())
                .build();

        return postRepository.save(post);
    }

    @Override
    @Transactional
    public void removePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new PostNotFoundException("post " + String.valueOf(id) + " not found");
        }

        postRepository.deleteById(id);
    }
}
