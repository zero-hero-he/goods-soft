import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { NzModalRef, NzMessageService } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { BehaviorSubject } from 'rxjs';
import { SFSchema, SFUISchema } from '@delon/form';
import { AreaService } from 'src/app/routes/area/area.service';

@Component({
  selector: 'app-pro-brand-edit',
  templateUrl: './edit.component.html',
})
export class ProBrandEditComponent implements OnInit, AfterViewInit {
  url = '/brand/update';
  modelName = '编辑';
  record: any = {};
  isSpinning = true;

  private provinces = new BehaviorSubject<any>({});
  private citys = new BehaviorSubject<any>({});
  private countrys = new BehaviorSubject<any>({});
  schema: SFSchema = {
    properties: {
      id: { type: 'number', title: 'ID' },
      name: { type: 'string', title: '品牌简称', minLength: 1, maxLength: 64 },
      fullName: { type: 'string', title: '品牌全称', minLength: 1, maxLength: 254 },
      province: {
        type: 'string',
        title: '省',
      },
      city: {
        type: 'string',
        title: '市',
      },
      country: {
        type: 'string',
        title: '区',
      },
      address: { type: 'string', title: '详细地址' },
      note: { type: 'string', title: '备注' },
    },
    required: ['name', 'fullName'],
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
    $fullName: {
      widget: 'string',
    },
    $address: {
      widget: 'string',
    },
    $province: {
      widget: 'select',
      allowClear: true,
      asyncData: () => this.provinces.asObservable(),
      change: (ngModel: any) => this.provinceChange(ngModel),
    },
    $city: {
      widget: 'select',
      allowClear: true,
      asyncData: () => this.citys.asObservable(),
      change: (ngModel: any) => this.cityChange(ngModel),
    },
    $country: {
      widget: 'select',
      allowClear: true,
      asyncData: () => this.countrys.asObservable(),
      change: (ngModel: any) => this.countryChange(ngModel),
    },
    $note: {
      widget: 'textarea',
      grid: { span: 24 },
    },
  };

  async getProvinces() {
    this.areaService.getProvinces().subscribe(res => {
      const returnData = Array(res.data.length)
        .fill({})
        .map((item: any, idx: number) => {
          const iData: any = res.data[idx];
          iData.label = iData.name;
          iData.value = iData.provinceId;
          return iData;
        });
      this.provinces.next([{ children: returnData, label: '省', group: true }]);
    });
  }

  provinceChange(value: any) {
    this.areaService.getCitys(value).subscribe(res => {
      const returnData = Array(res.data.length)
        .fill({})
        .map((item: any, idx: number) => {
          const iData: any = res.data[idx];
          iData.label = iData.name;
          iData.value = iData.cityId;
          return iData;
        });
      this.citys.next([{ children: returnData, label: '市', group: true }]);
      this.countrys.next([{ children: [], label: '区', group: true }]);
    });
  }

  cityChange(value: any) {
    this.areaService.getCountrys(value).subscribe(res => {
      const returnData = Array(res.data.length)
        .fill({})
        .map((item: any, idx: number) => {
          const iData: any = res.data[idx];
          iData.label = iData.name;
          iData.value = iData.countryId;
          return iData;
        });
      this.countrys.next([{ children: returnData, label: '区', group: true }]);
    });
  }

  countryChange(value: any): void {}

  constructor(
    private modal: NzModalRef,
    private msgSrv: NzMessageService,
    public http: _HttpClient,
    private areaService: AreaService,
  ) {}

  async ngOnInit(): Promise<void> {
    await this.getProvinces();
    if (this.record.pageType === 'add') {
      this.modelName = '新增';
      this.isSpinning = false;
      this.url = '/brand/add';
      return;
    }
    if (this.record.id > 0)
      await this.http.get(`/brand/get/${this.record.id}`).subscribe(async res => {
        this.record = res.data;
        if (this.record !== null && this.record.province !== null) {
          if (this.record.province) {
            await this.provinceChange(this.record.province.provinceId);
            this.record.province = this.record.province.provinceId;
          }
          if (this.record.city) {
            await this.cityChange(this.record.city.cityId);
            this.record.city = this.record.city.cityId;
          }
          if (this.record.country) {
            await this.countryChange(this.record.country.countryId);
            this.record.country = this.record.country.countryId;
          }
        }
        this.isSpinning = false;
      });
  }

  ngAfterViewInit() {}

  save(value: any) {
    const data = JSON.parse(JSON.stringify(value));
    const province: string = value.province;
    const city: string = value.city;
    const country: string = value.country;
    data.province = { provinceId: province };
    data.city = { cityId: city };
    data.country = { countryId: country };

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
