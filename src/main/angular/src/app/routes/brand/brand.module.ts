import { NgModule } from '@angular/core';
import { SharedModule } from '@shared';
import { BrandRoutingModule } from './brand-routing.module';
import { BrandListComponent } from './list/list.component';

const COMPONENTS = [BrandListComponent];
const COMPONENTS_NOROUNT = [];

@NgModule({
  imports: [SharedModule, BrandRoutingModule],
  declarations: [...COMPONENTS, ...COMPONENTS_NOROUNT],
  entryComponents: COMPONENTS_NOROUNT,
})
export class BrandModule {}
