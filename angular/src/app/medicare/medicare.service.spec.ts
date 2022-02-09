import { TestBed } from '@angular/core/testing';

import { MedicareService } from './medicare.service';

describe('MedicareService', () => {
  let service: MedicareService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MedicareService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
