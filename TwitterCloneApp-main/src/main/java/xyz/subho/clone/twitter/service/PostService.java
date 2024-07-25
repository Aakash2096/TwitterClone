
package xyz.subho.clone.twitter.service;

import java.util.List;
import java.util.UUID;
import xyz.subho.clone.twitter.model.PostModel;

public interface PostService {

  public List<PostModel> getAllPosts();

  public PostModel getPost(UUID postId);

  public PostModel addPost(PostModel postModel);

  public boolean deletePost(UUID postId, UUID userId);

  public long addLike(UUID postId, UUID userId);

  public long removeLike(UUID postId, UUID userId);
}
