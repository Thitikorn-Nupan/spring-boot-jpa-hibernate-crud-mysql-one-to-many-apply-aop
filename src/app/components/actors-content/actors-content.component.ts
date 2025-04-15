import {Component, OnInit, ViewChild} from '@angular/core';
import {AuthorService} from "../../service/author.service";
import {Author} from "../../entity/author";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {FormComponent} from "../form/form.component";

@Component({
  selector: 'actors-content',
  templateUrl: './actors-content.component.html',
  styleUrl: './actors-content.component.css'
})
export class ActorsContentComponent implements OnInit {

  @ViewChild(FormComponent,{static: false})
  protected formComponent! : FormComponent;

  protected formGroup : FormGroup;
  protected searchInputFormGroupName : string = 'searchFullname'
  protected headerColumns  : string []
  protected authors: Author[];
  protected authorsCanChange: Author[];
  protected response : { card : boolean , form : boolean , row : any  }

  constructor(private authorService: AuthorService){
    this.headerColumns = ["#","Info","Total Modify","Alive","Options"]
    this.authors = []
    this.authorsCanChange = []
    this.formGroup = new FormGroup({})
    this.response = {
      card : false,
      form : false,
      row : null,
    }
  }

  ngOnInit(): void {
    this.authorService.authorsReplaySubject.subscribe((authors: Author[]) => {
      this.authors = authors;
      // console.log(this.authors);
      this.authors.map((author: Author) => {
        author.image = "https://www.svgrepo.com/show/535711/user.svg"
        // way to sort the lest to new id (** use sql is easy then this)
        author.editHistories = author.editHistories.sort((editHistoryL, editHistoryR) => editHistoryR.eid - editHistoryL.eid)
      })
      this.authorsCanChange = this.authors
    })
    // add controller after created instance
    this.formGroup.addControl(this.searchInputFormGroupName, new FormControl('',Validators.required))
  }

  protected onSearchClick() {
    // console.log('Clicked ',this.formGroup.valid);
    if (this.formGroup.valid) {
      // if this.authorsCanChange changes data table change too
      this.authorsCanChange = this.authors.filter((author: Author) => author.fullname === this.formGroup.value.searchFullname);
      if (this.authorsCanChange.length === 0) {
        this.authorsCanChange = this.authors
      }
    }
  }

  protected setReadData(response: any) {
    console.log('read ',response)
    this.response = response
  }

  protected setUpdateData(response: any) {
    console.log('updated ',response)
    this.response = response
    this.formComponent?.reloadFormGroup(response.row)
  }

  protected setCardStatus(status: boolean) {
    this.response.card = status
  }

  protected setFormStatus(status: boolean) {
    this.response.form = status
  }

  protected setFormGroupUpdate($event: FormGroup) {
    if ($event.valid) {
      const aid = $event.value.aid
      const age = $event.value.age
      const fullname = $event.value.fullname
      const alive = $event.value.alive
      const author = new Author(aid,fullname,age,alive,[])
      this.authorService.updateAuthor(author,author.aid)
    }
  }
}
