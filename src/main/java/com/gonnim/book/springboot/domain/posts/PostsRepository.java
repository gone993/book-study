package com.gonnim.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

    @Query("SELECT p From Posts p WHERE p.title like CONCAT('%',:title,'%')")
    List<Posts> findByTitle(@Param("title") String title);

    @Query("SELECT p From Posts p WHERE p.author like CONCAT('%',:author,'%')")
    List<Posts> findByAuthor(@Param("author") String author);

    @Query("SELECT p From Posts p WHERE p.content like CONCAT('%',:content,'%')")
    List<Posts> findByContent(@Param("content") String content);
}
