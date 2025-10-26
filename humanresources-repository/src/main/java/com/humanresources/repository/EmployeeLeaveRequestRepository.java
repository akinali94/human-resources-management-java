package com.humanresources.repository;

import com.humanresources.model.entity.EmployeeLeaveRequest;
import com.humanresources.model.entity.User;
import com.humanresources.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeLeaveRequestRepository extends BaseRepository<EmployeeLeaveRequest, Long> {
    
    List<EmployeeLeaveRequest> findByRequesting(User user);
    
    List<EmployeeLeaveRequest> findByRequestingAndApproved(User user, Boolean approved);
    
    @Transactional
    @Modifying
    @Query("UPDATE EmployeeLeaveRequest e SET e.approved = true, e.leaveApprovalDate = CURRENT_DATE WHERE e.id = :id")
    void approveLeaveRequest(@Param("id") Long id);
    
    @Transactional
    @Modifying
    @Query("UPDATE EmployeeLeaveRequest e SET e.approved = false, e.leaveApprovalDate = CURRENT_DATE WHERE e.id = :id")
    void rejectLeaveRequest(@Param("id") Long id);
    
    List<EmployeeLeaveRequest> findByStartDateBetweenOrEndDateBetween(
            LocalDate startDate1, LocalDate endDate1,
            LocalDate startDate2, LocalDate endDate2);
}