

package xyz.subho.clone.twitter.service;

import java.util.List;
import java.util.UUID;
import xyz.subho.clone.twitter.entity.Users;
import xyz.subho.clone.twitter.model.UserModel;

public interface UserService {

  public UserModel getUserByUserName(String username);

  public UserModel getUserByUserId(UUID userId);

  public Users getUserEntityByUserId(UUID userId);

  public UserModel addUser(UserModel user);

  public UserModel editUser(UserModel user);

  public boolean addFollower(UUID followerId, UUID userId);

  public boolean removeFollower(UUID followerId, UUID userId);

  public List<UserModel> getFollowers(UUID userId);

  public List<UserModel> getFollowings(UUID userId);
}
