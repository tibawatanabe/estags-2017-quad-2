import { TestBed, inject } from '@angular/core/testing';

import { UserInfoService } from './user-info.service';

describe('UsersInfoService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UserInfoService]
    });
  });

  it('should ...', inject([UserInfoService], (service: UserInfoService) => {
    expect(service).toBeTruthy();
  }));
});
