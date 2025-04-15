import {Component, EventEmitter, Input, Output} from '@angular/core';


@Component({
  selector: 'card',
  templateUrl: './card.component.html',
  styleUrl: './card.component.css'
})
export class CardComponent {

  protected subCard : boolean = false
  @Input()
  public declare response : { card : boolean , form : boolean , row : any}
  @Output()
  protected getCardStatus = new EventEmitter<boolean>();

  protected getColorStatus(alive: boolean) {
    return alive ? "h-5 w-5 rounded-full me-2 bg-green-500" : "h-5 w-5 rounded-full me-2  bg-red-500";
  }

  protected disableCard() {
    this.getCardStatus.emit(false);
  }

  protected enableSubCard() {
    this.subCard = !this.subCard;
  }

  protected convertUnixTimestamp(unixTimestamp: number) {
    // Create a new Date object from the Unix timestamp (in milliseconds)
    const date = new Date(unixTimestamp * 1000);
    // Get the year, month, date, hours, minutes, and seconds from the Date object
    const year = date.getFullYear();
    const month = date.getMonth() + 1; // Months are 0-based
    const day = date.getDate();
    const hours = date.getHours();
    const minutes = date.getMinutes();
    const seconds = date.getSeconds();
    // Format the date and  
    // time string according to your desired format
    return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
  }
}
