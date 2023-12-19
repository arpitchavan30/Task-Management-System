package com.example.Task.Management.Repository;

import com.example.Task.Management.Model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    Task findByTaskId(String TaskId);

    Page<Task> findAll(Pageable pageable);

    @Query(value = "SELECT MAX(CAST(SUBSTRING(task_id, 4) AS SIGNED)) FROM TASK", nativeQuery = true)
    Long findLatestTaskSequenceNumber();

    Page<Task> findAllByOrderByStatusAsc(Pageable pageable);
}
