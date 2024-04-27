package com.arkinefed.kingpaimonrest.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkinefed.kingpaimonrest.data.request.AddCommentRequest;
import com.arkinefed.kingpaimonrest.data.response.CommentData;
import com.arkinefed.kingpaimonrest.exception.CommentNotFoundException;
import com.arkinefed.kingpaimonrest.model.AppUser;
import com.arkinefed.kingpaimonrest.model.Comment;
import com.arkinefed.kingpaimonrest.model.Post;
import com.arkinefed.kingpaimonrest.repository.CommentRepository;
import com.arkinefed.kingpaimonrest.repository.PostRepository;
import com.arkinefed.kingpaimonrest.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;

    @Override
    public Comment getComment(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException("comment " + String.valueOf(id) + " not found"));
    }

    @Override
    public List<CommentData> getPostComments(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);

        List<CommentData> data = new ArrayList<>();

        for (Comment comment : comments) {
            data.add(CommentData.fromComment(comment));
        }

        return data;
    }

    @Override
    public Comment addComment(AddCommentRequest data, AppUser author) {
        Post post = postRepository.findById(data.getPostId())
                .orElseThrow(
                        () -> new CommentNotFoundException(
                                "comment " + String.valueOf(data.getPostId()) + " not found"));

        Comment comment = Comment.builder()
                .author(author)
                .content(data.getContent())
                .post(post)
                .whenAdded(LocalDateTime.now())
                .build();

        return commentRepository.save(comment);
    }

    @Override
    public void removeComment(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new CommentNotFoundException("comment " + String.valueOf(id) + " not found");
        }

        commentRepository.deleteById(id);
    }
}
