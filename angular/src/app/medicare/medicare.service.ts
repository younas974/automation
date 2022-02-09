import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { userModel } from './medicare.model';

const baseUrl  ="http://localhost:4200/"

const httpOptions: { headers :any; observe :any } = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  }),
  observe: 'response'
};
@Injectable({
  providedIn: 'root'
})


export class MedicareService {


  constructor(private http:HttpClient) { }
  
  addHero(data: userModel) {
    return this.http.post<userModel>(baseUrl, data, httpOptions) 
  }
}
