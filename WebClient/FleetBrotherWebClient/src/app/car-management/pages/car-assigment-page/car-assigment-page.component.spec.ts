import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarAssigmentPageComponent } from './car-assigment-page.component';

describe('CarAssigmentPageComponent', () => {
  let component: CarAssigmentPageComponent;
  let fixture: ComponentFixture<CarAssigmentPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CarAssigmentPageComponent]
    });
    fixture = TestBed.createComponent(CarAssigmentPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
