import { Component, ViewChild } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { CrewMember } from '../../interfaces/crew-member.interface';
import { from } from 'rxjs';


@Component({
  selector: 'app-crew-register',
  imports: [FormsModule],
  templateUrl: './crew-register.html',
  styleUrl: './crew-register.css',
})
export class CrewRegister {
  @ViewChild('crewForm') crewForm!: NgForm;

  crewMember: CrewMember = {
    name: '',
    role: '',
    experience: 0,
    email: ''
  }

  roles: string[] = ['Commander', 'Pilot', 'Engineer', 'Scientist', 'Medical Officer'];

  registeredCrew: CrewMember[] = [];

  onSubmit(): void {

    if (this.crewForm.invalid) {
      Object.keys(this.crewForm.controls).forEach(key => {
        this.crewForm.controls[key].markAsTouched();
      });
      return;
    }

    this.registeredCrew.push({ ...this.crewMember });
    this.crewForm.reset();

    this.crewMember = {
      name: '',
      role: '',
      experience: 0,
      email: ''
    }
  }
}
