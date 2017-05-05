import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { AlbumService } from '../../../services/album.service';

import { Album } from '../../../models/album.model';

@Component({
  selector: 'album-list-page',
  templateUrl: './album-list.component.html',
  styleUrls: ['./album-list.component.scss']
})
export class AlbumListComponent {

  public albums: Array<Album>;
  public filter: string;

  constructor(private route: ActivatedRoute, private albumService: AlbumService) {
    this.route.data.subscribe((data) => {
      this.filter = data.filter;
      console.log(this.filter);
    });
  }

  ngAfterViewInit() {
    this.albumService.getAll().subscribe(
      (data) => { this.albums = data; }
    );
  }
}
