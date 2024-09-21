import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditUserModalComponent } from './edit-modal-component.component';

describe('EditModalComponentComponent', () => {
  let component: EditUserModalComponent;
  let fixture: ComponentFixture<EditUserModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EditUserModalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditUserModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
