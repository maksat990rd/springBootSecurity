package peaksoft.springbootsecurity.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.springbootsecurity.model.Company;
import peaksoft.springbootsecurity.serviceImple.service.CompanyService;

import java.util.List;


@Controller
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @GetMapping()
    private String getAllCompanies(Model model) {
        List<Company> companies = companyService.getAllCompanies();
        model.addAttribute("companies", companies);
        return "company/companies";
    }




    @GetMapping("/addCompany")
    private String addCompany(Model model) {
        model.addAttribute( "company", new Company());
        return "company/addCompany";
    }
    @PostMapping("/saveCompany")
    private String saveCompany(@ModelAttribute("company") Company company) {
        companyService.saveCompany(company);
        return "redirect:/companies";
    }




    @GetMapping("/getCompany/{id}")
    private String getCompanyById(@PathVariable("id") int id,Model model) {
        model.addAttribute("company",companyService.getCompanyById(id));
        return "company/companies";
    }

    @GetMapping("/update/{id}")
    private String updateCompany(@PathVariable("id")int id, Model model) {
        model.addAttribute("company",companyService.getCompanyById(id));
        return "company/updateCompany";
    }
    @PostMapping("{id}/updateCompany")
    private String saveUpdateCompany(@PathVariable("id") int id,
                                     @ModelAttribute("company") Company company) {
        companyService.updateCompany(id,company);
        return "redirect:/companies";
    }




    @PostMapping("/{id}")
    private String deleteCompany(@PathVariable("id") int id) {
        companyService.deleteCompanyById(id);
        return "redirect:/companies";
    }
}