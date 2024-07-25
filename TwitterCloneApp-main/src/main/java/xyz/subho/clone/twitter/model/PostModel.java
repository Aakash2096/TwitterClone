

package xyz.subho.clone.twitter.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class PostModel {

  private UUID id;
  private String text;
  private UUID userId;
  private List<String> images = new ArrayList<>(4);
  private Long likeCount;
  private Long repostCount;
  private UUID originalPostId;
  private UUID replyToId;
  private Date timestamp;
  private List<String> hashtags = new ArrayList<>();
  private List<String> mentions = new ArrayList<>();
}
