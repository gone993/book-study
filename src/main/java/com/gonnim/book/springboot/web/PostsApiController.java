package com.gonnim.book.springboot.web;

import com.gonnim.book.springboot.service.posts.PostsService;
import com.gonnim.book.springboot.web.dto.PostsListResponseDto;
import com.gonnim.book.springboot.web.dto.PostsResponseDto;
import com.gonnim.book.springboot.web.dto.PostsSaveRequestDto;
import com.gonnim.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    //등록
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    //수정
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    //삭제
    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }

    //조회
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id) {
        return postsService.findById(id);
    }

    //검색
    @GetMapping("/api/v1/posts/{category}/{keyword}")
    public List<PostsListResponseDto> findBooks(@PathVariable String category, @PathVariable String keyword) {
        System.out.println("@@@@@@ category ::: " + category);
        System.out.println("@@@@@@ keyword ::: " + keyword);
        return postsService.findBooks(category, keyword);
    }

}
