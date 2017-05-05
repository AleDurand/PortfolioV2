import { Component, ElementRef, EventEmitter, Output, Input, Renderer2 } from '@angular/core';

@Component({
  selector: 'tab',
  templateUrl: './tab.component.html',
  styleUrls: ['./tab.component.scss']
})
export class TabComponent {

  @Input() title: string;
  @Input() selected: boolean = false;
  @Output() tabClicked = new EventEmitter<null>();

  constructor(private element: ElementRef, private renderer: Renderer2) { }

  setSelected(selected: boolean) {
    this.selected = selected;
    let element = this.element.nativeElement
    if(selected) this.renderer.addClass(element, 'selected');
    else this.renderer.removeClass(element, 'selected');
  }

  clicked() {
    this.tabClicked.emit();
  }

}
