import { TestBed } from '@angular/core/testing';

import { AlertsManagementService } from './alerts-management.service';

describe('AlertsManagementService', () => {
  let service: AlertsManagementService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AlertsManagementService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
