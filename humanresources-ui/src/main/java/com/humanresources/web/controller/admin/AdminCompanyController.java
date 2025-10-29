package com.humanresources.web.controller.admin;

import com.humanresources.model.entity.Company;
import com.humanresources.service.CompanyService;
import com.humanresources.web.dto.CompanyDTO;
import jakarta.validation.Valid;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/companies")
@Secured("ROLE_ADMIN")
public class AdminCompanyController {

    private final CompanyService companyService;
    private final ModelMapper modelMapper;

    public AdminCompanyController(CompanyService companyService, ModelMapper modelMapper) {
        this.companyService = companyService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public String listCompanies(Model model) {
        List<Company> companies = companyService.findAll();
        List<CompanyDTO> companyDTOs = companies.stream()
                .map(company -> modelMapper.map(company, CompanyDTO.class))
                .collect(Collectors.toList());
        model.addAttribute("companies", companyDTOs);
        return "admin/company/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("companyDTO", new CompanyDTO());
        return "admin/company/create";
    }

    @PostMapping("/create")
    public String createCompany(@Valid @ModelAttribute("companyDTO") CompanyDTO companyDTO,
                               BindingResult result,
                               @RequestParam("logoFile") MultipartFile logoFile,
                               RedirectAttributes redirectAttributes) {
        
        if (result.hasErrors()) {
            return "admin/company/create";
        }
        
        try {
            Company company = modelMapper.map(companyDTO, Company.class);
            
            // Handle file upload if provided
            if (logoFile != null && !logoFile.isEmpty()) {
                String fileName = processLogoUpload(logoFile);
                company.setLogo(fileName);
            }
            
            companyService.save(company);
            redirectAttributes.addFlashAttribute("successMessage", "Şirket başarıyla oluşturuldu.");
            return "redirect:/admin/companies";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Şirket oluşturulurken bir hata oluştu: " + e.getMessage());
            return "admin/company/create";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Company> companyOpt = companyService.findById(id);
        
        if (companyOpt.isPresent()) {
            CompanyDTO companyDTO = modelMapper.map(companyOpt.get(), CompanyDTO.class);
            model.addAttribute("companyDTO", companyDTO);
            return "admin/company/edit";
        } else {
            return "redirect:/admin/companies";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateCompany(@PathVariable Long id,
                               @Valid @ModelAttribute("companyDTO") CompanyDTO companyDTO,
                               BindingResult result,
                               @RequestParam("logoFile") MultipartFile logoFile,
                               RedirectAttributes redirectAttributes) {
        
        if (result.hasErrors()) {
            return "admin/company/edit";
        }
        
        try {
            Optional<Company> existingCompanyOpt = companyService.findById(id);
            
            if (existingCompanyOpt.isPresent()) {
                Company existingCompany = existingCompanyOpt.get();
                modelMapper.map(companyDTO, existingCompany);
                
                // Handle file upload if provided
                if (logoFile != null && !logoFile.isEmpty()) {
                    String fileName = processLogoUpload(logoFile);
                    existingCompany.setLogo(fileName);
                }
                
                companyService.save(existingCompany);
                redirectAttributes.addFlashAttribute("successMessage", "Şirket başarıyla güncellendi.");
            }
            
            return "redirect:/admin/companies";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Şirket güncellenirken bir hata oluştu: " + e.getMessage());
            return "admin/company/edit";
        }
    }

    @GetMapping("/details/{id}")
    public String showCompanyDetails(@PathVariable Long id, Model model) {
        Optional<Company> companyOpt = companyService.findById(id);
        
        if (companyOpt.isPresent()) {
            CompanyDTO companyDTO = modelMapper.map(companyOpt.get(), CompanyDTO.class);
            model.addAttribute("company", companyDTO);
            
            // Şirkete ait çalışanları da getir
            List<User> employees = companyService.findEmployeesByCompanyId(id);
            model.addAttribute("employees", employees);
            
            return "admin/company/details";
        } else {
            return "redirect:/admin/companies";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteCompany(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            companyService.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Şirket başarıyla silindi.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Şirket silinirken bir hata oluştu: " + e.getMessage());
        }
        
        return "redirect:/admin/companies";
    }

    private String processLogoUpload(MultipartFile file) throws IOException {
        // Dosya adını unique yap
        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
        
        // Uploads dizinini kontrol et/oluştur
        Path uploadPath = Paths.get("src/main/resources/static/uploads");
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        
        // Dosyayı kaydet
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);
        
        return fileName;
    }
}