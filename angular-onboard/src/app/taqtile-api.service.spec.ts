import { TestBed, inject } from '@angular/core/testing';

import { TaqtileApiService } from './taqtile-api.service';

describe('TaqtileApiService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TaqtileApiService]
    });
  });

  it('should ...', inject([TaqtileApiService], (service: TaqtileApiService) => {
    expect(service).toBeTruthy();
  }));
});
