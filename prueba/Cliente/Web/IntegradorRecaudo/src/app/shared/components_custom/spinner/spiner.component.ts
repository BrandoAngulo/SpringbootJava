import { Component, Input, OnInit } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-spiner',
  templateUrl: './spiner.component.html',
  styleUrls: ['./spiner.component.css']
})
export class SpinerComponent implements OnInit {

  @Input() inFullScreen : boolean = true;

  constructor(private spinner: NgxSpinnerService) {
  }  

  ngOnInit(): void {
  }

  spinnerShow() {
    this.spinner.show();
  }

  spinnerHide() {
    this.spinner.hide();
  }

}
