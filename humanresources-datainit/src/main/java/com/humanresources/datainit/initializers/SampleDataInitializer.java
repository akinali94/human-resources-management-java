package com.humanresources.datainit.initializers;

import com.humanresources.model.entity.*;
import com.humanresources.model.enums.ApprovalStatus;
import com.humanresources.model.enums.Currency;
import com.humanresources.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class SampleDataInitializer {

    private final UserRepository userRepository;
    private final ExpenditureTypeRepository expenditureTypeRepository;
    private final LeaveTypeRepository leaveTypeRepository;
    private final AllowanceTypeRepository allowanceTypeRepository;
    private final ExpenditureRequestRepository expenditureRequestRepository;
    private final LeaveRequestRepository leaveRequestRepository;
    private final IndividualAdvanceRepository individualAdvanceRepository;
    private final InstitutionalAllowanceRepository institutionalAllowanceRepository;
    
    public void initialize() {
        if (expenditureRequestRepository.count() == 0 && leaveRequestRepository.count() == 0 &&
            individualAdvanceRepository.count() == 0 && institutionalAllowanceRepository.count() == 0) {
            
            System.out.println("Initializing sample business data...");
            
            User manager = userRepository.findByUsername("manager.ayse@bugbusters.com")
                    .orElseThrow(() -> new RuntimeException("Manager user not found. Initialize users first."));
            User employee = userRepository.findByUsername("employee.ali@bugbusters.com")
                    .orElseThrow(() -> new RuntimeException("Employee user not found. Initialize users first."));
                    
            ExpenditureType mealType = expenditureTypeRepository.findByName("Meal")
                    .orElseThrow(() -> new RuntimeException("Meal expenditure type not found. Initialize types first."));
            ExpenditureType transportType = expenditureTypeRepository.findByName("Transport")
                    .orElseThrow(() -> new RuntimeException("Transport expenditure type not found. Initialize types first."));
            
            LeaveType annualLeaveWoman = leaveTypeRepository.findByNameAndGender("Annual Leave", Gender.FEMALE)
                    .orElseThrow(() -> new RuntimeException("Annual leave type for women not found. Initialize types first."));
            
            AllowanceType equipmentType = allowanceTypeRepository.findByName("Equipment")
                    .orElseThrow(() -> new RuntimeException("Equipment allowance type not found. Initialize types first."));
            
            // Expenditure Requests
            ExpenditureRequest teamLunch = new ExpenditureRequest();
            teamLunch.setTitle("Team lunch");
            teamLunch.setCurrency(Currency.TL);
            teamLunch.setAmount(new BigDecimal("180"));
            teamLunch.setExpenditureType(mealType);
            teamLunch.setUser(manager);
            teamLunch.setRequestDate(LocalDateTime.now().minusDays(7));
            teamLunch.setApprovalStatus(ApprovalStatus.APPROVED);
            teamLunch.setApprovalDate(LocalDateTime.now().minusDays(6));
            teamLunch.setImageUrl("https://picsum.photos/seed/receipt1/400");
            
            expenditureRequestRepository.save(teamLunch);
            
            ExpenditureRequest clientVisit = new ExpenditureRequest();
            clientVisit.setTitle("Client visit transport");
            clientVisit.setCurrency(Currency.TL);
            clientVisit.setAmount(new BigDecimal("90"));
            clientVisit.setExpenditureType(transportType);
            clientVisit.setUser(manager);
            clientVisit.setRequestDate(LocalDateTime.now().minusDays(3));
            clientVisit.setApprovalStatus(ApprovalStatus.PENDING);
            clientVisit.setImageUrl("https://picsum.photos/seed/receipt2/400");
            
            expenditureRequestRepository.save(clientVisit);
            
            // Leave Request
            LeaveRequest annualLeave = new LeaveRequest();
            annualLeave.setStartDate(LocalDate.now().plusDays(5));
            annualLeave.setEndDate(LocalDate.now().plusDays(9));
            annualLeave.setRequestDate(LocalDate.now());
            annualLeave.setApprovalStatus(ApprovalStatus.PENDING);
            annualLeave.setLeaveType(annualLeaveWoman);
            annualLeave.setUser(employee);
            
            leaveRequestRepository.save(annualLeave);
            
            // Individual Advance
            IndividualAdvance laptopAdvance = new IndividualAdvance();
            laptopAdvance.setCurrency(Currency.TL);
            laptopAdvance.setDescription("Laptop advance");
            laptopAdvance.setRequestDate(LocalDateTime.now().minusDays(10));
            laptopAdvance.setApprovalDate(LocalDateTime.now().minusDays(9));
            laptopAdvance.setApprovalStatus(ApprovalStatus.APPROVED);
            laptopAdvance.setAmount(new BigDecimal("5000"));
            laptopAdvance.setRemainingAmount(new BigDecimal("2000"));
            laptopAdvance.setUser(manager);
            
            individualAdvanceRepository.save(laptopAdvance);
            
            // Institutional Allowance
            InstitutionalAllowance monitorAllowance = new InstitutionalAllowance();
            monitorAllowance.setTitle("New monitor");
            monitorAllowance.setCurrency(Currency.TL);
            monitorAllowance.setAmount(new BigDecimal("2500"));
            monitorAllowance.setRequestDate(LocalDateTime.now().minusDays(12));
            monitorAllowance.setApprovalStatus(ApprovalStatus.PENDING);
            monitorAllowance.setAllowanceType(equipmentType);
            monitorAllowance.setUser(manager);
            monitorAllowance.setImageUrl("https://picsum.photos/seed/allowance1/400");
            
            institutionalAllowanceRepository.save(monitorAllowance);
            
            System.out.println("Sample business data initialization completed.");
        } else {
            System.out.println("Sample business data already exists. Skipping initialization.");
        }
    }
}