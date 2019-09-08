import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProBrandComponent } from './brand/brand.component';
import { ProChannelComponent } from './channel/channel.component';

const routes: Routes = [

  { path: 'brand', component: ProBrandComponent },
  { path: 'channel', component: ProChannelComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProRoutingModule { }
