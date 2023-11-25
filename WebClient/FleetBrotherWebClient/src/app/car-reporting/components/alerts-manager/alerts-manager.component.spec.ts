import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlertsManagerComponent } from './alerts-manager.component';

describe('AlertsManagerComponent', () => {
  let component: AlertsManagerComponent;
  let fixture: ComponentFixture<AlertsManagerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AlertsManagerComponent]
    });
    fixture = TestBed.createComponent(AlertsManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
