package br.com.dbccompany.dojo.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Author mfachinelli
 */
public abstract class AbstractRestControllerTest {

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
