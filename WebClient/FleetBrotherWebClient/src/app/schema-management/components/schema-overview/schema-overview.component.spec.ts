import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SchemaOverviewComponent } from './schema-overview.component';

describe('SchemaOverviewComponent', () => {
  let component: SchemaOverviewComponent;
  let fixture: ComponentFixture<SchemaOverviewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SchemaOverviewComponent]
    });
    fixture = TestBed.createComponent(SchemaOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
