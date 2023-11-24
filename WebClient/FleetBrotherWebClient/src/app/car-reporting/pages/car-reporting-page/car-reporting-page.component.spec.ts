import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarReportingPageComponent } from './car-reporting-page.component';

describe('CarReportingPageComponent', () => {
  let component: CarReportingPageComponent;
  let fixture: ComponentFixture<CarReportingPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CarReportingPageComponent]
    });
    fixture = TestBed.createComponent(CarReportingPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
