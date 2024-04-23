package com.arkinefed.kingpaimonrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arkinefed.kingpaimonrest.data.request.AddPostRequest;
import com.arkinefed.kingpaimonrest.exception.PostNotFoundException;
import com.arkinefed.kingpaimonrest.misc.CommonVariables;
import com.arkinefed.kingpaimonrest.model.AppUser;
import com.arkinefed.kingpaimonrest.model.Post;
import com.arkinefed.kingpaimonrest.service.PostService;

@RestController
@RequestMapping(CommonVariables.API_V1)
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping("/public/posts")
    public ResponseEntity<?> getPosts() {
        return ResponseEntity.ok(postService.getPosts());
    }

    @GetMapping("/public/post/{id}")
    public ResponseEntity<?> getPost(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(postService.getPost(id));
        } catch (PostNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/admin/add-post")
    public ResponseEntity<?> addPost(@RequestBody AddPostRequest request) {
        if (request.getTitle() == null ||
                request.getContent() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not all required values were provided");
        }

        if (request.getTitle().length() > 128) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("too long title; max " + String.valueOf(128));
        }

        if (request.getContent().length() > 8192) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("too long content; max " + String.valueOf(8192));
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser user = (AppUser) authentication.getPrincipal();

        Post post = postService.addPost(request, user);

        return ResponseEntity.ok(post.getId());
    }

    @DeleteMapping("/admin/remove-post/{id}")
    public ResponseEntity<?> removePost(@PathVariable Long id) {
        try {
            postService.removePost(id);

            return ResponseEntity.ok("post " + String.valueOf(id) + " removed");
        } catch (PostNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
