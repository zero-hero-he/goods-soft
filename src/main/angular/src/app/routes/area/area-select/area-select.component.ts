import { Country } from './../country';
import { City } from './../city';
import { Component, OnInit } from '@angular/core';
import { Province } from '../province';
import { AreaService } from '../area.service';

@Component({
  selector: 'app-area-select',
  templateUrl: './area-select.component.html',
  styles: [],
})
export class AreaSelectComponent implements OnInit {
  layout = '';

  provinceValue: string = null;
  cityValue: string = null;
  countryValue: string = null;

  provinces: Province[] = [];
  citys: City[] = [];
  countrys: Country[] = [];
  constructor(private areaService: AreaService) {}

  ngOnInit() {
    this.getProvinces();
    console.log('=============', this.layout);
  }

  getProvinces() {
    this.areaService.getProvinces().subscribe(res => {
      this.provinces = res.data;
    });
  }

  provinceChange(value: { label: string; value: string; idx: number }): void {
    this.areaService.getCitys(value).subscribe(res => {
      this.citys = res.data;
    });
  }
  cityChange(value: { label: string; value: string; idx: number }): void {
    this.areaService.getCountrys(value).subscribe(res => {
      this.countrys = res.data;
    });
  }
  countryChange(value: { label: string; value: string; idx: number }): void {}
}
