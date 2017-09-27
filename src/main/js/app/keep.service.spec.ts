import { TestBed, inject } from '@angular/core/testing';

import { KeepService } from './keep.service';

describe('KeepService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [KeepService]
    });
  });

  it('should be created', inject([KeepService], (service: KeepService) => {
    expect(service).toBeTruthy();
  }));
});
