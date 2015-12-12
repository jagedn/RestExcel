package restexcel

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
            respond sheets: xlsService.sheets
            return
        }

        if( !params.row ) {
            respond xlsService.sheets.find{ it.name == params.sheet }
            return
        }

        if( !params.col ) {
            respond xlsService.getRow(params.sheet, params.row as int)
            return
        }

        respond xlsService.getCol(params.sheet, params.row as int, params.col as int)
    }

}
