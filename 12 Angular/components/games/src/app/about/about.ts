import { Component, OnInit, OnDestroy } from '@angular/core';

@Component({
  selector: 'app-about',
  standalone: true,
  imports: [],
  templateUrl: './about.html',
  styleUrl: './about.css',
})
export class About implements OnInit, OnDestroy {
  companyName = 'Games Depot';
  foundYear = '2019';

  manager: { name: string, email: string } | null = {
    name:'Ivan Ivanov',
    email: 'vanco-ivanov@gmail.com'
  }

  ngOnInit() {
    console.log('CREATED');
  }

  ngOnDestroy() {
    console.log('DELETED');
  }
}
