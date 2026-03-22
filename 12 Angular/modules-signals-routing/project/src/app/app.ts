import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Nav } from '../layout/nav/nav';
import { CountDown } from './features/count-down/count-down';


@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Nav, CountDown],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
 
}
