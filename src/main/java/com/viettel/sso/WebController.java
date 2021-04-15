package com.viettel.sso;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class WebController {

    @Autowired
    private CustomerDAO customerDAO;

    @GetMapping()
    public String index() {
        return "external";
    }

    @GetMapping(path = "/customers")
    public String customers(Principal principal, Model model) {
        addCustomers();
        Iterable<Customer> customers = customerDAO.findAll();
        model.addAttribute("customers", customers);
        model.addAttribute("username", principal.getName());
        return "customers";
    }

    // add customers for demonstration
    public void addCustomers() {

        Customer customer1 = new Customer();
        customer1.setAddress("1111 phạm văn đồng");
        customer1.setName("jack");
        customer1.setServiceRendered("Important services");
        customerDAO.save(customer1);

        Customer customer2 = new Customer();
        customer2.setAddress("2222 trần duy hưng");
        customer2.setName("alice");
        customer2.setServiceRendered("Important services");
        customerDAO.save(customer2);

        Customer customer3 = new Customer();
        customer3.setAddress("333 hùng vương");
        customer3.setName("king");
        customer3.setServiceRendered("Important services");
        customerDAO.save(customer3);
    }
}
