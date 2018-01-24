package br.com.dbccompany.dojo.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author mfachinelli
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendedorDTO {
    private String nome;

    @JsonProperty("vendedor_url")
    private String vendedorUrl;

}
