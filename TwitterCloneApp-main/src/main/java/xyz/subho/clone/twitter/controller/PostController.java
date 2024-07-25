
package xyz.subho.clone.twitter.controller;

import java.security.Principal;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.subho.clone.twitter.model.PostModel;
import xyz.subho.clone.twitter.service.PostService;

@RestController
@RequestMapping("/posts")
@Slf4j
public class PostController {

  @Autowired private PostService postService;

  @GetMapping
  public ResponseEntity<List<PostModel>> getAllPosts() {
    List<PostModel> posts = postService.getAllPosts();
    return new ResponseEntity<>(posts, HttpStatus.OK);
  }

  @GetMapping("/{postId}")
  public ResponseEntity<PostModel> getPost(@PathVariable("postId") UUID postId) {
    PostModel post = postService.getPost(postId);
    return new ResponseEntity<>(post, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<PostModel> addPost(@RequestBody PostModel postModel, Principal principal) {
    UUID userId = UUID.randomUUID(); // TODO: Extract from Principal
    PostModel post = postService.addPost(postModel);
    return new ResponseEntity<>(post, HttpStatus.OK);
  }

  @DeleteMapping("/{postId}")
  public ResponseEntity<HttpStatus> deletePost(
      @PathVariable("postId") UUID postId, Principal principal) {

    UUID userId = UUID.randomUUID(); // TODO: Extract from Principal
    postService.deletePost(postId, userId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping("/{postId}/like")
  public ResponseEntity<Long> likePost(@PathVariable("postId") UUID postId, Principal principal) {

    UUID userId = UUID.randomUUID(); // TODO: Extract from Principal
    return new ResponseEntity<>(postService.addLike(postId, userId), HttpStatus.CREATED);
  }

  @DeleteMapping("/{postId}/like")
  public ResponseEntity<Long> removeLikePost(
      @PathVariable("postId") UUID postId, Principal principal) {

    UUID userId = UUID.randomUUID(); // TODO: Extract from Principal
    return new ResponseEntity<>(postService.removeLike(postId, userId), HttpStatus.OK);
  }
}
