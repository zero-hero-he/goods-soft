import { Component, OnInit, ViewChild } from '@angular/core';
import { NzModalRef, NzMessageService } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { of, Observable, BehaviorSubject } from 'rxjs';
import { delay, map } from 'rxjs/operators';
import { SFSchema, SFUISchema, SFSelectWidgetSchema, SFComponent } from '@delon/form';
import { AreaService } from 'src/app/routes/area/area.service';
import { ifStmt } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-pro-brand-edit',
  templateUrl: './edit.component.html',
})
export class ProBrandEditComponent implements OnInit {
  url = '/brand/update';
  modelName = '编辑';
  record: any = {};
  isSpinning = true;
  provinces: Observable<any>;

  provinceDefault = '';
  cityDefault = '';
  countryDefault = '';

  private citys = new BehaviorSubject<any>({});
  private countrys = new BehaviorSubject<any>({});
  @ViewChild('sf', { static: false }) sf: SFComponent;
  schema: SFSchema = {
    properties: {
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
      // images: { type: 'string', title: '区' },
    },
    required: ['name', 'fullName'],
  };
  ui: SFUISchema = {
    '*': {
      spanLabelFixed: 100,
      grid: { span: 12 },
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
      asyncData: () => this.getProvinces(),
      change: (ngModel: any) => this.provinceChange(ngModel),
    },
    $city: {
      widget: 'select',
      allowClear: true,
      // asyncData: () => this.citys,
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

  getProvinces() {
    return this.areaService.getProvinces().pipe(
      map(res => {
        const returnData = { children: [], label: '省', group: true };
        returnData.children = Array(res.data.length)
          .fill({})
          .map((item: any, idx: number) => {
            const iData: any = res.data[idx];
            iData.label = iData.name;
            iData.value = iData.provinceId;
            return iData;
          });
        return [returnData];
      }),
    );
  }

  provinceChange(value: any): void {
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

  cityChange(value: any): void {
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

  ngOnInit(): void {
    console.log(this.record);
    if (this.record.pageType === 'add') {
      this.modelName = '新增';
      this.isSpinning = false;
      this.url = '/brand/add';
      return;
    }
    if (this.record.id > 0)
      this.http.get(`/brand/get/${this.record.id}`).subscribe(res => {
        this.record = res.data;
        this.provinceDefault =
          this.record === null || this.record.province === null ? '' : this.record.province.provinceId;
        this.cityDefault = this.record === null || this.record.city === null ? '' : this.record.city.cityId;
        this.countryDefault = this.record === null || this.record.country === null ? '' : this.record.country.countryId;
        console.log(this.provinceDefault);
        this.sf.getProperty('/province').
        this.isSpinning = false;
      });
  }

  save(value: any) {
    if (this.record.pageType === 'add') {
      this.http.put(`${this.url}`, value).subscribe(res => {
        this.msgSrv.success('保存成功');
        this.modal.close(true);
      });
    } else {
      this.http.post(`${this.url}/${this.record.id}`, value).subscribe(res => {
        this.msgSrv.success('更新成功');
        this.modal.close(true);
      });
    }
  }

  close() {
    this.modal.destroy();
  }
}
