/**
 * Created by jorge on 1/06/16.
 */

import com.jayway.restassured.RestAssured
import com.jayway.restassured.builder.RequestSpecBuilder
import com.jayway.restassured.specification.RequestSpecification
import grails.test.mixin.integration.Integration
import org.springframework.restdocs.ManualRestDocumentation
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.request.ParameterDescriptor
import spock.lang.Specification

import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.documentationConfiguration
import static org.springframework.restdocs.restassured.operation.preprocess.RestAssuredPreprocessors.modifyUris
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse

@Integration
class StepByStepSpec extends Specification{

    // Simple RestAssured test
    void "restassured simple test 1"(){
        expect:
            RestAssured.given().expect().statusCode(200).when().get('/xls')
    }

    // One step beyond
    void "restassured simple test 2"(){
        expect:
        RestAssured.given().when().get('/xls').then().statusCode(200)
    }

    // One step beyond + pathParams
    void "restassured simple test 3"(){
        expect:
        RestAssured.given().pathParam("sheet","Products").when().get('/xls/{sheet}').then().statusCode(200)
    }

    /////////////////////////////////
    // Preparando el Spec para poder documentarlo
    //
    protected final ManualRestDocumentation restDocumentation = new ManualRestDocumentation('build/generated-snippets')

    protected RequestSpecification documentationSpec

    void setup() {
        this.documentationSpec = new RequestSpecBuilder()
                .addFilter(documentationConfiguration(restDocumentation)).build()
        this.restDocumentation.beforeTest(getClass(), specificationContext.currentSpec.name)
    }

    void cleanup() {
        this.restDocumentation.afterTest()
    }

    // Estructura general de un RestAssured+Docu
    // 1.- add documentation spec
    void "documenting simple 1"(){
        expect:
            RestAssured.given(documentationSpec).
                    filter(
                            document('stepbystep1')
                    ).
                    pathParam("sheet","Products").
                    when().get('/xls/{sheet}').then().statusCode(200)
    }


    // Estructura general de un RestAssured+Docu
    // 1.- add documentation spec
    // 2.- add filters al documentation
    void "documenting simple 2"(){
        expect:
        RestAssured.given(documentationSpec).
                filter(
                        document('stepbystep2',
                                preprocessRequest(
                                        modifyUris().host("mihost").port(9999).scheme("schema")
                                ),
                                preprocessResponse(prettyPrint())
                        )
                ).
                pathParam("sheet","Products").
                when().get('/xls/{sheet}').then().statusCode(200)
    }

    // One step beyond RestAssured+Docu
    // 1.- add documentation spec
    // 2.- add filters al documentation
    // 3.- add more filters to documentation
    void "documenting simple 3"(){
        expect:
        RestAssured.given(documentationSpec).
                filter(
                        document('stepbystep3',
                                preprocessRequest(
                                        modifyUris().host("mihost").port(9999).scheme("schema")
                                ),
                                preprocessResponse(prettyPrint()),
                                pathParameters(
                                        parameterWithName('sheet').description('el nombre de la hoja')
                                )
                        )
                ).
                pathParam("sheet","Products").
                when().get('/xls/{sheet}').then().statusCode(200)
    }

    // One step beyond RestAssured+Docu
    // 1.- add documentation spec
    // 2.- add filters al documentation
    // 3.- add more filters to documentation
    // 4.- and more filters
    void "documenting simple 4"(){
        expect:
        RestAssured.given(documentationSpec).
                filter(
                        document('stepbystep4',
                                preprocessRequest(
                                        modifyUris().host("mihost").port(9999).scheme("schema")
                                ),
                                preprocessResponse(prettyPrint()),
                                pathParameters(
                                        parameterWithName('sheet').description('el nombre de la hoja')
                                ),
                                responseFields(
                                        fieldWithPath('name').description('el id de la hoja a usar para siguientes peticiones'),
                                        fieldWithPath('rows').description('el numero de filas que contiene'),
                                        fieldWithPath('cols').description('el numero de columnas que contiene'),
                                        fieldWithPath('headers').description('la primera fila de cada sheet contiene los nombres de los campos'),
                                )
                        )
                ).
                pathParam("sheet","Products").
                when().get('/xls/{sheet}').then().statusCode(200)
    }

}
