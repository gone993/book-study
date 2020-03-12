package com.gonnim.book.springboot.web;

import com.gonnim.book.springboot.config.auth.LoginUser;
import com.gonnim.book.springboot.config.auth.dto.SessionUser;
import com.gonnim.book.springboot.domain.user.User;
import com.gonnim.book.springboot.service.posts.PostsService;
import com.gonnim.book.springboot.web.dto.PostsListResponseDto;
import com.gonnim.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        if(user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

    @GetMapping("/posts/content/{id}")
    public String postsContent(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-content";
    }

    @GetMapping("posts/search/{category}/{keyword}")
    public String searchPosts(@PathVariable String category, @PathVariable String keyword, Model model) {
       List<PostsListResponseDto> dtos = postsService.findBooks(category, keyword);
       model.addAttribute("posts", dtos);

        //        if(user != null) {
//            model.addAttribute("userName", user.getName());
//        }
        return "index";
    }
}
