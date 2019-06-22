import { TestBed } from '@angular/core/testing';

import { LojaClientService } from './loja-client.service';

describe('LojaClientService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LojaClientService = TestBed.get(LojaClientService);
    expect(service).toBeTruthy();
  });
});
