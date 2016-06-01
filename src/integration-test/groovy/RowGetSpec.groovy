/**
 * Created by jorge on 27/05/16.
 */
import grails.test.mixin.integration.Integration
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.request.ParameterDescriptor

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters

@Integration
class RowGetSpec extends BaseSpec{

    void "get first row"() {

        expect:
        givenRequest(
                documentBase("row",
                        pathParameters(
                            parameterWithName('sheet').description('el nombre de la hoja'),
                            parameterWithName('row').description('el numero de fila a consultar (la primera contiene la cabecera)')
                        ),
                        responseFields(
                            fieldWithPath('[]').description('Un array de celdas')
                        ).andWithPrefix('[].',DocuFields.cellFields),
                )
        )
        .pathParam('sheet','Products')
        .pathParam('row','1')
        .expect()
            .statusCode(200)
        .when()
            .get("/xls/{sheet}/{row}")
    }

    void "get n row"() {

        expect:
        givenRequest(
                documentBase("row_with_params",
                        pathParameters(
                                parameterWithName('sheet').description('el nombre de la hoja'),
                        ),
                        requestParameters(
                                parameterWithName('row').description('el numero de fila a consultar (la primera contiene la cabecera)'),
                                parameterWithName('max').description('numero maximo de filas posteriores)')
                        ),
                        responseFields(
                                fieldWithPath('[]').description('Un array de rows')
                        ).andWithPrefix('[].[].',DocuFields.cellFields)
                )
        )
                .pathParam('sheet','Products')
                .parameter('row',2)
                .parameter('max',5)
                .expect()
                    .statusCode(200)
                .when()
                .   get("/xls/{sheet}")
    }
}
