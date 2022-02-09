import { Component } from '@angular/core';
import { MedicareService } from './medicare/medicare.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  data:Array<any>;
  title = 'AngularClient';
  constructor (private jasonplaceholder: MedicareService){
    this.data = new Array<any>()
  }
  getDataFromAPI(){
   
  }
}
