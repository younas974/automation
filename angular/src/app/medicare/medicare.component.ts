import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { userModel } from './medicare.model';
import { MedicareService } from './medicare.service';


@Component({
  selector: 'app-medicare',
  templateUrl: './medicare.component.html',
  styleUrls: ['./medicare.component.css'],
  providers:[MedicareService]
})
export class MedicareComponent implements OnInit {
 
  user:userModel=new userModel();
  
  profileForm = new FormGroup({
    firstName: new FormControl(this.user.firstName),
    lastName: new FormControl(this.user.lastName),
    email: new FormControl(this.user.email),
    phoneNumber: new FormControl(this.user.phoneNumber),
    street: new FormControl(this.user.street),
    city: new FormControl(this.user.city),
    state: new FormControl(this.user.state),
    zipCode: new FormControl(this.user.zipCode),    
  });


  constructor() { }

  ngOnInit() {
   
    

}
}