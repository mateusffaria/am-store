package br.com.les.amstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataSetDTO {
    private String label;
    private List<Double> data ;
    private String borderColor;
    private String backgroundColor;
}
