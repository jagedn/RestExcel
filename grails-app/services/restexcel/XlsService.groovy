package restexcel

import groovy.transform.CompileStatic
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory


/**
 * Created by jorge on 3/12/15.
 */
@CompileStatic
class XlsService {


    public void init(){

    }

    private Workbook wb

    List<SheetBean> sheets = []

    public List<SheetBean> loadExcel( InputStream inputStream ){
        wb = WorkbookFactory.create(inputStream);
        wb.sheetIterator().each {sheet->
            Row firstRow = sheet.getRow(0)
            SheetBean add = new SheetBean(name: sheet.sheetName, rows:sheet.lastRowNum-1, cols : firstRow.lastCellNum, headers:firstRow.cellIterator()*.stringCellValue)
            sheets.add add
        }
        sheets
    }

    public List<CellBean>getRow( String sheetName, int rowNumber){
        if( rowNumber < 1 )
            return null
        Sheet sheet = wb.getSheet(sheetName)
        if( ! sheet )
            return null
        Row row = sheet.getRow(rowNumber)
        if( ! row )
            return null
        SheetBean bean = sheets.find{ it.name == sheetName }
        List<CellBean>ret = []
        (0..bean.cols).each{ idx->
            Cell cell = row.getCell(idx)
            if( cell ){
                CellBean add = new CellBean(row:rowNumber,cell:idx,value:cell2value(cell))
                ret.add add
            }
        }
        ret
    }

    String cell2value(Cell cell){
        switch (cell.cellType){
            case Cell.CELL_TYPE_BLANK:
                return ''
                break
            case Cell.CELL_TYPE_BOOLEAN:
                return "$cell.booleanCellValue".toString()
                break
            case Cell.CELL_TYPE_ERROR:
                return "$cell.errorCellValue".toString()
                break
            case Cell.CELL_TYPE_FORMULA:
                return "$cell.cellFormula".toString()
                break
            case Cell.CELL_TYPE_NUMERIC:
                return "$cell.numericCellValue".toString()
                break
            case Cell.CELL_TYPE_STRING:
                return "$cell.stringCellValue".toString()
                break
        }
    }

}
