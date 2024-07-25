

package xyz.subho.clone.twitter.model;

import java.util.UUID;
import lombok.Data;

@Data
public class UserModel {

  private UUID id;
  private String username;
  private String name;
  private String avatar;
  private String bio;
  private Long followerCount;
  private Long followingCount;
  private Boolean verified;
}
