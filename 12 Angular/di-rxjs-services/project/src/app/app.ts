import { Component } from '@angular/core';
import { Counter } from './components/counter/counter';
import { RxjsDemo  } from './components/rxjs-demo/rxjs-demo';
import { Posts } from './components/posts/posts';


@Component({
  selector: 'app-root',
  imports: [Counter, RxjsDemo, Posts],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  
}
