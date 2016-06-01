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

@Integration
class SheetGetSpec extends BaseSpec{

    ParameterDescriptor[]parameters = [
            parameterWithName('sheet').description('el nombre de la hoja')
    ]

    void "get sheet"() {

        expect:
        givenRequest(
                documentBase("sheet",
                        responseFields(DocuFields.sheetFields),
                        pathParameters(parameters) ))
            .pathParam('sheet','Products')
            .expect()
                .statusCode(200)
            .when()
                .get("/xls/{sheet}")
    }
}
