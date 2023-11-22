import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FleetOverviewComponent } from './fleet-overview.component';

describe('FleetOverviewComponent', () => {
  let component: FleetOverviewComponent;
  let fixture: ComponentFixture<FleetOverviewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FleetOverviewComponent]
    });
    fixture = TestBed.createComponent(FleetOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
