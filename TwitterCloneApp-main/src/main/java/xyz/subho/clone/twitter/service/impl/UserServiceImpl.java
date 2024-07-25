
package xyz.subho.clone.twitter.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.subho.clone.twitter.entity.Users;
import xyz.subho.clone.twitter.model.UserModel;
import xyz.subho.clone.twitter.repository.UsersRepository;
import xyz.subho.clone.twitter.service.UserService;
import xyz.subho.clone.twitter.utility.Mapper;

@Service
public class UserServiceImpl implements UserService {

  @Autowired private UsersRepository usersRepository;

  @Autowired
  @Qualifier("UserMapper")
  private Mapper<Users, UserModel> userMapper;

  @Override
  public UserModel getUserByUserName(String username) {
    return userMapper.transform(usersRepository.findByUsername(username));
  }

  @Override
  public UserModel getUserByUserId(UUID userId) {
    var user = usersRepository.getById(userId);
    return userMapper.transform(user);
  }

  @Override
  public Users getUserEntityByUserId(UUID userId) {
    return usersRepository.getById(userId);
  }

  @Override
  @Transactional
  public UserModel addUser(UserModel user) {
    Users users = userMapper.transformBack(user);
    return userMapper.transform(usersRepository.save(users));
  }

  @Override
  @Transactional
  public UserModel editUser(UserModel user) {
    Users users = userMapper.transformBack(user);
    return userMapper.transform(usersRepository.save(users));
  }

  @Override
  @Transactional
  public boolean addFollower(UUID followerId, UUID userId) {
    Users user = usersRepository.getById(userId);
    user.setFollower(followerId);
    usersRepository.save(user);
    return true;
  }

  @Override
  public boolean removeFollower(UUID followerId, UUID userId) {
    Users user = usersRepository.getById(userId);
    user.removeFollower(followerId);
    usersRepository.save(user);
    return true;
  }

  @Override
  public List<UserModel> getFollowers(UUID userId) {
    List<UserModel> followers = new ArrayList<>();
    Users user = usersRepository.getById(userId);
    List<Users> users = usersRepository.findAllById(user.getFollower().keySet());
    Optional.ofNullable(users)
        .ifPresent(
            usersList ->
                usersList.forEach(eachUser -> followers.add(userMapper.transform(eachUser))));
    return followers;
  }

  @Override
  public List<UserModel> getFollowings(UUID userId) {
    List<UserModel> followings = new ArrayList<>();
    Users user = usersRepository.getById(userId);
    List<Users> users = usersRepository.findAllById(user.getFollowing().keySet());
    Optional.ofNullable(users)
        .ifPresent(
            usersList ->
                usersList.forEach(eachUser -> followings.add(userMapper.transform(eachUser))));
    return followings;
  }
}
