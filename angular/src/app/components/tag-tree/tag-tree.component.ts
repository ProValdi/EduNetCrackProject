import {Component, ViewChild, AfterViewInit, ViewEncapsulation, OnInit, SimpleChange} from '@angular/core';

import {jqxTreeComponent} from 'jqwidgets-ng/jqxtree';
import {jqxTreeGridComponent} from 'jqwidgets-ng/jqxtreegrid';
import {jqxMenuComponent} from 'jqwidgets-ng/jqxmenu';
import {TagService} from "../../services/tag-service/tag.service";
import {Tag} from "../../model/entity/tag";
import {LearnRequestBody} from "../../model/entity/learn-request-body";
import {AppComponent} from "../app/app.component";

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
        this.setExpandedChild(allTags, tag.children);
      }
      this.dataAdapter = new jqx.dataAdapter(this.getSource());
      this.dataAdapter.localData = this.tags;
    });
  }

  setExpandedChild(allTags: any[], tags: any): void {
    console.log(tags);
    if(!tags.length)
      return;
    for (let node of tags) {
      node.children = allTags.filter(x => x.parentId === node.id);
      node.expanded = false;
      this.setExpandedChild(allTags, node.children);
    }
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
    console.log("editing tag \'" + this.oldCellName + "\'");
    console.log(event.args);
  }

  treeGridOnCellEndEdit(event: any): void {
    let args = event.args;
    let rowKey = args.key; // id of tag
    let rowData = args.row; // all internal data of tag
    let value = args.value; // title of selected tag
    
    
    console.log(value);
    
    if (this.oldCellName === value || value == "") {
      console.log("no changes");
      return;
    }
    
    this.tagService.getById(rowKey).subscribe(tag => {
      tag.title = value;
      this.tagService.update(tag).subscribe(_ => {
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

  tagNumbers: number[];
  
  deleteChild(array: any[]): void {
    for (let i = 0; i < array.length; i++) {
      this.tagService.delete(array[i].id).subscribe();
      if (array[i].children.length == 0)
        return;
      this.deleteChild(array[i].children);
    }
  }
  
  myMenuOnItemClick(event: any): void {
    let args = event.args;
    let selection = this.myTreeGrid.getSelection();
    let rowid = selection[0].uid;

    switch (args.innerHTML) { 
      case 'Delete Selected Row':
        
        let selection = this.myTreeGrid.getSelection();
        
        if (selection.length > 1) {
          for (let i = 0; i < selection.length; i++) {
            let key = this.myTreeGrid.getKey(selection[i]);
            this.myTreeGrid.deleteRow(key);
          }
        }
        else {
          this.myTreeGrid.deleteRow(rowid);
        }

        this.deleteChild(selection);
        
        break;
      case 'Add new Row':
        
        this.tagService.getLastId().subscribe( value => {
          this.tagService.getById(rowid).subscribe(tag => {
            
            tag.parentId = rowid;
            tag.id = parseInt(String(value)) + 1;
            this.tagService.create(tag).subscribe();
            this.myTreeGrid.expandRow(rowid);
            this.myTreeGrid.addRow(value + 1, {}, 'first', rowid);
            this.myTreeGrid.clearSelection();
            this.myTreeGrid.selectRow(value + 1);
            this.myTreeGrid.beginRowEdit(value + 1);
          });
        });

        break;
    }
  };
  
}
