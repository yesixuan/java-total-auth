package cn.vic.init.repository;

import cn.vic.init.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by tomoya at 2018/8/10
 */
public interface TopicRepository extends JpaRepository<Topic, Integer> {
}
