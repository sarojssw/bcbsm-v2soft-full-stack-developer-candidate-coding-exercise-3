import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubmitfeedbackComponent } from './submitfeedback.component';

describe('SubmitfeedbackComponent', () => {
  let component: SubmitfeedbackComponent;
  let fixture: ComponentFixture<SubmitfeedbackComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SubmitfeedbackComponent]
    });
    fixture = TestBed.createComponent(SubmitfeedbackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
