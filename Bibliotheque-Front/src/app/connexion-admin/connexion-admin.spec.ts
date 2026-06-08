import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter } from '@angular/router';
import { routes } from '../app.routes';

import { ConnexionAdmin } from './connexion-admin';

describe('ConnexionAdmin', () => {
  let component: ConnexionAdmin;
  let fixture: ComponentFixture<ConnexionAdmin>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConnexionAdmin],
      providers: [provideRouter(routes)],
    }).compileComponents();

    fixture = TestBed.createComponent(ConnexionAdmin);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
