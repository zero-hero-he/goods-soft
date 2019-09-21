import { Component, OnInit, ViewChild } from '@angular/core';
import { NzModalRef, NzMessageService } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { SFSchema, SFUISchema, SFSchemaEnum, SFComponent } from '@delon/form';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-pro-product-edit',
  templateUrl: './edit.component.html',
})
export class ProProductEditComponent implements OnInit {
  url = '/product/update';
  modelName = '编辑';
  record: any = {};
  isSpinning = true;

  private defaultBrandsEnum = new BehaviorSubject<any>({});

  @ViewChild('sf', { static: false }) sf: SFComponent;
  schema: SFSchema = {
    properties: {
      id: { type: 'number', title: 'ID' },
      name: { type: 'string', title: '产品名称', minLength: 1, maxLength: 30 },
      brandId: { type: 'string', title: '品牌' },
      model: { type: 'string', title: '型号', minLength: 1, maxLength: 40 },
      specification: { type: 'string', title: '规格', minLength: 1, maxLength: 30 },
      no: { type: 'string', title: '产品编码', minLength: 1, maxLength: 30 },
    },
    required: ['name', 'brandId'],
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
    $model: {
      widget: 'string',
    },
    $specification: {
      widget: 'string',
    },
    $no: {
      widget: 'custom',
    },
    $brandId: {
      widget: 'select',
      notFoundContent: '更换关键字搜索',
      allowClear: true,
      showSearch: true,
      serverSearch: true,
      onSearch: (text: string) => this.brandChange(text),
      asyncData: () => this.defaultBrandsEnum.asObservable(),
    },
  };

  brandChange(text: string): Promise<SFSchemaEnum[]> {
    const brandSearch = {
      name: text === null ? '' : text,
      pi: 0,
      ps: 50,
    };
    return this.http
      .get('/brand/get/name', brandSearch)
      .toPromise()
      .then(res => {
        const returnData = Array(res.data.list.length)
          .fill({})
          .map((item: any, idx: number) => {
            const iData: any = res.data.list[idx];
            iData.label = iData.name;
            iData.value = iData.id;
            return iData;
          });
        return returnData;
      });
  }

  constructor(private modal: NzModalRef, private msgSrv: NzMessageService, public http: _HttpClient) {}

  async ngOnInit() {
    if (this.record.pageType === 'add') {
      this.modelName = '新增';
      this.isSpinning = false;
      this.url = '/product/add';
      return;
    }
    this.schema.properties.no.readOnly = true;
    if (this.record.id > 0)
      await this.http.get(`/product/get/${this.record.id}`).subscribe(async res => {
        this.record = res.data;
        if (this.record !== null) {
          this.record.brandId = this.record.brand.id;
          await this.setDefaultBrandsEnum(this.record.brandId);
        }
        this.isSpinning = false;
      });
  }

  async setDefaultBrandsEnum(id) {
    await this.http.get(`/brand/get/${id}`).subscribe(async res => {
      this.defaultBrandsEnum.next([{ label: res.data.name, value: res.data.id }]);
    });
  }

  save(value: any) {
    const data = JSON.parse(JSON.stringify(value));
    data.brand = { id: data.brandId };
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

  getNo() {
    console.log('getNo', this.record.pageType);
    if (this.record.pageType !== 'add') {
      return;
    }
    this.http.get(`/tool/no`).subscribe(res => {
      this.sf.setValue('/no', res.data);
    });
  }

  close() {
    this.modal.destroy();
  }
}
