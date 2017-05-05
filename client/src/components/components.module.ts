import { CommonModule } from '@angular/common';
import { ModuleWithProviders, NgModule } from '@angular/core';

import * as TabsComponent from './tabs';

@NgModule({
  imports: [ CommonModule ],
  declarations: [TabsComponent.TabsComponent, TabsComponent.TabComponent ],
  exports: [ TabsComponent.TabsComponent, TabsComponent.TabComponent ]
})
export class ComponentsModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: ComponentsModule,
    };
  }
}
