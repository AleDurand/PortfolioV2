import { alias } from '../decorators/alias.decorator';

import { ModelFactory } from './model-factory.model';
import { Photo } from './photo.model';

export class Album extends ModelFactory {
  @alias('id') id: string;
  @alias('name') name: string;
  @alias('description') description: string;
  @alias('type') type: string;
  @alias('path') path: string;
  @alias('photos', Photo) photos: Array<Photo>;
  @alias('readOnly') readOnly: boolean;
  @alias('creationTime', Date) creationTime: Date;
  @alias('createdBy') createdBy: string;
  @alias('updateTime', Date) updateTime: Date;
  @alias('updatedBy') updatedBy: string;
}
