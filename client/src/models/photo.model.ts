import { alias } from '../decorators/alias.decorator';

import { ModelFactory } from './model-factory.model';

export class Photo extends ModelFactory {
  @alias('id') id: string;
  @alias('path') path: string;
  @alias('creationTime', Date) creationTime: Date;
  @alias('createdBy') createdBy: string;
  @alias('creationTime', Date) updateTime: Date;
  @alias('updatedBy') updatedBy: string;
}
