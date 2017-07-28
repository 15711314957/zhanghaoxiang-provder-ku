package com.bw.repository;

import com.bw.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhx on 2017/7/28.
 */

public interface UserRepository extends JpaRepository<User,Integer> {

    /*
    登陆
     */
    public User findUserByUsernameAndPassword(String  username,String password);

    @Query("select password from User u where u.username=:username")
    public String findOldPassword(@Param("username") String username);

    /*
    注册
     */
    public User save(User user);

    List<User> findAll();

    void delete(Integer id);
}
