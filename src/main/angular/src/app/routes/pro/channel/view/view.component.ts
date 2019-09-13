import { Component, OnInit } from '@angular/core';
import { NzModalRef, NzMessageService } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';

@Component({
  selector: 'app-pro-channel-view',
  templateUrl: './view.component.html',
})
export class ProChannelViewComponent implements OnInit {
  record: any = {};
  i: any;

  constructor(private modal: NzModalRef, public msgSrv: NzMessageService, public http: _HttpClient) {}

  ngOnInit(): void {
    this.http.get(`/channel/get/${this.record.id}`).subscribe(res => {
      this.i = res.data;
      this.i.provinceName = this.i.province !== null ? this.i.province.name : '';
      this.i.cityName = this.i.city !== null ? this.i.city.name : '';
      this.i.countryName = this.i.country !== null ? this.i.country.name : '';
    });
  }

  close() {
    this.modal.destroy();
  }
}
