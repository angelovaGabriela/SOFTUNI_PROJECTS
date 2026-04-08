import { Component, computed, inject } from '@angular/core';
import { AuthService } from '../../core/services/auth.service';
import { RouterLink, Router, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-header',
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
})
export class HeaderComponent {
  private authservice = inject(AuthService);
  private router = inject(Router);

  isLoggedIn = this.authservice.isLoggedIn;

  username = computed(() => this.authservice.currentUser()?.username ?? '');

  onLogout(): void {
    this.authservice.logout();
    this.router.navigate(['/home']);
  }
}
