package com.arkinefed.kingpaimonrest.data.response;

import java.time.LocalDateTime;

import com.arkinefed.kingpaimonrest.model.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostData {
    private Long id;
    private String author;
    private String title;
    private String content;
    private LocalDateTime whenAdded;

    public static PostData fromPost(Post post) {
        PostData data = PostData.builder()
                .id(post.getId())
                .author(post.getAuthor().getUsername())
                .title(post.getTitle())
                .content(post.getContent())
                .whenAdded(post.getWhenAdded())
                .build();

        return data;
    }
}
