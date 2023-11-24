import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FieldCreationComponent } from './field-creation.component';

describe('FieldCreationComponent', () => {
  let component: FieldCreationComponent;
  let fixture: ComponentFixture<FieldCreationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FieldCreationComponent]
    });
    fixture = TestBed.createComponent(FieldCreationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
