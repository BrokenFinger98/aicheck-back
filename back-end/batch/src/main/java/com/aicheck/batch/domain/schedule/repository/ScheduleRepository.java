package com.aicheck.batch.domain.schedule.repository;

import com.aicheck.batch.domain.schedule.entity.Schedule;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByMemberIdAndDeletedAtIsNull(Long memberId);

    void deleteAllByChildId(Long childId);

    Optional<Schedule> findByChildIdAndDeletedAtIsNull(Long childId);
}
