package br.com.les.amstore.service.serviceImpl;

import br.com.les.amstore.domain.Customer;
import br.com.les.amstore.repository.Customers;
import br.com.les.amstore.service.ICustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomersService {
    @Autowired
    private Customers customers;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Customer> findAll() {
        return customers.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return customers.findById(id).get();
    }

    @Override
    public Customer saveAndFlush(Customer customer) {
        if(null != customer.getPassword() && customer.getPassword().trim().length() >= 6) {
            customer.setEncryptedPassword(passwordEncoder.encode(customer.getPassword()));
        }
        return customers.saveAndFlush(customer);
    }

    @Override
    public Customer findByEmail(String email) {
        return customers.findByEmail(email).get();
    }
    @Override
    public Customer currentUserLoggedIn() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
            Customer customer = this.findByEmail(username);
            return customer;
        }

        return null;
    }
    @Override
    public void isCurrentUserLoggedIn(Long id, ModelAndView mv) {
        Customer customer = this.currentUserLoggedIn();

        if(!customer.getId().equals(id))
            mv.setViewName("redirect:/");
    }

    @Override
    public boolean isCurrentUserLoggedIn(Long id) {
        return this.currentUserLoggedIn().getId().equals(id);
    }
}
