import * as FileSaver from 'file-saver';
import * as xlsx from 'xlsx'

export function exportExcel(data : any[], filename : string) {
    data = data.map(entry => changeListsToString(entry))
    const worksheet = xlsx.utils.json_to_sheet(data);
    const workbook = { Sheets: { data: worksheet }, SheetNames: ['data'] };
    const excelBuffer: any = xlsx.write(workbook, { bookType: 'xlsx', type: 'array' });
        saveAsExcelFile(excelBuffer, filename);
}

function saveAsExcelFile(buffer: any, fileName: string): void {
    let EXCEL_TYPE = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8';
    let EXCEL_EXTENSION = '.xlsx';
    const data: Blob = new Blob([buffer], {
        type: EXCEL_TYPE
    });
    FileSaver.saveAs(data, fileName + '_export_' + new Date().getTime() + EXCEL_EXTENSION);
}

function changeListsToString(o : any) {
    Object.keys(o).forEach(k => {
      if (o[k] instanceof Array) {
        o[k] = o[k].join(", ");
      }
    });
    return o;
}