import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  imports: [FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss',
})
export class RegisterComponent {

  user = {
    username: '',
    email: '',
    password: '',
    role: 'aluno',
  };

  constructor(private http: HttpClient, private router: Router) {}

  onSubmit(form: any) {
    if (form.valid) {
      this.http.post('http://localhost:8080/auth/register', this.user).subscribe(
        (resoibse: any) => {
          this.router.navigate(['/login'])
        },
        (error) => {
          console.error('Error ao registrar:', error);
        }
      );
    }
  }
}
