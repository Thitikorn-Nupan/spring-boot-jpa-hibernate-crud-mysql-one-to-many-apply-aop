import {EditHistories} from "./edit-history";

export class Author {
  public aid: string
  public fullname: string
  public age: number
  public alive: boolean
  public image!: string
  public editHistories: EditHistories[]

  constructor(aid: string, fullname: string, age: number, alive: boolean, editHistories: EditHistories[]) {
    this.aid = aid;
    this.fullname = fullname;
    this.age = age;
    this.alive = alive;
    this.editHistories = editHistories;
  }
}
