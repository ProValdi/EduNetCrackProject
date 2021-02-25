import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-tag-tree',
  templateUrl: './tag-tree.component.html',
  styleUrls: ['./tag-tree.component.less']
})
export class TagTreeComponent implements OnInit {
  
  
  
  constructor() {

  }

  ngOnInit(): void {
    
      // $('#tree').fancytree({
      //   source: [
      //     {title: "Node 1", key: "1"},
      //     {title: "Folder 2", key: "2", folder: true, children: [
      //         {title: "Node 2.1", key: "3"},
      //         {title: "Node 2.2", key: "4"}
      //       ]}
      //   ]
      // });
    
  }

}
