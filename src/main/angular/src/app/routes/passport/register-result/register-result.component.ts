import { Component } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'passport-register-result',
  templateUrl: './register-result.component.html',
})
export class UserRegisterResultComponent {
  params = { username: '' };
  username = '';
  constructor(route: ActivatedRoute, public msg: NzMessageService) {
    this.params.username = this.username = route.snapshot.queryParams.username || 'songwen';
  }
}
