package restexcel

import grails.converters.JSON
import grails.transaction.Transactional

/**
 * Created by jorge on 3/12/15.
 */
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

@Transactional(readOnly = true)
class XlsController {

    static responseFormats = ['json', 'xml']

    XlsService xlsService

    def index() {
        if(!params.sheet) {
            respond xlsService.sheets
            return
        }

        if( !params.row ) {
            respond xlsService.sheets.find{ it.name == params.sheet }
            return
        }

        if( !params.col ) {
            if( params.max ) {
                def ret = xlsService.getRow(params.sheet, params.row as int, params.max as int)
                println ret as JSON
                respond ret
            }else
                respond xlsService.getRow(params.sheet, params.row as int)
            return
        }

        respond xlsService.getCol(params.sheet, params.row as int, params.col as int)
    }

}
