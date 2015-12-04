class UrlMappings {

    static mappings = {

        "/xls/$sheet/$row?(.$format)?"(controller:'xls'){
            constraints{
               // row(validator: it.isInteger())
            }
        }

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: 'index')
        "500"(controller: 'InternalServerError')
        "404"(controller: 'NotFound')
    }
}
