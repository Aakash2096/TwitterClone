
package xyz.subho.clone.twitter.service;

import java.util.List;
import xyz.subho.clone.twitter.entity.Hashtags;
import xyz.subho.clone.twitter.model.HashtagModel;
import xyz.subho.clone.twitter.model.PostModel;

public interface HashtagService {

  public List<HashtagModel> getHashtags();

  public List<PostModel> getPosts(String tag);

  public List<Hashtags> getHashtagsByTags(List<String> hashtag);
}
