import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'data-form',
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})
export class FormComponent implements OnInit {

  protected declare formGroup: FormGroup;
  protected declare formGroupControlNames : string[]
  protected declare formGroupControlValues : any[]

  @Input()
  public declare response: { card: boolean, form: boolean, row: any  }
  @Output()
  protected getFormStatus = new EventEmitter<boolean>();
  @Output()
  protected getReloadStatus = new EventEmitter<boolean>();
  @Output()
  protected getFormGroup = new EventEmitter<FormGroup>();

  constructor() {
  }


  ngOnInit(): void {
    this.reloadFormGroup(this.response.row)
  }

  public reloadFormGroup(row : any) {
    this.formGroup = new FormGroup({})
    this.formGroupControlNames = []
    this.formGroupControlValues = []
    Object.keys(row).forEach((k) => {
      this.formGroupControlNames.push(k)
    })
    Object.values(row).forEach((v) => {
      this.formGroupControlValues.push(v)
    })
    for (let i = 0; i < this.formGroupControlNames.length; i++) {
      this.formGroup.addControl(this.formGroupControlNames[i],new FormControl(this.formGroupControlValues[i],Validators.required))
    }
    console.log('current form group',this.formGroup)
  }

  protected getColorStatus(alive: boolean) {
    return alive ? "h-5 w-5 rounded-full me-2 bg-green-500" : "h-5 w-5 rounded-full me-2  bg-red-500";
  }

  protected disableForm() {
    this.getFormStatus.emit(false);
  }

  protected onFormUpdateClick() {
    // console.log('update form group ',this.formGroup)
    this.getFormGroup.emit(this.formGroup)
    this.response.row = this.formGroup.value // update response object
  }
}
