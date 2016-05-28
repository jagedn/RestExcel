/**
 * Created by jorge on 27/05/16.
 */
import grails.test.mixin.integration.Integration
import org.springframework.restdocs.payload.FieldDescriptor

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields

@Integration
class RowGetSpec extends BaseSpec{

    FieldDescriptor[] fields = [
            fieldWithPath('[].row').description('el id de la fila'),
            fieldWithPath('[].cell').description('el id de la columna'),
            fieldWithPath('[].name').description('el nombre del campo (tomado de la primera fila del excel)'),
            fieldWithPath('[].value').description('el valor del campo'),
    ]

    void "get first row"() {

        expect:
        givenRequest(documentBase("row/product", responseFields(fields)))
                .get("/xls/Products/1")
                .then()
                .assertThat().statusCode(200)

    }
}
