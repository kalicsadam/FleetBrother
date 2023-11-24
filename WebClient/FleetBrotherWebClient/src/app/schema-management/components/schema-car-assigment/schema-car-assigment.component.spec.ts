import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SchemaCarAssigmentComponent } from './schema-car-assigment.component';

describe('SchemaCarAssigmentComponent', () => {
  let component: SchemaCarAssigmentComponent;
  let fixture: ComponentFixture<SchemaCarAssigmentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SchemaCarAssigmentComponent]
    });
    fixture = TestBed.createComponent(SchemaCarAssigmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
