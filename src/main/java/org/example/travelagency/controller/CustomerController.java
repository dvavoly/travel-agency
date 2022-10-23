package org.example.travelagency.controller;

import org.example.travelagency.model.Customer;
import org.example.travelagency.service.CustomerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("customers", customerService.getAll());
        return "customer/customers";
    }

    @PreAuthorize("hasRole('MANAGER') or authentication.principal.id == #id")
    @GetMapping("/{id}")
    public String getCustomerById(@PathVariable Integer id, Model model) {
        model.addAttribute("customer", customerService.readById(id));
        return "customer/customer";
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/create-customer";
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("customer") Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            return "customer/create-customer";
        }
        customerService.create(customer);
        return "redirect:/customers";
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/{id}/update")
    public String update(@PathVariable Integer id, Model model) {
        model.addAttribute("customer", customerService.readById(id));
        return "customer/update-customer";
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/{id}/update")
    public String update(@PathVariable Integer id, Model model,
                         @Validated @ModelAttribute("customer") Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            return "customer/update-customer";
        }
        customerService.update(customer);
        return "redirect:/customers";
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, Model model) {
        customerService.delete(id);
        model.addAttribute("customers", customerService.getAll());
        return "customer/customers";
    }
}
