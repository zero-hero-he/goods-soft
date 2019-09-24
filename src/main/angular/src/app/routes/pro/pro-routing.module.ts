import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProBrandComponent } from './brand/brand.component';
import { ProChannelComponent } from './channel/channel.component';
import { ProBatchComponent } from './batch/batch.component';
import { ProProductComponent } from './product/product.component';
import { ProPriceComponent } from './price/price.component';
import { ProHistoryPriceComponent } from './history-price/history-price.component';

const routes: Routes = [
  { path: 'brand', component: ProBrandComponent },
  { path: 'channel', component: ProChannelComponent },
  { path: 'batch', component: ProBatchComponent },
  { path: 'product', component: ProProductComponent },
  { path: 'price', component: ProPriceComponent },
  { path: 'history-price', component: ProHistoryPriceComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ProRoutingModule {}
