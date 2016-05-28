/**
 * Created by jorge on 28/05/16.
 */
import grails.test.mixin.integration.Integration
import org.springframework.restdocs.payload.FieldDescriptor

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields

@Integration
class ErrorSpec extends BaseSpec{

    FieldDescriptor[] fields = [
            fieldWithPath('error').description('el codigo del error'),
            fieldWithPath('message').description('una descripcion del error')
    ]

    void "get unknow sheet"() {

        expect:
        givenRequest(documentBase("error-example", responseFields(fields)))
                .when()
                .port(8080)
                .get("/xls/seguroquenoexiste")
                .then()
                .assertThat().statusCode(404)

    }
}
