import { Component, inject } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { Mission } from '../../interfaces/mission.interface';


@Component({
  selector: 'app-mission-form',
  imports: [ReactiveFormsModule],
  templateUrl: './mission-form.html',
  styleUrl: './mission-form.css',
})
export class MissionForm {
  private fb = inject(FormBuilder)

  destinations = ['Moon', 'Mars', 'Venus', 'Jupiter', 'Saturn', 'Asteroid Belt'];
  statuses: Array<'planned' | 'active' | 'completed'> = ['planned', 'active', 'completed'];


  missionForm = this.fb.group({
    name: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]],
    destination: ['', Validators.required],
    launchDate: ['', [Validators.required, Validators.pattern(/^\d{4}-\d{2}-\d{2}$/)]],
    status: ['planned', Validators.required],
    crew: [4, [Validators.required, Validators.min(1), Validators.max(20)]],
    description: ['']
  })

  get nameControl() {
    return this.missionForm.get('name');
  }

  get destinationControl() {
    return this.missionForm.get('destination');
  }

  get launchDateControl() {
    return this.missionForm.get('launchDate');
  }

  get statusControl() {
    return this.missionForm.get('status');
  }

  get crewControl() {
    return this.missionForm.get('crew');
  }

  get descriptionControl() {
    return this.missionForm.get('description');
  }

  onSubmit(): void {
    if (this.missionForm.invalid) {
      this.missionForm.markAllAsTouched();
      return;
    } 

    const newMission: Mission = {
      id: Date.now(),
      ...(this.missionForm.value as Omit<Mission, 'id'>)
    };

    this.missionForm.reset({
      name: '',
      destination: '',
      launchDate: '',
      status: 'planned',
      crew: 4,
      description: ''
    });
  }
}
