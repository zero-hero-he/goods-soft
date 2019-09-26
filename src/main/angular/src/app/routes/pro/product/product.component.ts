import { ProProductViewComponent } from './view/view.component';
import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { _HttpClient, ModalHelper } from '@delon/theme';
import { STColumn, STComponent, STPage, STData, STChange } from '@delon/abc';
import { tap } from 'rxjs/operators';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { ProProductEditComponent } from './edit/edit.component';

@Component({
  selector: 'app-pro-product',
  templateUrl: './product.component.html',
})
export class ProProductComponent implements OnInit {
  q: any = {
    name: '',
    model: '',
    specification: '',
    no: '',
    brandId: '',
  };

  brands = [];

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
    { title: '产品编号', index: 'no' },
    { title: '产品名称', index: 'name' },
    { title: '型号', index: 'model' },
    { title: '规格', index: 'specification' },
    {
      title: '渠道',
      index: 'brand',
      format: (item: STData, col: STColumn, index: number) => {
        if (item.brand !== null) {
          return item.brand.name;
        } else {
          return '';
        }
      },
    },
    { title: '创建时间', type: 'date', index: 'createTime' },
    { title: '修改时间', type: 'date', index: 'updateTime' },
    {
      title: '',
      buttons: [
        {
          text: '查看',
          type: 'static',
          component: ProProductViewComponent,
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
    if (this.q.name === null) {
      this.q.name = '';
    }
    if (this.q.model === null) {
      this.q.model = '';
    }
    if (this.q.specification === null) {
      this.q.specification = '';
    }
    if (this.q.no === null) {
      this.q.no = '';
    }
    if (this.q.brandId === null) {
      this.q.brandId = '';
    }
    this.http
      .get('/product/get', this.q)
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
      nzOkText: '删除',
      nzOkType: 'danger',
      nzOnOk: () =>
        this.http.delete(`/product/delete/${this.selectedRows.map(i => i.id)}`).subscribe((res: any) => {
          console.log(res);
          this.msg.success(`删除了 ${res.data} 笔`);
          this.getData();
          this.st.clearCheck();
        }),
      nzCancelText: '再想想',
    });
  }

  add() {
    this.modal.createStatic(ProProductEditComponent, { record: { pageType: 'add' } }).subscribe(res => {
      this.getData();
    });
  }

  reset() {
    // wait form reset updated finished
    setTimeout(() => this.getData());
  }

  searchBrand(value: { label: string; value: string; idx: number }): void {
    const brandSearch = {
      name: value === null ? '' : value,
      pi: 0,
      ps: 50,
    };
    this.http.get('/brand/get/name', brandSearch).subscribe(res => {
      this.brands = res.data.list;
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
      this.getData();
      this.searchBrand(null);
    });
  }
}
