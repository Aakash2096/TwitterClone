
package xyz.subho.clone.twitter.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.subho.clone.twitter.entity.HashtagPosts;
import xyz.subho.clone.twitter.entity.Likes;
import xyz.subho.clone.twitter.entity.Posts;
import xyz.subho.clone.twitter.entity.Users;
import xyz.subho.clone.twitter.exception.ErrorSavingEntityToDatabaseException;
import xyz.subho.clone.twitter.exception.ResourceNotFoundException;
import xyz.subho.clone.twitter.model.PostModel;
import xyz.subho.clone.twitter.model.UserModel;
import xyz.subho.clone.twitter.repository.HashtagPostsRepository;
import xyz.subho.clone.twitter.repository.LikesRepository;
import xyz.subho.clone.twitter.repository.PostsRepository;
import xyz.subho.clone.twitter.repository.UsersRepository;
import xyz.subho.clone.twitter.service.HashtagService;
import xyz.subho.clone.twitter.service.PostService;
import xyz.subho.clone.twitter.service.UserService;
import xyz.subho.clone.twitter.utility.Mapper;

@Service
@Slf4j
public class PostServiceImpl implements PostService {

  @Autowired private PostsRepository postsRepository;

  @Autowired private HashtagPostsRepository hashtagPostRepository;

  @Autowired private UserService userService;

  @Autowired private HashtagService hashtagService;

  @Autowired private LikesRepository likeRepository;

  @Autowired private UsersRepository usersRepository;

  @Autowired
  @Qualifier("PostMapper")
  private Mapper<Posts, PostModel> postMapper;

  @Autowired
  @Qualifier("UserMapper")
  private Mapper<Users, UserModel> userMapper;

  @Override
  public List<PostModel> getAllPosts() {
    var posts = postsRepository.findAll();
    List<PostModel> postModels = new ArrayList<>();
    Optional.ofNullable(posts)
        .ifPresent(
            post -> post.forEach(eachPost -> postModels.add(postMapper.transform(eachPost))));
    return postModels;
  }

  @Override
  public PostModel getPost(UUID postId) {

    var post = postsRepository.findById(postId);
    if (post.isPresent()) return postMapper.transform(post.get());
    throw new ResourceNotFoundException("Post ID is Invalid");
  }

  @Override
  @Transactional
  public PostModel addPost(PostModel postModel) {

    List<HashtagPosts> hashtagPosts = new ArrayList<>();
    var post = postMapper.transformBack(postModel);
    post.setUsers(usersRepository.getById(postModel.getUserId()));
    Optional.ofNullable(hashtagService.getHashtagsByTags(postModel.getHashtags()))
        .ifPresent(
            hashtags -> {
              hashtags.forEach(
                  hashtag -> {
                    HashtagPosts hashtagPost = new HashtagPosts();
                    hashtagPost.setHashtags(hashtag);
                    hashtagPost.setPosts(post);
                    hashtagPosts.add(hashtagPost);
                  });
            });
    post.setPostHashtags(hashtagPosts);
    hashtagPostRepository.saveAll(hashtagPosts);
    return postMapper.transform(postsRepository.save(post));
  }

  @Override
  @Transactional
  public boolean deletePost(UUID postId, UUID userId) {

    if (Optional.ofNullable((getPost(postId))).isPresent()) {
      postsRepository.deleteById(postId);
      return true;
    }
    return false;
  }

  @Override
  @Transactional
  public long addLike(UUID postId, UUID userId) {

    var post = postMapper.transformBack(getPost(postId));
    post.incrementLikeCount();
    var user = userMapper.transformBack(userService.getUserByUserId(userId));

    var likeMapping = new Likes();
    likeMapping.setPosts(post);
    likeMapping.setUsers(user);
    try {
      likeRepository.save(likeMapping);
      postsRepository.save(post);
      return post.getLikeCount();
    } catch (Exception excp) {
      log.error("Cannot Save LIKES Entity");
      throw new ErrorSavingEntityToDatabaseException("Cannot Save to Database");
    }
  }

  @Override
  @Transactional
  public long removeLike(UUID postId, UUID userId) {

    var post = postMapper.transformBack(getPost(postId));
    post.decrementLikeCount();
    var user = userMapper.transformBack(userService.getUserByUserId(userId));

    try {
      likeRepository.deleteByPostsAndUsers(post, user);
      postsRepository.save(post);
      return post.getLikeCount();
    } catch (Exception excp) {
      log.error("Cannot Save LIKES Entity");
      throw new ErrorSavingEntityToDatabaseException("Cannot Save to Database");
    }
  }
}
