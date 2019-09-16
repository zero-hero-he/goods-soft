import { Component, OnInit } from '@angular/core';
import { NzModalRef, NzMessageService } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { SFSchema, SFUISchema } from '@delon/form';

@Component({
  selector: 'app-pro-batch-edit',
  templateUrl: './edit.component.html',
})
export class ProBatchEditComponent implements OnInit {
  url = '/batch/update';
  modelName = '编辑';
  record: any = {};
  isSpinning = true;

  schema: SFSchema = {
    properties: {
      id: { type: 'number', title: 'ID' },
      name: { type: 'string', title: '批次名称', minLength: 1, maxLength: 64 },
      batchId: { type: 'string', title: '批次号', minLength: 1, maxLength: 30 },
    },
    required: ['name', 'batchId'],
  };
  ui: SFUISchema = {
    '*': {
      spanLabelFixed: 100,
      grid: { span: 12 },
    },
    $id: {
      hidden: true,
    },
    $name: {
      widget: 'string',
    },
    $batchId: {
      widget: 'string',
    },
  };

  constructor(private modal: NzModalRef, private msgSrv: NzMessageService, public http: _HttpClient) {}

  ngOnInit(): Promise<void> {
    if (this.record.pageType === 'add') {
      this.modelName = '新增';
      this.isSpinning = false;
      this.url = '/batch/add';
      return;
    }
    if (this.record.id > 0)
      this.http.get(`/batch/get/${this.record.id}`).subscribe(async res => {
        this.record = res.data;
        this.isSpinning = false;
      });
  }

  save(value: any) {
    const data = JSON.parse(JSON.stringify(value));

    if (this.record.pageType === 'add') {
      this.http.put(`${this.url}`, data).subscribe(res => {
        this.msgSrv.success('保存成功');
        this.modal.close(true);
      });
    } else {
      this.http.post(`${this.url}`, data).subscribe(res => {
        this.msgSrv.success('更新成功');
        this.modal.close(true);
      });
    }
  }

  close() {
    this.modal.destroy();
  }
}
