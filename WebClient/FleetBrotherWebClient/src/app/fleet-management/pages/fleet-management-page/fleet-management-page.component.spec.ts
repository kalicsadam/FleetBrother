import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FleetManagementPageComponent } from './fleet-management-page.component';

describe('FleetManagementPageComponent', () => {
  let component: FleetManagementPageComponent;
  let fixture: ComponentFixture<FleetManagementPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FleetManagementPageComponent]
    });
    fixture = TestBed.createComponent(FleetManagementPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
