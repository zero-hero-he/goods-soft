import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { SharedModule } from '@shared';
import { ProRoutingModule } from './pro-routing.module';
import { ProBrandComponent } from './brand/brand.component';
import { ProBrandEditComponent } from './brand/edit/edit.component';
import { ProBrandViewComponent } from './brand/view/view.component';
import { ProChannelComponent } from './channel/channel.component';
import { ProChannelEditComponent } from './channel/edit/edit.component';
import { ProChannelViewComponent } from './channel/view/view.component';
import { AreaSelectComponent } from '../area/area-select/area-select.component';
import { ProBatchComponent } from './batch/batch.component';
import { ProBatchEditComponent } from './batch/edit/edit.component';
import { ProBatchViewComponent } from './batch/view/view.component';
import { ProProductComponent } from './product/product.component';
import { ProProductEditComponent } from './product/edit/edit.component';
import { ProProductViewComponent } from './product/view/view.component';
import { ProPriceComponent } from './price/price.component';
import { ProPriceEditComponent } from './price/edit/edit.component';
import { ProPriceViewComponent } from './price/view/view.component';
import { ProHistoryPriceComponent } from './history-price/history-price.component';
import { ProHistoryPriceEditComponent } from './history-price/edit/edit.component';
import { ProHistoryPriceViewComponent } from './history-price/view/view.component';

const COMPONENTS = [
  ProBrandComponent,
  ProChannelComponent,
  AreaSelectComponent,
  ProBatchComponent,
  ProProductComponent,
  ProPriceComponent,
  ProHistoryPriceComponent,
];
const COMPONENTS_NOROUNT = [
  ProBrandEditComponent,
  ProBrandViewComponent,
  ProChannelEditComponent,
  ProChannelViewComponent,
  ProBatchEditComponent,
  ProBatchViewComponent,
  ProProductEditComponent,
  ProProductViewComponent,
  ProPriceEditComponent,
  ProPriceViewComponent,
  ProHistoryPriceEditComponent,
  ProHistoryPriceViewComponent,
];

@NgModule({
  imports: [SharedModule, ProRoutingModule],
  declarations: [...COMPONENTS, ...COMPONENTS_NOROUNT],
  entryComponents: COMPONENTS_NOROUNT,
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class ProModule {}
