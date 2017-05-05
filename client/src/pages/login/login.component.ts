import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'login-page',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  public loginForm: FormGroup;
  public loading: boolean = false;

  constructor(private formBuilder: FormBuilder) {
    this.loginForm = formBuilder.group({
      'username': ['', Validators.compose([Validators.required])],
      'password': ['', Validators.compose([Validators.required])]
    });
  }

  login() {
    this.loading = true;
    setTimeout(() => this.loading = false, 5000);
    if(this.loginForm.valid) {
      console.log(this.loginForm.value, this.loginForm.valid);
    }
  }

}
