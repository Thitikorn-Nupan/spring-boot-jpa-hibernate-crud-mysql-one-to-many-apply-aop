import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
// import {ActorsTableComponent} from "./components/actors-table/actors-table.component";
import {ActorsContentComponent} from "./components/actors-content/actors-content.component";

const routes: Routes = [
  // {path: 'actors-table', component: ActorsTableComponent},
  {path: 'actors-content', component: ActorsContentComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
