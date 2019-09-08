import { NgModule } from '@angular/core';
import { SharedModule } from '@shared';
import { ProRoutingModule } from './pro-routing.module';
import { ProBrandComponent } from './brand/brand.component';
import { ProBrandEditComponent } from './brand/edit/edit.component';
import { ProBrandViewComponent } from './brand/view/view.component';
import { ProChannelComponent } from './channel/channel.component';
import { ProChannelEditComponent } from './channel/edit/edit.component';
import { ProChannelViewComponent } from './channel/view/view.component';

const COMPONENTS = [
  ProBrandComponent,
  ProChannelComponent];
const COMPONENTS_NOROUNT = [
  ProBrandEditComponent,
  ProBrandViewComponent,
  ProChannelEditComponent,
  ProChannelViewComponent];

@NgModule({
  imports: [
    SharedModule,
    ProRoutingModule
  ],
  declarations: [
    ...COMPONENTS,
    ...COMPONENTS_NOROUNT
  ],
  entryComponents: COMPONENTS_NOROUNT
})
export class ProModule { }
