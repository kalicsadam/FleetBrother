import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FleetViewPageComponent } from './fleet-view-page.component';

describe('FleetViewPageComponent', () => {
  let component: FleetViewPageComponent;
  let fixture: ComponentFixture<FleetViewPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FleetViewPageComponent]
    });
    fixture = TestBed.createComponent(FleetViewPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
