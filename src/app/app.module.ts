import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MenubarComponent } from './components/menubar/menubar.component';
// import { ActorsTableComponent } from './components/actors-table/actors-table.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { ActorsContentComponent } from './components/actors-content/actors-content.component';
import { TableComponent } from './components/table/table.component';
import { CardComponent } from './components/card/card.component';
import { FormComponent } from './components/form/form.component';

@NgModule({
  declarations: [
    AppComponent,
    MenubarComponent,
    // ActorsTableComponent,
    ActorsContentComponent,
    TableComponent,
    CardComponent,
    FormComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule,
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
