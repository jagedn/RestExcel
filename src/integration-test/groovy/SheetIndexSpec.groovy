/**
 * Created by jorge on 26/05/16.
 */

import grails.test.mixin.integration.Integration
import org.springframework.restdocs.payload.FieldDescriptor

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields

@Integration
class SheetIndexSpec extends BaseSpec{

    FieldDescriptor[] fields = [
            fieldWithPath('[].name').description('el id de la hoja a usar para siguientes peticiones'),
            fieldWithPath('[].rows').description('el numero de filas que contiene'),
            fieldWithPath('[].cols').description('el numero de columnas que contiene'),
            fieldWithPath('[].headers').description('la primera fila de cada sheet contiene los nombres de los campos'),
    ]

    void "test index"() {

        expect:
        givenRequest(documentBase("index",responseFields(fields)))
                .when()
                .port(8080)
                .get("/xls")
                .then()
                .assertThat().statusCode(200)

    }
}
