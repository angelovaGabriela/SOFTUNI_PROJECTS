import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { MissionsService } from '../../services/missions.service';
import { Mission } from '../../interfaces/mission.interface';
@Component({
  selector: 'app-missions',
  imports: [RouterLink],
  templateUrl: './missions.html',
  styleUrl: './missions.css',
})
export class Missions {
  missions: Mission[] = [];

  constructor(private missionService: MissionsService) {
    this.missions = this.missionService.getAllMissions();

  }
}
