import { Component, ContentChildren, QueryList, AfterContentInit } from '@angular/core';

import { TabComponent } from '../tab/tab.component';

@Component({
  selector: 'tabs',
  templateUrl: './tabs.component.html',
  styleUrls: ['./tabs.component.scss']
})
export class TabsComponent implements AfterContentInit {

  @ContentChildren(TabComponent) tabs: QueryList<TabComponent>;

  ngAfterContentInit() {
    let activeTabs = this.tabs.filter((tab) => tab.selected);
    if(activeTabs.length === 0) {
      this.selectTab(this.tabs.first);
    }
    this.tabs.toArray().forEach((tab) => {
      tab.tabClicked.subscribe(() => {
        this.selectTab(tab);
      });
    })
  }

  selectTab(tab: TabComponent) {
    this.tabs.toArray().forEach((tab) => {
      tab.setSelected(false);
    });
    tab.setSelected(true);
  }

}
