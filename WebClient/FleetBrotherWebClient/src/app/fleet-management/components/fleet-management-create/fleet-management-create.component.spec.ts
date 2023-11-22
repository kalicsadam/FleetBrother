import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FleetManagementCreateComponent } from './fleet-management-create.component';

describe('FleetManagementCreateComponent', () => {
  let component: FleetManagementCreateComponent;
  let fixture: ComponentFixture<FleetManagementCreateComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FleetManagementCreateComponent]
    });
    fixture = TestBed.createComponent(FleetManagementCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
