import { Component, OnInit, ViewChild, ChangeDetectorRef, ChangeDetectionStrategy } from '@angular/core';
import { _HttpClient, ModalHelper } from '@delon/theme';
import { STColumn, STComponent, STPage, STData, STChange } from '@delon/abc';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { ProChannelEditComponent } from './edit/edit.component';
import { tap } from 'rxjs/operators';
import { ProChannelViewComponent } from './view/view.component';

@Component({
  selector: 'app-pro-channel',
  templateUrl: './channel.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ProChannelComponent implements OnInit {
  q: any = {
    name: '',
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

  @ViewChild('st', { static: false }) st: STComponent;

  columns: STColumn[] = [
    { title: '', index: 'key', type: 'checkbox' },
    { title: '', index: 'no', type: 'no' },
    // { title: '', type: 'checkbox' },
    { title: 'ID', index: 'id', type: 'number', iif: () => false },
    { title: '简称', index: 'name' },
    { title: '渠道全称', index: 'fullName' },
    { title: '联系方式', index: 'contact' },
    {
      title: '区域',
      index: 'province',
      format: (item: STData, col: STColumn, index: number) => {
        let area: any = '';
        if (item.province !== null && item.province.name !== null) {
          area += item.province.name;
        }
        if (item.city !== null && item.city.name !== null) {
          area += ' ' + item.city.name;
        }
        if (item.country !== null && item.country.name !== null) {
          area += ' ' + item.country.name;
        }
        return area;
      },
    },
    { title: '详细地址', index: 'address' },
    {
      title: '备注',
      index: 'note',
      render: 'note',
      width: 10,
    },
    { title: '创建时间', type: 'date', index: 'createTime' },
    { title: '修改时间', type: 'date', index: 'updateTime' },
    {
      title: '',
      buttons: [
        {
          text: '查看',
          type: 'static',
          component: ProChannelViewComponent,
          params: (record: STData) => {
            console.log('查看', record);
            return { record };
          },
          click: () => {
            this.getData();
          },
        },
        {
          text: '编辑',
          type: 'static',
          component: ProChannelEditComponent,
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
    this.http
      .get('/channel/get/name', this.q)
      .pipe(tap(() => (this.loading = false)))
      .subscribe(res => {
        // this.data = res.data.list;
        this.data = Array(res.data.list.length)
          .fill({})
          .map((item: any, idx: number) => {
            const iData: any = res.data.list[idx];
            iData.noteShort =
              iData.note !== null && iData.note.length > 20 ? iData.note.substr(20) + '...' : iData.note;
            return iData;
          });
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
        this.http.delete(`/channel/delete/${this.selectedRows.map(i => i.id)}`).subscribe((res: any) => {
          console.log(res);
          this.msg.success(`删除了 ${res.data} 笔`);
          this.getData();
          this.st.clearCheck();
        }),
      nzCancelText: '再想想',
    });
  }

  add() {
    this.modal.createStatic(ProChannelEditComponent, { record: { pageType: 'add' } }).subscribe(res => {
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

  ngOnInit() {}
}
