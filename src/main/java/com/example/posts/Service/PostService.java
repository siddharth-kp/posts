package com.example.posts.Service;

import com.example.posts.Entity.Post;
import com.example.posts.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    public void deletePost(int id) {
        postRepository.deleteById(id);
    }

    public Post updatePost(Post postDetails) {
        int id = postDetails.getId();
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setName(postDetails.getName());
            post.setContent(postDetails.getContent());
            return postRepository.save(post);
        } else {
            throw new RuntimeException("Post not found with id " + id);
        }
    }

}