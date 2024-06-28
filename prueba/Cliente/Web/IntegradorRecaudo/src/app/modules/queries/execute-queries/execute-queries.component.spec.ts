import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExecuteQueriesComponent } from './execute-queries.component';

describe('ExecuteQueriesComponent', () => {
  let component: ExecuteQueriesComponent;
  let fixture: ComponentFixture<ExecuteQueriesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExecuteQueriesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExecuteQueriesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
