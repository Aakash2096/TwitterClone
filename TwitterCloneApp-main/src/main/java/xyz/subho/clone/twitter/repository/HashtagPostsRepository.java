
package xyz.subho.clone.twitter.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import xyz.subho.clone.twitter.entity.HashtagPosts;
import xyz.subho.clone.twitter.entity.Hashtags;
import xyz.subho.clone.twitter.entity.Posts;

public interface HashtagPostsRepository extends JpaRepository<HashtagPosts, UUID> {

  public List<Posts> findByHashtags(Hashtags hashtag);

  public List<Hashtags> findByPosts(Posts post);
}
