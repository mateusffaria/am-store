package br.com.les.amstore.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class CouponDTO {
    public String code;
    public Double value;
    public Long id;
}
