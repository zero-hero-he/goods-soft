import { ProProductEditComponent } from './../product/edit/edit.component';
import { ProPriceViewComponent } from './view/view.component';
import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { _HttpClient, ModalHelper } from '@delon/theme';
import { STColumn, STComponent, STPage, STData, STChange } from '@delon/abc';
import { SFSchema } from '@delon/form';
import { tap } from 'rxjs/operators';
import { ProPriceEditComponent } from './edit/edit.component';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';

@Component({
  selector: 'app-pro-price',
  templateUrl: './price.component.html',
})
export class ProPriceComponent implements OnInit {
  q: any = {
    productIds: [],
    channelIds: [],
    no: '',
  };

  products = [];
  channels = [];

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
    { title: '', index: 'num', type: 'no' },
    { title: 'ID', index: 'id', type: 'number', iif: () => false },
    {
      title: '产品',
      index: 'product',
      format: (item: STData, col: STColumn, index: number) => {
        console.log('------------', item);
        return '';
      },
    },
    { title: '渠道', index: 'channel' },
    { title: '价格编号', index: 'no' },
    { title: '进货单价', index: 'unitPrice', type: 'number' },
    { title: '进货总价', index: 'sumPrice', type: 'number' },
    { title: '零售价', index: 'retailPrice', type: 'number' },
    { title: '数量', index: 'count', type: 'number' },
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
          component: ProPriceViewComponent,
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
          component: ProProductEditComponent,
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
    console.log('======', this.q);
    if (this.q.no === null) {
      this.q.no = '';
    }
    this.http
      .get('/price/get', this.q)
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
        this.http.delete(`/price/delete/${this.selectedRows.map(i => i.id)}`).subscribe((res: any) => {
          console.log(res);
          this.msg.success(`删除了 ${res.data} 笔`);
          this.getData();
          this.st.clearCheck();
        }),
      nzCancelText: '再想想',
    });
  }

  add() {
    this.modal.createStatic(ProPriceEditComponent, { record: { pageType: 'add' } }).subscribe(res => {
      this.getData();
    });
  }

  reset() {
    // wait form reset updated finished
    setTimeout(() => this.getData());
  }

  searchProduct(value: { label: string; value: string; idx: number }): void {
    const productSearch = {
      name: value === null ? '' : value,
      pi: 0,
      ps: 50,
    };
    this.http.get('/product/name', productSearch).subscribe(res => {
      this.products = res.data;
    });
  }

  searchChannel(value: { label: string; value: string; idx: number }): void {
    const channelSearch = {
      name: value === null ? '' : value,
      pi: 0,
      ps: 50,
    };
    this.http.get('/channel/get/name', channelSearch).subscribe(res => {
      this.channels = res.data.list;
    });
  }

  constructor(
    private http: _HttpClient,
    public msg: NzMessageService,
    private modalSrv: NzModalService,
    private cdr: ChangeDetectorRef,
    private modal: ModalHelper,
  ) {}

  ngOnInit() {
    setTimeout(() => {
      // this.getData();
      this.searchProduct(null);
      this.searchChannel(null);
    });
  }
}
