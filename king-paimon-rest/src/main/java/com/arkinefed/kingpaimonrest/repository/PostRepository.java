package com.arkinefed.kingpaimonrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arkinefed.kingpaimonrest.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
