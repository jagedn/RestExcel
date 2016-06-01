/**
 * Created by jorge on 26/05/16.
 */

import grails.test.mixin.integration.Integration
import org.springframework.restdocs.payload.FieldDescriptor

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields

@Integration
class SheetIndexSpec extends BaseSpec{

    void "test index"() {

        expect:
        givenRequest(
                documentBase("index",
                        responseFields(
                                fieldWithPath('[]').description('Un array de hojas')
                        ).andWithPrefix('[].',DocuFields.sheetFields)
                )
        ).expect()
            .statusCode(200)
        .when()
            .get("/xls")
    }
}
