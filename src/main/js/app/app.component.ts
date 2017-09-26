import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  keeps = [
    { name: 'k1', description: 'Lorem ipsum dolor sit amet, consectetur, Lorem ipsum dolor sit amet, consectetu' },
    { name: 'k2', description: 'Lorem ipsum dolor sit amet, consectetur a asd ad asd as lermasd asdjasdla' },
    { name: 'k3', description: 'Lorem ipsum dolor sit amet, consectetur' },
    { name: 'k4', description: 'Lorem ipsum dolor sit amet, consectetur' }
  ];
}
