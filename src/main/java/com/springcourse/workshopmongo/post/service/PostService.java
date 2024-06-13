package com.springcourse.workshopmongo.post.service;

import com.springcourse.workshopmongo.exceptions.NotFoundException;
import com.springcourse.workshopmongo.post.model.Post;
import com.springcourse.workshopmongo.post.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post findById(String id) {
        Optional<Post> post = postRepository.findById(id);

        if (post.isPresent()){
            return post.get();
        }

        throw new NotFoundException("Post não encontrado!");
    }

    public List<Post> findByTitle(String text) {
        return postRepository.searchTitle(text);
    }

    public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
        return postRepository.fullSearch(text, minDate, maxDate);
    }
}
