import { Routes } from '@angular/router';
import { Missions } from './features/missions/missions';
import { CountDown } from './features/count-down/count-down';
import { Login } from './features/login/login';

export const routes: Routes = [
    
    { path: '', redirectTo: 'missions', pathMatch: 'full' }, // home page -> redirect to /missions
    { path: 'missions', component: Missions  }, // /missions -> putting this inside the main
    { path: 'countDown', component: CountDown },
    { path: 'login', component: Login },
];
