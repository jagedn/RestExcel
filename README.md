# RestExcel
put your microsoft excel behind a REST service and access the content via http

- no quieres/puedes usar una base de datos en tu aplicación.
- tu cliente sólo quiere/sabe manejar excel (incluso lo llama su base de datos!!)
- ...
- un blog en Excel 
- la hoja de cálculo aquella donde tenías organizados tus discos, libros

Con RestExcel podrás ofrecer las hojas Excel a través de un servicio REST (json y/o xml):

consulta las hojas que existen en el excel
http://{tu_servidor}/xls/
    {"sheets":[{"cols":3,"headers":["id","name","surname"],"name":"Hoja1","rows":1}]}
    
consulta el detalle de la hoja "Hoja1"
http://{tu_servidor}/xls/Hoja1
    {"cols":3,"headers":["id","name","surname"],"name":"Hoja1","rows":1}

recupera los datos de la fila 2 de "Hoja1"
http://{tu_servidor}/xls/Hoja1/2
    [{"cell":0,"row":2,"value":"2.0"},{"cell":1,"row":2,"value":"pepito"},{"cell":2,"row":2,"value":"meganito"}]




 
