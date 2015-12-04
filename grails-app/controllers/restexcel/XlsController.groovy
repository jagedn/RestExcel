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

        if(params.sheet){
            if( params.row ){
                respond xlsService.getRow(params.sheet, params.row as int)
            }else{
                respond xlsService.sheets.find{ it.name == params.sheet }
            }


        }else {
            respond sheets: xlsService.sheets
        }

    }

}
