import { ProBatchViewComponent } from './view/view.component';
import { Component, OnInit, ViewChild } from '@angular/core';
import { _HttpClient, ModalHelper } from '@delon/theme';
import { STColumn, STComponent, STData } from '@delon/abc';
import { SFSchema } from '@delon/form';
import { ProBrandEditComponent } from '../brand/edit/edit.component';

@Component({
  selector: 'app-pro-batch',
  templateUrl: './batch.component.html',
})
export class ProBatchComponent implements OnInit {
  url = `/user`;
  searchSchema: SFSchema = {
    properties: {
      batchId: {
        type: 'string',
        title: '批次号',
      },
      name: {
        type: 'string',
        title: '名称',
      },
    },
  };
  @ViewChild('st', { static: false }) st: STComponent;
  columns: STColumn[] = [
    { title: '', index: 'key', type: 'checkbox' },
    { title: '', index: 'no', type: 'no' },
    { title: 'ID', index: 'id', type: 'number', iif: () => false },
    { title: '批次号', index: 'batchId' },
    { title: '批次名称', index: 'name' },
    { title: '创建时间', type: 'date', index: 'createTime' },
    { title: '修改时间', type: 'date', index: 'updateTime' },
    {
      title: '',
      buttons: [
        {
          text: '查看',
          type: 'static',
          component: ProBatchViewComponent,
          params: (record: STData) => {
            console.log('查看', record);
            return { record };
          },
          click: () => {
            // this.getData();
          },
        },
        {
          text: '编辑',
          type: 'static',
          component: ProBrandEditComponent,
          params: (record: STData) => {
            record.pageType = 'edit';
            return { record };
          },
          click: () => {
            // this.getData();
          },
        },
      ],
    },
  ];

  constructor(private http: _HttpClient, private modal: ModalHelper) {}

  ngOnInit() {}

  add() {
    // this.modal
    //   .createStatic(FormEditComponent, { i: { id: 0 } })
    //   .subscribe(() => this.st.reload());
  }
}
