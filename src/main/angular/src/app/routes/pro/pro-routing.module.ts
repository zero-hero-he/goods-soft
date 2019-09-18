import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProBrandComponent } from './brand/brand.component';
import { ProChannelComponent } from './channel/channel.component';
import { ProBatchComponent } from './batch/batch.component';
import { ProProductComponent } from './product/product.component';

const routes: Routes = [

  { path: 'brand', component: ProBrandComponent },
  { path: 'channel', component: ProChannelComponent },
  { path: 'batch', component: ProBatchComponent },
  { path: 'product', component: ProProductComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProRoutingModule { }
