import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { KeepComponent } from './keep/keep.component';
import { KeepService } from './keep.service';

@NgModule({
  declarations: [
    AppComponent,
    KeepComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [
    KeepService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
