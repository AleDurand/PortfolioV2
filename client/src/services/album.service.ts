import { Injectable } from '@angular/core';

import { environment } from '../environments/environment';
import { SafeHttp } from './safe-http.service'

import { Album } from '../models/album.model';

@Injectable()
export class AlbumService {

  private basepath: string;

  constructor(private http: SafeHttp) {
    this.basepath = environment.apiUrl + '/albums'
  }

  getAll() {
    return this.http.get(this.basepath).map((response) => {
      let toReturn = [];
      response.json().content.forEach((obj) => { toReturn.push(new Album(obj)); });
      return toReturn;
    });
  }

}
