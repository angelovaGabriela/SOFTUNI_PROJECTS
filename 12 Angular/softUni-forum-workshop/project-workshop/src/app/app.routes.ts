import { Routes } from '@angular/router';
import { HomeComponent } from './features/home/home.component';
import { ThemesComponent } from './features/themes/themes.component';
import { LoginComponent } from './features/auth/login/login.component';
import { RegisterComponent } from './features/auth/register/register.component';
import { ThemesContentComponent } from './features/themes/themes-content/themes-content.component';
import { authGuard } from './core/guards/auth.guard';

export const routes: Routes = [
    { path: "", redirectTo: 'home', pathMatch: 'full' },

    { path: "home", component: HomeComponent },
    { path: "themes", component: ThemesComponent },

    { path: "login", component: LoginComponent },
    { path: "register", component: RegisterComponent },

    { path: "themes", component: ThemesComponent },
    { path: "themes/:themeId", component: ThemesContentComponent, canActivate: [authGuard]},
];
