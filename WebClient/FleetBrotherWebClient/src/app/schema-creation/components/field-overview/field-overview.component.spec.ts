import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FieldOverviewComponent } from './field-overview.component';

describe('FieldOverviewComponent', () => {
  let component: FieldOverviewComponent;
  let fixture: ComponentFixture<FieldOverviewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FieldOverviewComponent]
    });
    fixture = TestBed.createComponent(FieldOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
