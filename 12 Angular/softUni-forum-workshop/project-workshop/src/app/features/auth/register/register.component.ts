import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../../core/services/auth.service';
import { UserService } from '../../../core/services/user.service';
import { User, UserWithCredentials } from '../../../shared/interfaces/user';
import { RouterLink, Router } from '@angular/router';

@Component({
  selector: 'app-register',
  imports: [FormsModule, RouterLink],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
})
export class RegisterComponent {
  private router = inject(Router);
  private authService = inject(AuthService);
  private userService = inject(UserService);

  username = '';
  email = '';
  tel = '';
  password = '';
  rePassword = '';

  onRegister(): void {
    if (!this.email) {
      alert('Email is required!');
      return; 
    }

      if (!this.password) {
      alert('Password is required!');
      return; 
    }

    if (this.password !== this.rePassword) {
      alert('Passwords do not match!');
      return; 
    }

    const newUser:UserWithCredentials = {
      _id: this.generateId(),
      username: this.username,
      email: this.email,
      tel: "+359" + this.tel,
      password: this.password
    }

    const sesionUser = this.userService.register(newUser);
    this.authService.setSession(sesionUser);
    this.router.navigate(['/themes']);

  }

  private generateId(): string {
    return Math.random().toString(36).substring(2,15);
  }

}
