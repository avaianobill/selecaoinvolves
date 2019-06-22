import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastrarLojaComponent } from './cadastrar-loja.component';

describe('CadastrarLojaComponent', () => {
  let component: CadastrarLojaComponent;
  let fixture: ComponentFixture<CadastrarLojaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CadastrarLojaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CadastrarLojaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
