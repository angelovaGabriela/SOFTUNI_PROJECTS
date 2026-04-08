import { Routes } from '@angular/router';
import { HomeComponent } from './features/home/home.component';
import { ThemesComponent } from './features/themes/themes.component';
import { LoginComponent } from './features/auth/login/login.component';
import { RegisterComponent } from './features/auth/register/register.component';
import { ThemesContentComponent } from './features/themes/themes-content/themes-content.component';
import { authGuard } from './core/guards/auth.guard';
import { ProfileComponent } from './features/profile/profile.component';
import { NotFoundComponent } from './features/not-found/not-found.component';


export const routes: Routes = [
    { path: "", redirectTo: 'home', pathMatch: 'full' },

    { path: "home", component: HomeComponent },
    { path: "themes", component: ThemesComponent },

    { path: "login", component: LoginComponent },
    { path: "register", component: RegisterComponent },

    { path: "themes", component: ThemesComponent },
    { path: "themes/:themeId", component: ThemesContentComponent },

    { path: "profile", component: ProfileComponent, canActivate: [authGuard]},

    { path: "**", component: NotFoundComponent},
];
