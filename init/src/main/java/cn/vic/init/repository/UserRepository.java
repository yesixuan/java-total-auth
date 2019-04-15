package cn.vic.init.repository;


import cn.vic.init.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by tomoya at 2018/8/10
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
