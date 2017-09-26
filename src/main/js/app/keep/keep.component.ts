import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-keep',
  templateUrl: './keep.component.html',
  styleUrls: ['./keep.component.scss']
})
export class KeepComponent implements OnInit {

  @Input() keep;

  constructor() { }

  ngOnInit() {
  }

}
