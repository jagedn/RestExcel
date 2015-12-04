import grails.core.GrailsApplication
import restexcel.XlsService

class BootStrap {

    XlsService xlsService

    GrailsApplication grailsApplication

    def init = { servletContext ->

        xlsService.loadExcel( new FileInputStream( grailsApplication.config.xls?.path ))

    }
    def destroy = {
    }
}
