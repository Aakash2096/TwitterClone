
package xyz.subho.clone.twitter.entity;

import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "hashtag_posts")
@Data
public class HashtagPosts {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(columnDefinition = "BINARY(16)")
  private UUID id;

  @ManyToOne(targetEntity = Hashtags.class)
  @JoinColumn(
      name = "hashtags_id",
      columnDefinition = "BINARY(16)",
      updatable = false,
      nullable = false)
  private Hashtags hashtags;

  @ManyToOne(targetEntity = Posts.class)
  @JoinColumn(
      name = "posts_id",
      columnDefinition = "BINARY(16)",
      updatable = false,
      nullable = false)
  private Posts posts;

  @CreationTimestamp private Date createdAt;

  @UpdateTimestamp private Date updatedAt;
}
