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

const COMPONENTS = [ProBrandComponent, ProChannelComponent, AreaSelectComponent];
const COMPONENTS_NOROUNT = [
  ProBrandEditComponent,
  ProBrandViewComponent,
  ProChannelEditComponent,
  ProChannelViewComponent,
];

@NgModule({
  imports: [SharedModule, ProRoutingModule],
  declarations: [...COMPONENTS, ...COMPONENTS_NOROUNT],
  entryComponents: COMPONENTS_NOROUNT,
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class ProModule {}
