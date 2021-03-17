import {Component, ViewChild, AfterViewInit, ViewEncapsulation, OnInit, SimpleChange} from '@angular/core';

import {jqxTreeComponent} from 'jqwidgets-ng/jqxtree';
import {jqxTreeGridComponent} from 'jqwidgets-ng/jqxtreegrid';
import {jqxMenuComponent} from 'jqwidgets-ng/jqxmenu';
import {TagService} from "../../services/tag-service/tag.service";
import {Tag} from "../../model/entity/tag";
import {checkIfClassIsExported} from "@angular/compiler-cli/src/ngtsc/typecheck/src/ts_util";

@Component({
  selector: 'app-tag-tree',
  templateUrl: './tag-tree.component.html',
  styleUrls: ['./tag-tree.component.less']
})

export class TagTreeComponent implements OnInit {
  @ViewChild('TreeGrid') treeGrid: jqxTreeGridComponent;
  @ViewChild('myTreeGrid') myTreeGrid: jqxTreeGridComponent;
  @ViewChild('myMenu') myMenu: jqxMenuComponent;

  constructor(private tagService: TagService) {
  }

  getWidth(): any {
    if (document.body.offsetWidth < 250) {
      return '90%';
    }

    return 200;
  }

  tags: any[] = null;

  employees: any[] = [
    {
      id: 2,
      title: "Andrew",
      parentId: "Fuller",
      expanded: "true",
      children: [
        {
          id: 8,
          title: "Laura",
          parentId: "Callahan"
        }
      ]
    }
  ];

  ngOnInit(): void {
    let allTags: Tag[];

    this.tagService.getAll().subscribe(tags => {
      allTags = tags
      this.tags = allTags.filter(tag => tag.parentId === 0);

      for (let tag of this.tags) {
        tag.children = allTags.filter(x => x.parentId === tag.id);
        tag.expanded = false;
      }
      this.dataAdapter = new jqx.dataAdapter(this.getSource());
      this.dataAdapter.localData = this.tags;
    });
  }

  getSource(): any {
    return {
      dataType: "json",
      dataFields: [
        {name: "id", type: "number"},
        {name: "title", type: "string"},
        {name: "parentId", type: "number"},
        {name: "children", type: "array"},
        {name: "expanded", type: "bool"}
      ],
      hierarchy: {
        root: "children"
      },
      id: "id",
      localData: this.tags
    };
  }

  dataAdapter: any;

  columns =
    [
      {text: 'tag tree view', dataField: 'title', minWidth: 100, width: 200},
    ];

  ready = (): void => {
    this.myTreeGrid.expandRow(32);
    document.addEventListener('contextmenu', event => event.preventDefault());
  };

  editSettings: any =
    {
      saveOnPageChange: true, saveOnBlur: true, saveOnSelectionChange: true,
      cancelOnEsc: true, saveOnEnter: true, editSingleCell: true, editOnDoubleClick: true, editOnF2: true
    };

  oldCellName: string;
  
  treeGridOnCellBeginEdit(event: any): void {
    this.oldCellName = event.args.value; 
    console.log(event.args);
  }

  treeGridOnCellEndEdit(event: any): void {
    let args = event.args;
    let rowKey = args.key; // id of tag
    let rowData = args.row; // all internal data of tag
    let value = args.value; // title of selected tag
    
    if (this.oldCellName === value) {
      console.log("no changes");
      return;
    }
    
    this.tagService.getById(rowKey).subscribe(tag => {
      tag.title = value;
      this.tagService.update(tag).subscribe(_ =>{
        console.log("item id=" + tag.id + " \'" +  this.oldCellName + "\' was renamed, new name is \'" + value + "\'");
      });
    });
  }

  myTreeGridOnContextmenu(): boolean {
    return false;
  };

  myTreeGridOnRowClick(event: any): boolean {
    let args = event.args;
    if (args.originalEvent.button == 2) {
      let scrollTop = window.scrollX;
      let scrollLeft = window.scrollY;
      this.myMenu.open(parseInt(event.args.originalEvent.clientX) + 5 + scrollLeft, parseInt(event.args.originalEvent.clientY) + 5 + scrollTop);
      return false;
    }
  };

  myMenuOnItemClick(event: any): void {
    let args = event.args;
    let selection = this.myTreeGrid.getSelection();
    let rowid = selection[0].uid;

    switch (args.innerHTML) { 
      case 'Delete Selected Row':
        console.log(selection.pop().data);
        this.myTreeGrid.deleteRow(rowid);

        //this.deleteTagBranch(selection.pop());
        break;
      case 'Add new Row':
        
        this.tagService.getById(selection.pop().data.id).subscribe(tag => {
          
        });
        
        
        
        this.myTreeGrid.expandRow(rowid);
        this.myTreeGrid.addRow(rowid + 1, {}, 'first', rowid);
        this.myTreeGrid.clearSelection();
        this.myTreeGrid.selectRow(rowid + 1);
        this.myTreeGrid.beginRowEdit(rowid + 1);

        break;
    }
  };

  deleteTagBranch(tag: any): any[] {
    this.tagService.delete(tag.id);

    let children = tag.children;
    if (children.isEmpty) {
      return null;
    } else {
      for (let child of children) {
        this.deleteTagBranch(child);
      }
    }
  }
}
