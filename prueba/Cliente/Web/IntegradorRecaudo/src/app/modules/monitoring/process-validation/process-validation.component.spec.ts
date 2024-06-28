import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProcessValidationComponent } from './process-validation.component';

describe('ProcessValidationComponent', () => {
  let component: ProcessValidationComponent;
  let fixture: ComponentFixture<ProcessValidationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProcessValidationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProcessValidationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
