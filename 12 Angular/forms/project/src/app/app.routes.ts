import { Routes } from '@angular/router';
import { Missions } from './features/missions/missions';
import { CountDown } from './features/count-down/count-down';
import { Login } from './features/login/login';
import { MissionDetails } from './features/mission-details/mission-details';
import { missionResolver } from './guardes/mission.resolver';
import { CommandCenter } from './features/command-center/command-center';
import { authGuard } from './guardes/auth.guard';
import { NotFound } from './features/not-found/not-found';


export const routes: Routes = [

    { path: '', redirectTo: 'missions', pathMatch: 'full' }, // home page -> redirect to /missions
    { path: 'missions', component: Missions },
    { path: 'missions/:id', component: MissionDetails, resolve: { mission: missionResolver } },
    { path: 'countDown', component: CountDown },
    { path: 'commandCenter', component: CommandCenter, canActivate: [authGuard] },
    { path: 'login', component: Login },
    { path: '**', component: NotFound }, // wildcard route for 404 page
];
