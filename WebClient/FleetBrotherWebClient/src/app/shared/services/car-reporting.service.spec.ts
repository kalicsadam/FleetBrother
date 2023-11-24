import { TestBed } from '@angular/core/testing';

import { CarReportingService } from './car-reporting.service';

describe('CarReportingService', () => {
  let service: CarReportingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CarReportingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
