import { Component, OnInit } from '@angular/core';
import { NzModalRef, NzMessageService } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';

@Component({
  selector: 'app-pro-product-view',
  templateUrl: './view.component.html',
})
export class ProProductViewComponent implements OnInit {
  record: any = {};
  i: any;

  constructor(private modal: NzModalRef, public msgSrv: NzMessageService, public http: _HttpClient) {}

  ngOnInit(): void {
    this.http.get(`/product/get/${this.record.id}`).subscribe(res => {
      this.i = res.data;
      if (res.data !== null && res.data.brand !== null) {
        this.i.brandName = res.data.brand.name;
      }
    });
  }

  close() {
    this.modal.destroy();
  }
}
