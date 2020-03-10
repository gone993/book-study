package com.gonnim.book.springboot.service.posts;

import com.gonnim.book.springboot.domain.posts.Posts;
import com.gonnim.book.springboot.domain.posts.PostsRepository;
import com.gonnim.book.springboot.web.dto.PostsListResponseDto;
import com.gonnim.book.springboot.web.dto.PostsResponseDto;
import com.gonnim.book.springboot.web.dto.PostsSaveRequestDto;
import com.gonnim.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    public List<PostsListResponseDto> findBooks (String category, String keyword) {
        List<Posts> entities = null;
        if("title".equals(category)) {
            entities = postsRepository.findByTitle(keyword);
        } else if("author".equals(category)) {
            entities = postsRepository.findByAuthor(keyword);
        } else {
            entities = postsRepository.findByContent(keyword);
        }
        //Posts entity = postsRepository.findBy
        return entities.stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }


    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        postsRepository.delete(posts);
    }
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
