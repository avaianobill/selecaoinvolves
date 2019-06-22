import { TestBed } from '@angular/core/testing';

import { VinculoClientService } from './vinculo-client.service';

describe('VinculoClientService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: VinculoClientService = TestBed.get(VinculoClientService);
    expect(service).toBeTruthy();
  });
});
