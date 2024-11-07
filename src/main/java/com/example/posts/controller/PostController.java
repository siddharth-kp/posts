package com.example.posts.controller;

import com.example.posts.Entity.Post;
import com.example.posts.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> getPosts() {
        List<Post> posts = postService.getPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Post> addPost(@RequestBody Post post) {
        Post createdPost = postService.addPost(post);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable int id) {
        boolean b = postService.deletePost(id);
        if(b==false) {
            return new ResponseEntity<>("id not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Post deleted successfully!", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Post> updatePost(@RequestBody Post post) {
        try {
            Post updatedPost = postService.updatePost(post);
            return new ResponseEntity<>(updatedPost, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable int id) {
        Optional<Post> post = postService.getPostById(id);

        if (post.isPresent()) {
            return new ResponseEntity<>(post.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
