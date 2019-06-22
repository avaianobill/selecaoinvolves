import { TestBed } from '@angular/core/testing';

import { FuncionarioClientService } from './funcionario-client.service';

describe('FuncionarioClientService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FuncionarioClientService = TestBed.get(FuncionarioClientService);
    expect(service).toBeTruthy();
  });
});
