import { ProBatchEditComponent } from './edit/edit.component';
import { ProBatchViewComponent } from './view/view.component';
import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { _HttpClient, ModalHelper } from '@delon/theme';
import { STColumn, STComponent, STData, STPage, STChange } from '@delon/abc';
import { tap } from 'rxjs/operators';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';

@Component({
  selector: 'app-pro-batch',
  templateUrl: './batch.component.html',
})
export class ProBatchComponent implements OnInit {
  q: any = {
    name: '',
    batchId: '',
  };

  total = 0;

  data: any[] = [];
  loading = false;

  pages: STPage = {
    show: true,
    front: false,
    zeroIndexed: true,
  };

  selectedRows: STData[] = [];

  addData: any = {
    name: '',
    batchId: '',
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
            return { record };
          },
          click: () => {
            this.getData();
          },
        },
        {
          text: '编辑',
          type: 'static',
          component: ProBatchEditComponent,
          params: (record: STData) => {
            record.pageType = 'edit';
            return { record };
          },
          click: () => {
            this.getData();
          },
        },
      ],
    },
  ];

  getData() {
    this.loading = true;
    if (this.q.name === null) {
      this.q.name = '';
    }
    if (this.q.batchId === null) {
      this.q.batchId = '';
    }
    this.http
      .get('/batch/get', this.q)
      .pipe(tap(() => (this.loading = false)))
      .subscribe(res => {
        this.data = res.data.list;
        this.total = res.data.total;
        this.cdr.detectChanges();
      });
  }

  stChange(e: STChange) {
    switch (e.type) {
      case 'checkbox':
        this.selectedRows = e.checkbox!;
        this.cdr.detectChanges();
        break;
      case 'filter':
        this.getData();
        break;
    }
  }

  stError(err: any) {
    console.error(err);
  }

  remove() {
    this.modalSrv.confirm({
      nzTitle: '确认删除选择项?',
      // nzContent: '<b style="color: red;">Some descriptions</b>',
      nzOkText: '删除',
      nzOkType: 'danger',
      nzOnOk: () =>
        this.http.delete(`/batch/delete/${this.selectedRows.map(i => i.id)}`).subscribe((res: any) => {
          console.log(res);
          this.msg.success(`删除了 ${res.data} 笔`);
          this.getData();
          this.st.clearCheck();
        }),
      nzCancelText: '再想想',
    });
  }

  add() {
    this.modal.createStatic(ProBatchEditComponent, { record: { pageType: 'add' } }).subscribe(res => {
      this.getData();
    });
  }

  reset() {
    // wait form reset updated finished
    setTimeout(() => this.getData());
  }

  constructor(
    private http: _HttpClient,
    public msg: NzMessageService,
    private modalSrv: NzModalService,
    private cdr: ChangeDetectorRef,
    private modal: ModalHelper,
  ) {}

  ngOnInit() {
    this.getData();
  }
}
