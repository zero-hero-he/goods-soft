import { Injectable } from '@angular/core';
import { _HttpClient } from '@delon/theme';

@Injectable({
  providedIn: 'root',
})
export class AreaService {
  constructor(private http: _HttpClient) {}

  getProvinces() {
    const url = '/area/province';
    const params = '';
    return this.get(url, params);
  }

  getProvince(provinceId: any) {
    const url = '/area/province/' + provinceId;
    const params = '';
    return this.get(url, params);
  }

  getCitys(provinceId: any) {
    const url = '/area/citys/' + provinceId;
    const params = '';
    return this.get(url, params);
  }

  getCity(cityId: any) {
    const url = '/area/city/' + cityId;
    const params = '';
    return this.get(url, params);
  }

  getCountrys(cityId: any) {
    const url = '/area/countrys/' + cityId;
    const params = '';
    return this.get(url, params);
  }

  getCountry(countId: any) {
    const url = '/area/country/' + countId;
    const params = '';
    return this.get(url, params);
  }

  get(url: string, params: any) {
    return this.http.get(url, params);
  }
}
