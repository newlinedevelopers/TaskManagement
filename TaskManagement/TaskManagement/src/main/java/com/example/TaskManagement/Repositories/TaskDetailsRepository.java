package com.example.TaskManagement.Repositories;

import com.example.TaskManagement.Entities.TaskDetails;
import com.example.TaskManagement.Entities.UserCredentials;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TaskDetailsRepository extends JpaRepository<TaskDetails, Long> {
    @Cacheable(value = "taskDetails", key = "#id", sync = true)
    @Query("select td from TaskDetails td where td.id = ?1")
    TaskDetails findTaskById(Long id);
    @Query("select td from TaskDetails td where td.userCredentials = ?1")
    List<TaskDetails> findTaskByUser(UserCredentials userCredentials);
    @Transactional
    @CachePut(value = "taskDetails", key = "#result.id")
    <S extends TaskDetails> S save(S entity);
    @Modifying
    @Query("DELETE FROM TaskDetails t WHERE t.id = :id")
    @CacheEvict(value = "taskDetails", key = "#id")
    void deleteById(@Param("id") Long id);
}
