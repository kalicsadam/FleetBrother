import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlertCreationComponent } from './alert-creation.component';

describe('AlertCreationComponent', () => {
  let component: AlertCreationComponent;
  let fixture: ComponentFixture<AlertCreationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AlertCreationComponent]
    });
    fixture = TestBed.createComponent(AlertCreationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
