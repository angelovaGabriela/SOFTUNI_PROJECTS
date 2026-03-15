import { Component } from '@angular/core';
import { Counter } from './components/counter/counter';
import { RxjsDemo  } from './components/rxjs-demo/rxjs-demo';

@Component({
  selector: 'app-root',
  imports: [Counter, RxjsDemo],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  
}
