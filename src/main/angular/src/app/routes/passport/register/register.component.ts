import { Component, OnDestroy, Injector } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { NzMessageService, NzNotificationService } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';

@Component({
  selector: 'passport-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.less'],
})
export class UserRegisterComponent implements OnDestroy {
  constructor(
    fb: FormBuilder,
    private router: Router,
    public http: _HttpClient,
    public msg: NzMessageService,
    private injector: Injector,
  ) {
    this.form = fb.group({
      username: [null, [Validators.required, Validators.minLength(4), Validators.maxLength(16)]],
      firstName: [null, [Validators.required, Validators.minLength(1), Validators.maxLength(50)]],
      lastName: [null, [Validators.required, Validators.minLength(1), Validators.maxLength(50)]],
      email: [null, [Validators.required, Validators.email]],
      password: [
        null,
        [
          Validators.required,
          Validators.minLength(6),
          Validators.maxLength(20),
          UserRegisterComponent.checkPassword.bind(this),
        ],
      ],
      confirm: [null, [Validators.required, Validators.minLength(6), UserRegisterComponent.passwordEquar]],
      // mobilePrefix: ['+86'],
      // mobile: [null, [Validators.required, Validators.pattern(/^1\d{10}$/)]],
      // captcha: [null, [Validators.required]],
    });
  }

  private get notification(): NzNotificationService {
    return this.injector.get(NzNotificationService);
  }

  // #region fields

  get email() {
    return this.form.controls.mail;
  }
  get password() {
    return this.form.controls.password;
  }
  get confirm() {
    return this.form.controls.confirm;
  }
  get username() {
    return this.form.controls.username;
  }

  get firstName() {
    return this.form.controls.firstName;
  }

  get lastName() {
    return this.form.controls.lastName;
  }
  // get mobile() {
  //   return this.form.controls.mobile;
  // }
  // get captcha() {
  //   return this.form.controls.captcha;
  // }
  form: FormGroup;
  error = '';
  type = 0;
  visible = false;
  status = 'pool';
  progress = 0;
  passwordProgressMap = {
    ok: 'success',
    pass: 'normal',
    pool: 'exception',
  };

  // #endregion

  // #region get captcha

  count = 0;
  interval$: any;

  static checkPassword(control: FormControl) {
    if (!control) return null;
    const self: any = this;
    self.visible = !!control.value;
    if (control.value && control.value.length > 9) {
      self.status = 'ok';
    } else if (control.value && control.value.length > 5) {
      self.status = 'pass';
    } else {
      self.status = 'pool';
    }

    if (self.visible) {
      self.progress = control.value.length * 10 > 100 ? 100 : control.value.length * 10;
    }
  }

  static passwordEquar(control: FormControl) {
    if (!control || !control.parent) {
      return null;
    }
    if (control.value !== control.parent.get('password')!.value) {
      return { equar: true };
    }
    return null;
  }

  // getCaptcha() {
  //   if (this.mobile.invalid) {
  //     this.mobile.markAsDirty({ onlySelf: true });
  //     this.mobile.updateValueAndValidity({ onlySelf: true });
  //     return;
  //   }
  //   this.count = 59;
  //   this.interval$ = setInterval(() => {
  //     this.count -= 1;
  //     if (this.count <= 0) clearInterval(this.interval$);
  //   }, 1000);
  // }

  // #endregion

  submit() {
    this.error = '';
    Object.keys(this.form.controls).forEach(key => {
      this.form.controls[key].markAsDirty();
      this.form.controls[key].updateValueAndValidity();
    });
    console.log(this.form);
    if (this.form.invalid) {
      console.log('data');
      return;
    }

    const data = this.form.value;
    console.log(data);
    this.http.post('/auth/register?_allow_anonymous=true', data).subscribe((res: any) => {
      console.log(data);
      if (res.resultCode !== '000') {
        this.error = res.resultMsg;
        return;
      }
      this.router.navigateByUrl('/passport/register-result', {
        queryParams: { username: data.username },
      });
    });
  }

  ngOnDestroy(): void {
    if (this.interval$) clearInterval(this.interval$);
  }
}
