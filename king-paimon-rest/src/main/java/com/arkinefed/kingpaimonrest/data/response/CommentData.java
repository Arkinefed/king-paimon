package com.arkinefed.kingpaimonrest.data.response;

import java.time.LocalDateTime;

import com.arkinefed.kingpaimonrest.model.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentData {
    private Long id;
    private String author;
    private String content;
    private LocalDateTime whenAdded;

    public static CommentData fromComment(Comment comment) {
        CommentData data = CommentData.builder()
                .id(comment.getId())
                .author(comment.getAuthor().getUsername())
                .content(comment.getContent())
                .whenAdded(comment.getWhenAdded())
                .build();

        return data;
    }
}
