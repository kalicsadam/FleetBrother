import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SchemaCreateComponent } from './schema-create.component';

describe('SchemaCreateComponent', () => {
  let component: SchemaCreateComponent;
  let fixture: ComponentFixture<SchemaCreateComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SchemaCreateComponent]
    });
    fixture = TestBed.createComponent(SchemaCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
