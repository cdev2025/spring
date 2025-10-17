package com.example.mywebservice.controller;

import com.example.mywebservice.dto.PostDto;
import com.example.mywebservice.entity.User;
import com.example.mywebservice.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

// 웹 페이지 요청을 처리한느 MVC Controller
@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    // 게시글 목록 페이지 (검색 기능 포함)
    // GET /posts
    // http://localhost:8080/posts
    @GetMapping
    public String list(Model model, @RequestParam(required = false) String keyword,
                       @AuthenticationPrincipal User user)
    {
        log.info("GET /posts - keyowrd: {}", keyword);

        List<PostDto> posts;
        if( keyword != null && !keyword.trim().isEmpty() ){ // 키워드 있는 경우
            posts = postService.searchPostsByKeyword(keyword);
            model.addAttribute("keyword", keyword);
        } else{
            posts = postService.getAllPosts();
        }

        model.addAttribute("posts", posts);
        model.addAttribute("currentUser", user);
        return "list";
    }


    // 게시글 작성 폼
    @GetMapping("/create")
    public String createForm(Model model, @AuthenticationPrincipal User user){
        PostDto postDto = new PostDto();
        postDto.setAuthor(user.getName()); // 로그인된 사용자 이름 자동 설정
        model.addAttribute("post", postDto);
        return "create";
    }

    // 게시글 작성 처리
    @PostMapping("/create")
    public String createPost(@ModelAttribute("post") PostDto postDto, RedirectAttributes redirectAttributes){
        try{
            postService.createPost(postDto);
            redirectAttributes.addFlashAttribute("message", "게시글이 성공적으로 작성되었습니다.");
        }catch (Exception e){
            log.error("Error creating post", e);
            redirectAttributes.addFlashAttribute("error", "게시글 작성 중 오류가 발생했습니다.");
        }

        return "redirect:/posts";
    }

    // 게시글 상세 페이지
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id,
                         Model model,
                         @AuthenticationPrincipal User user){
        PostDto post = postService.getPostById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        model.addAttribute("post", post);
        model.addAttribute("currentUser", user);
        return "detail.html";
    }

    // 게시글 수정 폼
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id,
                           Model model,
                           @AuthenticationPrincipal User user){
        PostDto post = postService.getPostById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        // 권한 체크 : 본인 게시글만 수정 가능
        if( !post.getAuthor().equals(user.getName())){
            throw new RuntimeException("본인이 작성한 게시글만 수정할 수 있습니다.");
        }

        model.addAttribute("post", post);
        return "edit";
    }

    // 게시글 수정 처리
    @PostMapping("/{id}/edit")
    public String editPost(@PathVariable Long id,
                           @ModelAttribute("post") PostDto updatePostDto,
                           RedirectAttributes redirectAttributes){
        try {
            postService.updatePost(id, updatePostDto);
            redirectAttributes.addFlashAttribute("message", "게시글이 성공적으로 수정되었습니다.");
        }catch (Exception e){
            log.error("Error updating post", e);
            redirectAttributes.addFlashAttribute("error", "게시글 수정 중 오류가 발생했습니다.");
        }
        return "redirect:/posts/" + id;
    }

    // 게시글 삭제 처리
    @PostMapping("/{id}/delete")
    public String deletePost(@PathVariable Long id,
                             @AuthenticationPrincipal User user,
                             RedirectAttributes redirectAttributes){
        // 권한 체크
        PostDto post = postService.getPostById(id)
                .orElseThrow(()->new RuntimeException("게시글을 찾을 수 없습니다."));
        if(!post.getAuthor().equals(user.getName())){
            throw new RuntimeException("본인이 작성한 게시글만 삭제할 수 있습니다.");
        }

        try {
            boolean deleted = postService.deletePost(id);
            if(deleted){
                redirectAttributes.addFlashAttribute("message", "게시글이 성공적으로 삭제되었습니다.");
            }else {
                redirectAttributes.addFlashAttribute("message", "게시글을 찾을 수 없습니다.");
            }
        } catch (Exception e){
            log.error("Error deleting post", e);
            redirectAttributes.addFlashAttribute("error", "게시글 삭제 중 오류가 발생했습니다.");
        }
        return "redirect:/posts";
    }

}
