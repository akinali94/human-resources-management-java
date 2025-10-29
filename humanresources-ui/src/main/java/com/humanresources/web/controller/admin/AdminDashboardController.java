package com.humanresources.web.controller.admin;

import com.humanresources.model.entity.User;
import com.humanresources.model.enums.Role;
import com.humanresources.service.CompanyService;
import com.humanresources.service.UserService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@Secured("ROLE_ADMIN")
public class AdminDashboardController {

    private final UserService userService;
    private final CompanyService companyService;

    public AdminDashboardController(UserService userService, CompanyService companyService) {
        this.userService = userService;
        this.companyService = companyService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Dashboard istatistiklerini hesapla
        long totalEmployees = userService.findByRole(Role.ROLE_EMPLOYEE).size();
        long totalManagers = userService.findByRole(Role.ROLE_MANAGER).size();
        long totalCompanies = companyService.count();
        long activeCompanies = companyService.findActiveCompanies().size();

        // Model'e ekle
        model.addAttribute("totalEmployees", totalEmployees);
        model.addAttribute("totalManagers", totalManagers);
        model.addAttribute("totalCompanies", totalCompanies);
        model.addAttribute("activeCompanies", activeCompanies);

        return "admin/dashboard";
    }
}