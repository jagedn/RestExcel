import grails.test.mixin.integration.Integration
import org.springframework.restdocs.payload.FieldDescriptor

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters

/**
 * Created by jorge on 31/05/16.
 */
@Integration
class CellGetSpec extends BaseSpec{

    void "get cell 1/1"() {

        expect:
        givenRequest(
                documentBase(
                        "cels",
                        pathParameters(
                                parameterWithName('sheet').description('el nombre de la hoja'),
                                parameterWithName('row').description('el numero de fila a consultar (la primera contiene la cabecera)'),
                                parameterWithName('cell').description('el numero de celda a consultar ')
                        ),
                        responseFields(DocuFields.cellFields)
                )
        )
                .pathParam('sheet','Products')
                .pathParam('row','1')
                .pathParam('cell','1')
                .expect()
                    .statusCode(200)
                .when()
                    .get("/xls/{sheet}/{row}/{cell}")

    }
}

