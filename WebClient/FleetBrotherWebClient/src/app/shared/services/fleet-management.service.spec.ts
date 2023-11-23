import { TestBed } from '@angular/core/testing';

import { FleetManagementService } from './fleet-management.service';

describe('FleetManagementService', () => {
  let service: FleetManagementService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FleetManagementService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
