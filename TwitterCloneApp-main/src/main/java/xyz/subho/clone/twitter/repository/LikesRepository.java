
package xyz.subho.clone.twitter.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import xyz.subho.clone.twitter.entity.Likes;
import xyz.subho.clone.twitter.entity.Posts;
import xyz.subho.clone.twitter.entity.Users;

public interface LikesRepository extends JpaRepository<Likes, UUID> {

  void deleteByPostsAndUsers(Posts posts, Users users);
}
