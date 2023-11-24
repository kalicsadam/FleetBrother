import { TestBed } from '@angular/core/testing';

import { SchemaManagementService } from './schema-management.service';

describe('SchemaManagementService', () => {
  let service: SchemaManagementService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SchemaManagementService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
