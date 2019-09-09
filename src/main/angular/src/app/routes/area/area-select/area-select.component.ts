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

  provinces: Province[];
  constructor(private areaService: AreaService) {}

  ngOnInit() {
    return this.areaService.getProvinces();
  }

  getProvinces(): Province[] {
    return this.areaService.getProvinces();
  }

  provinceChange(value: { label: string; value: string; age: number }): void {}
}
