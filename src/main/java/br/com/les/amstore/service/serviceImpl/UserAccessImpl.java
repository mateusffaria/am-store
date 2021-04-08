package br.com.les.amstore.service.serviceImpl;

import br.com.les.amstore.domain.Person;
import br.com.les.amstore.model.UserAccess;
import br.com.les.amstore.service.IPersonService;
import br.com.les.amstore.service.IUserAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAccessImpl implements IUserAccessService {
    @Autowired
    IPersonService personService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Person> person = personService.findByEmail(email);

        person.orElseThrow(() -> new UsernameNotFoundException("Not found email: " + email));

        return person.map(UserAccess::new).get();
    }
}
