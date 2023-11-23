import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarFleetAssigmentComponent } from './car-fleet-assigment.component';

describe('CarFleetAssigmentComponent', () => {
  let component: CarFleetAssigmentComponent;
  let fixture: ComponentFixture<CarFleetAssigmentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CarFleetAssigmentComponent]
    });
    fixture = TestBed.createComponent(CarFleetAssigmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
