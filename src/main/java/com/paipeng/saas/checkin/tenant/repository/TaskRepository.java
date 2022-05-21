package com.paipeng.saas.checkin.tenant.repository;


import com.paipeng.saas.checkin.tenant.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
