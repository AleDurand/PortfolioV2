import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '@angular/material';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';

import 'hammerjs';

import { Router, RouterOutlet, RouterOutletMap } from '@angular/router';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { MyMaterialModule } from './app-material.module';

import { ComponentsModule } from '../components/components.module';
import { PipesModule } from '../pipes/pipes.module';

import { AlbumListComponent } from '../pages/albums/album-list/album-list.component';
import { ContactComponent } from '../pages/contact/contact.component';
import { HomeComponent } from '../pages/home/home.component';
import { LoginComponent } from '../pages/login/login.component';
import { PageNotFoundComponent } from '../pages/page-not-found/page-not-found.component';

import { AlbumService } from '../services/album.service';
import { SafeHttp } from '../services/safe-http.service';

@NgModule({
  declarations: [
    AppComponent, AlbumListComponent, ContactComponent,
    LoginComponent, HomeComponent, PageNotFoundComponent
  ],
  imports: [
    AppRoutingModule, BrowserModule, BrowserAnimationsModule, ComponentsModule.forRoot(),
    FormsModule, ReactiveFormsModule, HttpModule, MaterialModule.forRoot(), MyMaterialModule,
    PipesModule, RouterModule
  ],
  bootstrap: [AppComponent],
  entryComponents: [],
  providers: [ AlbumService, SafeHttp ]
})
export class AppModule { }
