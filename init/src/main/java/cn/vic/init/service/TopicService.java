package cn.vic.init.service;

import cn.vic.init.model.Topic;
import cn.vic.init.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by tomoya at 2018/8/10
 */
@Service
@Transactional
public class TopicService {

  @Autowired
  private TopicRepository topicRepository;

  public Topic save(Topic topic) {
    return topicRepository.save(topic);
  }

  public Page<Topic> findAll(Integer pageNo, Integer pageSize) {
    Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
    return topicRepository.findAll(pageable);
  }
}
