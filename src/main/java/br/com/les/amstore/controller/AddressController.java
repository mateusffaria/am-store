package br.com.les.amstore.controller;

import br.com.les.amstore.domain.State;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/address")
public class AddressController {

    @GetMapping("/state/{id}")
    public ResponseEntity getAddress(@PathVariable("id") State state) {
        if(null != state.getId())
            return ResponseEntity.ok(state.getCities());
        else
            return ResponseEntity.notFound().build();
    }
}
