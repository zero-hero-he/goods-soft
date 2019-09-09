import { Country } from './country';
import { City } from './city';
import { Injectable } from '@angular/core';
import { _HttpClient } from '@delon/theme';
import { Province } from './province';

@Injectable({
  providedIn: 'root',
})
export class AreaService {
  constructor(private http: _HttpClient) {}

  getProvinces(): Province[] {
    const url = '/area/province';
    const params = '';
    return this.get(url, params);
  }

  getProvince(provinceId: any): Province {
    const url = '/area/province/' + provinceId;
    const params = '';
    return this.get(url, params);
  }

  getCitys(provinceId: any): City[] {
    const url = '/area/citys/' + provinceId;
    const params = '';
    return this.get(url, params);
  }

  getCity(cityId: any): City {
    const url = '/area/city/' + cityId;
    const params = '';
    return this.get(url, params);
  }

  getCountrys(cityId: any): Country[] {
    const url = '/area/countrys/' + cityId;
    const params = '';
    return this.get(url, params);
  }

  getCountry(countId: any): Country {
    const url = '/area/country/' + countId;
    const params = '';
    return this.get(url, params);
  }

  get(url: string, params: any) {
    let data: any;
    this.http.get(url, params).subscribe(res => {
      data = res.data;
    });
    return data;
  }
}
