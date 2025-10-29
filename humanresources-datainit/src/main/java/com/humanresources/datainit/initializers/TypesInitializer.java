package com.humanresources.datainit.initializers;

import com.humanresources.model.entity.AllowanceType;
import com.humanresources.model.entity.ExpenditureType;
import com.humanresources.model.entity.LeaveType;
import com.humanresources.model.enums.Gender;
import com.humanresources.repository.AllowanceTypeRepository;
import com.humanresources.repository.ExpenditureTypeRepository;
import com.humanresources.repository.LeaveTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class TypesInitializer {

    private final ExpenditureTypeRepository expenditureTypeRepository;
    private final LeaveTypeRepository leaveTypeRepository;
    private final AllowanceTypeRepository allowanceTypeRepository;
    
    public void initialize() {
        initializeExpenditureTypes();
        initializeLeaveTypes();
        initializeAllowanceTypes();
    }
    
    private void initializeExpenditureTypes() {
        if (expenditureTypeRepository.count() == 0) {
            System.out.println("Initializing expenditure types...");
            
            ExpenditureType mealType = new ExpenditureType();
            mealType.setName("Meal");
            mealType.setMinPrice(BigDecimal.ZERO);
            mealType.setMaxPrice(new BigDecimal("250"));
            expenditureTypeRepository.save(mealType);
            
            ExpenditureType transportType = new ExpenditureType();
            transportType.setName("Transport");
            transportType.setMinPrice(BigDecimal.ZERO);
            transportType.setMaxPrice(new BigDecimal("500"));
            expenditureTypeRepository.save(transportType);
            
            ExpenditureType hotelType = new ExpenditureType();
            hotelType.setName("Hotel");
            hotelType.setMinPrice(BigDecimal.ZERO);
            hotelType.setMaxPrice(new BigDecimal("5000"));
            expenditureTypeRepository.save(hotelType);
            
            System.out.println("Expenditure types initialization completed.");
        } else {
            System.out.println("Expenditure types already exist. Skipping initialization.");
        }
    }
    
    private void initializeLeaveTypes() {
        if (leaveTypeRepository.count() == 0) {
            System.out.println("Initializing leave types...");
            
            LeaveType annualLeaveMan = new LeaveType();
            annualLeaveMan.setName("Annual Leave");
            annualLeaveMan.setDefaultDays(14);
            annualLeaveMan.setGender(Gender.MALE);
            leaveTypeRepository.save(annualLeaveMan);
            
            LeaveType annualLeaveWoman = new LeaveType();
            annualLeaveWoman.setName("Annual Leave");
            annualLeaveWoman.setDefaultDays(14);
            annualLeaveWoman.setGender(Gender.FEMALE);
            leaveTypeRepository.save(annualLeaveWoman);
            
            LeaveType excuseLeave = new LeaveType();
            excuseLeave.setName("Excuse Leave");
            excuseLeave.setDefaultDays(3);
            excuseLeave.setGender(Gender.MALE);
            leaveTypeRepository.save(excuseLeave);
            
            System.out.println("Leave types initialization completed.");
        } else {
            System.out.println("Leave types already exist. Skipping initialization.");
        }
    }
    
    private void initializeAllowanceTypes() {
        if (allowanceTypeRepository.count() == 0) {
            System.out.println("Initializing allowance types...");
            
            AllowanceType equipmentType = new AllowanceType();
            equipmentType.setName("Equipment");
            equipmentType.setMinPrice(BigDecimal.ZERO);
            equipmentType.setMaxPrice(new BigDecimal("10000"));
            allowanceTypeRepository.save(equipmentType);
            
            AllowanceType trainingType = new AllowanceType();
            trainingType.setName("Training");
            trainingType.setMinPrice(BigDecimal.ZERO);
            trainingType.setMaxPrice(new BigDecimal("5000"));
            allowanceTypeRepository.save(trainingType);
            
            System.out.println("Allowance types initialization completed.");
        } else {
            System.out.println("Allowance types already exist. Skipping initialization.");
        }
    }
}