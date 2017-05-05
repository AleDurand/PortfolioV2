import { NgModule } from '@angular/core';
import { MdTabsModule, MdTabNavBar } from '@angular/material';

@NgModule({
  imports: [MdTabsModule],
  exports: [MdTabsModule],
  providers: [MdTabNavBar]
})
export class MyMaterialModule { }
