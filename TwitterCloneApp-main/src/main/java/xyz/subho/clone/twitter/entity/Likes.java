/

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
@Table(name = "likes")
@Data
public class Likes {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(columnDefinition = "BINARY(16)")
  private UUID id;

  @ManyToOne(targetEntity = Posts.class)
  @JoinColumn(
      name = "posts_id",
      columnDefinition = "BINARY(16)",
      updatable = false,
      nullable = false)
  private Posts posts;

  @ManyToOne(targetEntity = Users.class)
  @JoinColumn(
      name = "users_id",
      columnDefinition = "BINARY(16)",
      updatable = false,
      nullable = false)
  private Users users;

  @CreationTimestamp private Date createdAt;

  @UpdateTimestamp private Date updatedAt;
}
