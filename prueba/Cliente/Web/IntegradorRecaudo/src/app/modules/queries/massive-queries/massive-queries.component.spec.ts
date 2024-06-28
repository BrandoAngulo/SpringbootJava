import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MassiveQueriesComponent } from './massive-queries.component';

describe('MassiveQueriesComponent', () => {
  let component: MassiveQueriesComponent;
  let fixture: ComponentFixture<MassiveQueriesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MassiveQueriesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MassiveQueriesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
