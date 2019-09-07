import { NgModule } from '@angular/core';
import { SharedModule } from '@shared';
import { ChannelRoutingModule } from './channel-routing.module';
import { ChannelListComponent } from './list/list.component';

const COMPONENTS = [ChannelListComponent];
const COMPONENTS_NOROUNT = [];

@NgModule({
  imports: [SharedModule, ChannelRoutingModule],
  declarations: [...COMPONENTS, ...COMPONENTS_NOROUNT],
  entryComponents: COMPONENTS_NOROUNT,
})
export class ChannelModule {}
