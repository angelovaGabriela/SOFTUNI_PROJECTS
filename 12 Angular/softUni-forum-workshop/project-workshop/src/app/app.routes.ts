import { Routes } from '@angular/router';
import { HomeComponent } from './features/home/home.component';
import { ThemesComponent } from './features/themes/themes.component';
import { LoginComponent } from './features/auth/login/login.component';
import { RegisterComponent } from './features/auth/register/register.component';
import { ThemesContentComponent } from './features/themes/themes-content/themes-content.component';
import { authGuard } from './core/guards/auth.guard';
import { ProfileComponent } from './features/profile/profile.component';
import { NotFoundComponent } from './features/not-found/not-found.component';
import { NewThemeComponent } from './features/themes/new-theme/new-theme.component';


export const routes: Routes = [
    { path: "", redirectTo: 'home', pathMatch: 'full' },

    { path: "home", component: HomeComponent },


    { path: "login", component: LoginComponent },
    { path: "register", component: RegisterComponent },

    { path: "themes", component: ThemesComponent },
    { path: "themes/:themeId", component: ThemesContentComponent },
    { path: "add-theme", component: NewThemeComponent, canActivate: [authGuard] },

    { path: "profile", component: ProfileComponent, canActivate: [authGuard] },

    { path: "**", component: NotFoundComponent },
];
