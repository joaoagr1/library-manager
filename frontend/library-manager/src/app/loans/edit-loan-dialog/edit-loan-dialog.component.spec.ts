import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditLoanDialogComponent } from './edit-loan-dialog.component';

describe('EditLoanDialogComponent', () => {
  let component: EditLoanDialogComponent;
  let fixture: ComponentFixture<EditLoanDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EditLoanDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditLoanDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
