package br.com.dbccompany.dojo.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author mfachinelli
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

    @ApiModelProperty(value = "Este é o primeiro nome do usuário", required = true)
    @JsonProperty("primeiro_nome")
    private String primeiroNome;

    @JsonProperty("ultimo_nome")
    private String ultimoNome;

    @JsonProperty("cliente_url")
    private String clienteUrl;
}
