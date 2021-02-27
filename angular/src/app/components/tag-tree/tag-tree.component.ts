import {Component, ViewChild, AfterViewInit, ViewEncapsulation, OnInit} from '@angular/core';

import { jqxTreeComponent } from 'jqwidgets-ng/jqxtree';
import { jqxMenuComponent } from 'jqwidgets-ng/jqxmenu';
import {coerceStringArray} from "@angular/cdk/coercion";

@Component({
  selector: 'app-tag-tree',
  templateUrl: './tag-tree.component.html',
  styleUrls: ['./tag-tree.component.less']
})

export class TagTreeComponent implements AfterViewInit, OnInit {
  @ViewChild('myTree', { static: false }) myTree: jqxTreeComponent;
  @ViewChild('myMenu', { static: false }) myMenu: jqxMenuComponent;
  @ViewChild('myName') input;
  
  editable: boolean = false;
  
  ngOnInit() {
    this.editable = false;
  }
  
  ngAfterViewInit(): void {
    this.myTree.selectItem(document.getElementById('home'));
    this.myTree.expandItem(document.getElementById('solutions'));
    
    document.addEventListener('contextmenu', event => {
      event.preventDefault();
      // if ((event.target).classList.contains('jqx-tree-item')) {
      //   this.myTree.selectItem((<HTMLElement>event.target).parentNode);
      //   let scrollTop = window.scrollY;
      //   let scrollLeft = window.scrollX;
      //   this.myMenu.open(event.clientX + 5 + scrollLeft, event.clientY + 5 + scrollTop);
      //   return false;
      // } else {
      //   this.myMenu.close();
      // }
    });
  }
  myMenuOnItemClick(event: any): void {
    let item = event.args.innerText;
    let selectedItem = null;
    switch (item) {
      case "Add Item":
        selectedItem = this.myTree.getSelectedItem();
        if (selectedItem != null) {
          this.myTree.addTo({ label: 'Item' }, selectedItem.element);
        }
        break;
      case "Remove Item":
        selectedItem = this.myTree.getSelectedItem();
        if (selectedItem != null) {
          this.myTree.removeItem(selectedItem.element);
        }
        break;
      case "Rename":
        selectedItem = this.myTree.getSelectedItem();
        if (selectedItem != null) {
          this.editable = true;
          console.log(this.input.getText());
        }
        //   if(this.input != null) {
        //     console.log(this.input.getText());
        //     this.myTree.updateItem(selectedItem, {label : this.input.nativeElement.value});
        //   }
        //}

        
        break;
    }
    
    
  };
}
