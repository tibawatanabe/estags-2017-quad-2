import { Pipe, PipeTransform } from '@angular/core';


@Pipe({ name: 'canShow' })
export class LoginPipe implements PipeTransform {
  transform(link: string) {
    return link + 'blablabla';
  }
}
