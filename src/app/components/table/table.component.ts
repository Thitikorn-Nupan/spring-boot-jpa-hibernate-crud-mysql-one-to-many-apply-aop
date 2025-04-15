import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {Author} from "../../entity/author";
import {FormComponent} from "../form/form.component";

@Component({
  selector: 'data-table',
  templateUrl: './table.component.html',
  styleUrl: './table.component.css'
})
export class TableComponent {


  @Input()
  public declare headerColumns : string[]
  @Input()
  public declare data : Array<any>
  @Output()
  protected getReadData = new EventEmitter<any>();
  @Output()
  protected getUpdateData = new EventEmitter<any>();

  protected getColorStatus(alive: boolean) {
    return alive ? "h-2.5 w-2.5 rounded-full me-2 bg-green-500" : "h-2.5 w-2.5 rounded-full me-2  bg-red-500";
  }

  protected getColumnWidth(index: number) {
    return index === 0 ? "p-4" : "px-6 py-3";
  }

  protected onReadClick(row : any) {
    const response = {
      card : true,
      form : false,
      row : row
    }
    this.getReadData.emit(response)
  }

  protected onUpdateClick(row : any) {
    const response = {
      card : false,
      form : true,
      row : row
    }
    this.getUpdateData.emit(response)
  }

}
