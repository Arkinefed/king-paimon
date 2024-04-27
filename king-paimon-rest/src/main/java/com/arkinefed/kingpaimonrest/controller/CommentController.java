package com.arkinefed.kingpaimonrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arkinefed.kingpaimonrest.data.request.AddCommentRequest;
import com.arkinefed.kingpaimonrest.data.response.CommentData;
import com.arkinefed.kingpaimonrest.exception.CommentNotFoundException;
import com.arkinefed.kingpaimonrest.exception.PostNotFoundException;
import com.arkinefed.kingpaimonrest.misc.CommonVariables;
import com.arkinefed.kingpaimonrest.model.AppUser;
import com.arkinefed.kingpaimonrest.model.Comment;
import com.arkinefed.kingpaimonrest.service.CommentService;
import com.arkinefed.kingpaimonrest.utils.AppUserUtils;

@RestController
@RequestMapping(CommonVariables.API_V1)
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/public/post/{id}/comment/all")
    public ResponseEntity<?> getPostComments(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.getPostComments(id));
    }

    @PutMapping("/user/comment/add")
    public ResponseEntity<?> addComment(@RequestBody AddCommentRequest request) {
        if (request.getContent() == null || request.getPostId() == null) {
            return ResponseEntity.badRequest().body("not all required values were provided");
        }

        if (request.getContent().length() > 512) {
            return ResponseEntity.badRequest().body("too long content; max " + String.valueOf(512));
        }

        AppUser user = AppUserUtils.getAppUserPerformingRequest();

        try {
            Comment comment = commentService.addComment(request, user);

            return ResponseEntity.ok(CommentData.fromComment(comment));
        } catch (PostNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/user/comment/{id}/remove")
    public ResponseEntity<?> removeComment(@PathVariable Long id) {
        AppUser user = AppUserUtils.getAppUserPerformingRequest();

        try {
            Comment comment = commentService.getComment(id);

            if (!user.isAdmin()) {
                if (!comment.getAuthor().getUsername().equals(user.getUsername())) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("you are not the author of the comment");
                }
            }

            commentService.removeComment(id);

            return ResponseEntity.ok("comment " + String.valueOf(id) + " removed");
        } catch (CommentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
