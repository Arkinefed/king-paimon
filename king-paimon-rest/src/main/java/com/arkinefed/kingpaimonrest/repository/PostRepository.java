package com.arkinefed.kingpaimonrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arkinefed.kingpaimonrest.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
