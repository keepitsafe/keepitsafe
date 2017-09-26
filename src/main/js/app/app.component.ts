import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  keeps = [
    { name: 'k1', description: 'Lorem ipsum dolor sit amet, consectetur' },
    { name: 'k2', description: 'Lorem ipsum dolor sit amet, consectetur' },
    { name: 'k3', description: 'Lorem ipsum dolor sit amet, consectetur' },
    { name: 'k4', description: 'Lorem ipsum dolor sit amet, consectetur' }
  ];
}
