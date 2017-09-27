import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class KeepService {

  constructor(private http: HttpClient) { }

  getKeeps() {
    this.http.get('/api/keep').subscribe(
      data => {
        console.log(data);
        return data;
      },
      error => {
        console.log(error.statusText);
        // TODO implement error handling
      }
    );
  }
}
