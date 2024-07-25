/

package xyz.subho.clone.twitter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(
    name = "hashtags",
    indexes = {@Index(columnList = "tag")})
@Data
public class Hashtags {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(columnDefinition = "BINARY(16)")
  private UUID id;

  @Column(unique = true, nullable = false)
  private String tag;

  @Column(name = "recent_post_count", columnDefinition = "BIGINT(20) default '1'", nullable = false)
  private Long recentPostCount = 1L;

  @OneToMany(mappedBy = "hashtags", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonIgnore
  private List<HashtagPosts> hashtagPosts = new ArrayList<>();

  @CreationTimestamp private Date createdAt;

  @UpdateTimestamp private Date updatedAt;
}
