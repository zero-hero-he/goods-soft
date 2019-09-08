import { Component, OnInit, ViewChild, TemplateRef, ChangeDetectionStrategy, ChangeDetectorRef } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { tap, map } from 'rxjs/operators';
import { STComponent, STColumn, STData, STChange, STPage } from '@delon/abc';
import { NzPopoverModule } from 'ng-zorro-antd/popover';
import { NzToolTipModule } from 'ng-zorro-antd/tooltip';

@Component({
  selector: 'app-pro-brand',
  templateUrl: './brand.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ProBrandComponent implements OnInit {
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

  addData: any = {
    name: '',
    fullName: '',
    contact: '',
    province: {},
    city: {},
    country: {},
    address: '',
    note: '',
    images: [],
  };

  @ViewChild('st', { static: false }) st: STComponent;

  columns: STColumn[] = [
    { title: '', index: 'key', type: 'checkbox' },
    { title: '', index: 'no', type: 'no' },
    // { title: '', type: 'checkbox' },
    { title: 'ID', index: 'id', type: 'number', iif: () => false },
    { title: '简称', index: 'name' },
    { title: '品牌全称', index: 'fullName' },
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
  ];

  getData() {
    this.loading = true;
    if (this.q.name === null) {
      this.q.name = '';
    }
    this.http
      .get('/brand/get/name', this.q)
      .pipe(tap(() => (this.loading = false)))
      .subscribe(res => {
        // this.data = res.data.list;
        this.data = Array(res.data.list.length)
          .fill({})
          .map((item: any, idx: number) => {
            const iData: any = res.data.list[idx];
            iData.noteShort =
              iData.note !== null && iData.note.length > 20 ? iData.note.substr(20) + '...' : iData.note;
            console.log('-------------', iData);

            return iData;
          });
        // this.data.forEach(i => {
        //   i.noteShort = i.note !== null && i.note.length > 20 ? i.note.substr(20) + '...' : i.note;
        // });
        console.log(this.data);
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
    this.http.delete('/brand/delete', { nos: this.selectedRows.map(i => i.no).join(',') }).subscribe((res: any) => {
      console.log(res);
      this.msg.success(`删除了 ${res.data} 笔`);
      this.getData();
      this.st.clearCheck();
    });
  }

  add(tpl: TemplateRef<{}>) {
    this.modalSrv.create({
      nzTitle: '新建品牌',
      nzContent: tpl,
      nzOnOk: () => {
        this.loading = true;
        this.http.put('/brand/add', this.addData).subscribe(() => this.getData());
      },
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
  ) {}

  ngOnInit() {
    this.getData();
  }
}
