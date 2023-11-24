import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SchemaManagementPageComponent } from './schema-management-page.component';

describe('SchemaManagementPageComponent', () => {
  let component: SchemaManagementPageComponent;
  let fixture: ComponentFixture<SchemaManagementPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SchemaManagementPageComponent]
    });
    fixture = TestBed.createComponent(SchemaManagementPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
