import { Component } from '@angular/core';

@Component({
  selector: 'menubar',
  templateUrl: './menubar.component.html',
  styleUrl: './menubar.component.css'
})
export class MenubarComponent {
  protected dropdown : boolean = false
  protected routers :string[] = [
    "actors-table",
    "actors-content",
  ]
  protected enableDropdown() {
    this.dropdown = !this.dropdown;
  }
}
