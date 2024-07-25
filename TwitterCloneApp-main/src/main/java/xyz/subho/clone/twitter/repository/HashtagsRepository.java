
package xyz.subho.clone.twitter.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import xyz.subho.clone.twitter.entity.Hashtags;

public interface HashtagsRepository extends JpaRepository<Hashtags, UUID> {

  public Hashtags findByTag(String tag);

  public List<Hashtags> findByTagIn(List<String> tags);
}
