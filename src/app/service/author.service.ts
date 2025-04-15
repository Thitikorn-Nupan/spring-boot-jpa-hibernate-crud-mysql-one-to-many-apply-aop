import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable, ReplaySubject} from "rxjs";
import {Author} from "../entity/author";
import {environment} from "../../environments/environment.development";

@Injectable({
  providedIn: 'root'
})
export class AuthorService {

  public authorsReplaySubject : ReplaySubject<Author[]>
  private baseUrl : string = environment.api;

  constructor(private http: HttpClient) {
    this.authorsReplaySubject = new ReplaySubject<Author[]>();
    this.publishAuthors()
  }

  public updateAuthor(author: Author,aid : string) : void {
    this.http.put<Author>(`${this.baseUrl}/update?aid=${aid}`,author).subscribe((author: Author) => {
      if (author!=null) {
        this.publishAuthors()
      }
    })
  }

  private publishAuthors() {
    this.http.get<Author[]>(`${this.baseUrl}/reads`).subscribe((authors: Author[]) => {
      this.authorsReplaySubject.next(authors)
    })
  }

}
