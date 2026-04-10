import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { MissionsService } from '../../services/missions.service';
import { Mission } from '../../interfaces/mission.interface';
import { HighlightsDirective } from '../../directives/highlights.directive';
import { StatusStyleDirective } from '../../directives/status-style.directive';

@Component({
  selector: 'app-missions',
  imports: [RouterLink, HighlightsDirective, StatusStyleDirective],
  templateUrl: './missions.html',
  styleUrl: './missions.css',
})
export class Missions {
  missions: Mission[] = [];

  constructor(private missionService: MissionsService) {
    this.missions = this.missionService.getAllMissions();

  }
}
