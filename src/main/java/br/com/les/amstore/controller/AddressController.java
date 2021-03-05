package br.com.les.amstore.controller;

import br.com.les.amstore.domain.State;
import br.com.les.amstore.dto.AddressDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/address")
public class AddressController {

    @GetMapping("/state/{id}")
    public ResponseEntity getAddress(@PathVariable("id") State state) {

        List<AddressDTO> addressDTOList = new ArrayList<>();

        state.getCities().forEach(city -> addressDTOList.add(new AddressDTO(city.getId(), city.getDescription())));

        if(null != state.getId())
            return ResponseEntity.ok(addressDTOList);
        else
            return ResponseEntity.notFound().build();
    }
}
