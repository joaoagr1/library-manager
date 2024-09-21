import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditModalComponentComponent } from './edit-modal-component.component';

describe('EditModalComponentComponent', () => {
  let component: EditModalComponentComponent;
  let fixture: ComponentFixture<EditModalComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EditModalComponentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditModalComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
