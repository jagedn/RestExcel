import org.springframework.restdocs.payload.FieldDescriptor

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath

/**
 * Created by jorge on 1/06/16.
 */
class DocuFields {


    static FieldDescriptor[] sheetFields = [
            fieldWithPath('name').description('el id de la hoja a usar para siguientes peticiones'),
            fieldWithPath('rows').description('el numero de filas que contiene'),
            fieldWithPath('cols').description('el numero de columnas que contiene'),
            fieldWithPath('headers').description('la primera fila de cada sheet contiene los nombres de los campos'),
    ]

    static FieldDescriptor[] cellFields = [
            fieldWithPath('row').description('el id de la fila'),
            fieldWithPath('cell').description('el id de la columna'),
            fieldWithPath('name').description('el nombre del campo (tomado de la primera fila del excel)'),
            fieldWithPath('value').description('el valor del campo'),
    ]

}
