import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListRessources } from './list-ressources';

describe('ListRessources', () => {
  let component: ListRessources;
  let fixture: ComponentFixture<ListRessources>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListRessources],
    }).compileComponents();

    fixture = TestBed.createComponent(ListRessources);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
